package cn.liangqinghai.study.spring.cloud.project.common.datascope.aspect

import cn.hutool.core.text.StrFormatter
import cn.liangqinghai.study.spring.cloud.project.api.domain.SysUser
import cn.liangqinghai.study.spring.cloud.project.common.core.utils.web.domain.BaseEntity
import cn.liangqinghai.study.spring.cloud.project.common.datascope.annos.DataScope
import cn.liangqinghai.study.spring.cloud.project.common.datascope.service.AwaitUserService
import org.apache.commons.lang3.StringUtils
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.aspectj.lang.reflect.MethodSignature
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Aspect
@Component
open class DataScopeAspect {

    companion object {

        /**
         * 全部权限
         */
        const val DATA_SCOPE_ALL = "1"

        /**
         * 自定义权限
         */
        const val DATA_SCOPE_CUSTOM = "2"

        /**
         * 部门权限
         */
        const val DATA_SCOPE_DEPT = "3"

        /**
         * 部门及以下数据权限
         */
        const val DATA_SCOPE_DEPT_AND_CHILD = "4"

        /**
         * 本人数据权限
         */
        const val DATA_SCOPE_SELF = "5"

        /**
         * 权限关键字
         */
        const val DATA_SCOPE = "dataScope"

        /**
         * 数据范围过滤
         *
         * @param joinPoint 切点
         * @param user 用户
         * @param deptAlias 部门别名
         * @param userAlias 用户别名
         */
        fun dataScopeFilter(joinPoint: JoinPoint, user: SysUser, deptAlias: String?, userAlias: String?) {
            var sqlString = StringBuilder()
            for (role in user.roles!!) {
                val dataScope = role.dataScope
                if (DATA_SCOPE_ALL == dataScope) {
                    sqlString = StringBuilder()
                    break
                } else if (DATA_SCOPE_CUSTOM == dataScope) {
                    sqlString.append(StrFormatter.format(
                        " OR {}.dept_id IN ( SELECT dept_id FROM sys_role_dept WHERE role_id = {} ) ", deptAlias,
                        role.roleId))
                } else if (DATA_SCOPE_DEPT == dataScope) {
                    sqlString.append(StrFormatter.format(" OR {}.dept_id = {} ", deptAlias, user.deptId))
                } else if (DATA_SCOPE_DEPT_AND_CHILD == dataScope) {
                    sqlString.append(StrFormatter.format(
                        " OR {}.dept_id IN ( SELECT dept_id FROM sys_dept WHERE dept_id = {} or find_in_set( {} , ancestors ) )",
                        deptAlias, user.deptId, user.deptId))
                } else if (DATA_SCOPE_SELF == dataScope) {
                    if (StringUtils.isNotBlank(userAlias)) {
                        sqlString.append(StrFormatter.format(" OR {}.user_id = {} ", userAlias, user.userId))
                    } else {
                        // 数据权限为仅本人且没有userAlias别名不查询任何数据
                        sqlString.append(" OR 1=0 ")
                    }
                }
            }
            if (StringUtils.isNotBlank(sqlString.toString())) {
                val baseEntity = joinPoint.args[0] as BaseEntity
                baseEntity.params[DATA_SCOPE] = " AND (" + sqlString.substring(4) + ")"
            }
        }

    }

    @Autowired
    val awaitUserService: AwaitUserService? = null

    /**
     * 切面
     *
     */
    @Pointcut("@annotation(cn.liangqinghai.study.spring.cloud.project.common.datascope.annos.DataScope)")
    fun dataScopePointCut() {}

    @Before("dataScopePointCut()")
    fun doBefore(joinPoint: JoinPoint) {

        handleDataScope(joinPoint)

    }


    protected fun handleDataScope(joinPoint: JoinPoint) {
        // 获得注解
        val controllerDataScope = getAnnotationLog(joinPoint) ?: return
        // 获取当前的用户
        val loginUser = awaitUserService!!.info()
        val currentUser = loginUser?.sysUser
        if (currentUser != null) {
            // 如果是超级管理员，则不过滤数据
            if (!currentUser.isAdmin) {
                dataScopeFilter(joinPoint, currentUser, controllerDataScope.deptAlias,
                    controllerDataScope.userAlias)
            }
        }
    }

    /**
     * 是否存在注解，如果存在就获取
     */
    private fun getAnnotationLog(joinPoint: JoinPoint): DataScope? {
        val signature = joinPoint.signature
        val methodSignature = signature as MethodSignature
        val method = methodSignature.method
        return method?.getAnnotation(DataScope::class.java)
    }

}