package cn.liangqinghai.study.spring.cloud.project.common.datascope.annos

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class DataScope(
        /**
         * 部门别名
         */
        val deptAlias: String = "",
        /**
         * 员工别名
         */
        val userAlias: String = ""
)