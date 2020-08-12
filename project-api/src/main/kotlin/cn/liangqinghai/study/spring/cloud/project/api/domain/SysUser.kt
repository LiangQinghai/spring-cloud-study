package cn.liangqinghai.study.spring.cloud.project.api.domain

import cn.liangqinghai.study.spring.cloud.project.common.core.utils.annos.Excel
import cn.liangqinghai.study.spring.cloud.project.common.core.utils.annos.Excels
import cn.liangqinghai.study.spring.cloud.project.common.core.utils.web.domain.BaseEntity
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

class SysUser : BaseEntity {

    constructor() {}

    constructor(userId: Long?) {
        this.userId = userId
    }

    /** 用户ID  */
    @Excel(name = "用户序号", cellType = Excel.ColumnType.NUMERIC, prompt = "用户编号")
    var userId: Long? = null

    /** 部门ID  */
    @Excel(name = "部门编号", type = Excel.Type.IMPORT)
    var deptId: Long? = null

    /** 用户账号  */
    @Excel(name = "登录名称")
    var userName: String? = null

    /** 用户昵称  */
    @Excel(name = "用户名称")
    var nickName: String? = null

    /** 用户邮箱  */
    @Excel(name = "用户邮箱")
    var email: String? = null

    /** 手机号码  */
    @Excel(name = "手机号码")
    var phonenumber: String? = null

    /** 用户性别  */
    @Excel(name = "用户性别", readConverterExp = "0=男,1=女,2=未知")
    var sex: String? = null

    /** 用户头像  */
    var avatar: String? = null

    /** 密码  */
    @get:JsonProperty
    var password: String? = null

    /** 盐加密  */
    var salt: String? = null

    /** 帐号状态（0正常 1停用）  */
    @Excel(name = "帐号状态", readConverterExp = "0=正常,1=停用")
    var status: String? = null

    /** 删除标志（0代表存在 2代表删除）  */
    var delFlag: String? = null

    /** 最后登陆IP  */
    @Excel(name = "最后登陆IP", type = Excel.Type.EXPORT)
    var loginIp: String? = null

    /** 最后登陆时间  */
    @Excel(name = "最后登陆时间", width = 30.0, dateFormat = "yyyy-MM-dd HH:mm:ss", type = Excel.Type.EXPORT)
    var loginDate: Date? = null

    /** 部门对象  */
    @Excels(Excel(name = "部门名称", targetAttr = "deptName", type = Excel.Type.EXPORT), Excel(name = "部门负责人", targetAttr = "leader", type = Excel.Type.EXPORT))
    var dept: SysDept? = null

    /** 角色对象  */
    var roles: List<SysRole>? = null

    /** 角色组  */
    lateinit var roleIds: Array<Long>

    /** 岗位组  */
    lateinit var postIds: Array<Long>

    val isAdmin: Boolean
        get() = isAdmin(userId)

    companion object {
        fun isAdmin(userId: Long?): Boolean {
            return userId != null && 1L == userId
        }
    }

}