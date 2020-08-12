package cn.liangqinghai.study.spring.cloud.project.api.model

import cn.liangqinghai.study.spring.cloud.project.api.domain.SysUser

class UserInfo {

    /**
     * 用户基本信息
     */
    var sysUser: SysUser? = null

    /**
     * 权限标识集合
     */
    var permissions: Set<String>? = null

    /**
     * 角色集合
     */
    var roles: Set<String>? = null

}