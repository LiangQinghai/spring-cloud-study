package cn.liangqinghai.study.spring.cloud.project.api.domain

import cn.liangqinghai.study.spring.cloud.project.common.core.utils.web.domain.BaseEntity
import java.util.ArrayList

class SysDept : BaseEntity() {

    /** 部门id **/
    var deptId: Long? = null

    /** 父部门id **/
    var parentId: Long? = null

    /** 祖级列表  */
    var ancestors: String? = null

    /** 部门名称  */
    var deptName: String? = null

    /** 显示顺序  */
    var orderNum: String? = null

    /** 负责人  */
    var leader: String? = null

    /** 联系电话  */
    var phone: String? = null

    /** 邮箱  */
    var email: String? = null

    /** 部门状态:0正常,1停用  */
    var status: String? = null

    /** 删除标志（0代表存在 2代表删除）  */
    var delFlag: String? = null

    /** 父部门名称  */
    var parentName: String? = null

    /** 子部门  */
    var children: List<SysDept> = ArrayList()
    override fun toString(): String {
        return "SysDept(deptId=$deptId, parentId=$parentId, ancestors=$ancestors, deptName=$deptName, orderNum=$orderNum, leader=$leader, phone=$phone, email=$email, status=$status, delFlag=$delFlag, parentName=$parentName, children=$children)"
    }

}