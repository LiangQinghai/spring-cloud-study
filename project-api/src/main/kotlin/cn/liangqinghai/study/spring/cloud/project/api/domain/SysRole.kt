package cn.liangqinghai.study.spring.cloud.project.api.domain

import cn.liangqinghai.study.spring.cloud.project.common.core.utils.annos.Excel
import cn.liangqinghai.study.spring.cloud.project.common.core.utils.web.domain.BaseEntity

class SysRole : BaseEntity {

    constructor() {}

    constructor(roleId: Long?) {
        this.roleId = roleId
    }

    /** 角色id **/
    @Excel(name = "角色序号", cellType = Excel.ColumnType.NUMERIC)
    var roleId: Long? = null

    /** 角色名称 **/
    @Excel(name = "角色名称")
    var roleName: String? = null

    /** 角色权限 */
    @Excel(name = "角色权限")
    var roleKey: String? = null

    /** 角色排序  */
    @Excel(name = "角色排序")
    var roleSort: String? = null

    /** 数据范围（1：所有数据权限；2：自定义数据权限；3：本部门数据权限；4：本部门及以下数据权限）  */
    @Excel(name = "数据范围", readConverterExp = "1=所有数据权限,2=自定义数据权限,3=本部门数据权限,4=本部门及以下数据权限")
    var dataScope: String? = null

    /** 角色状态（0正常 1停用）  */
    @Excel(name = "角色状态", readConverterExp = "0=正常,1=停用")
    var status: String? = null

    /** 删除标志（0代表存在 2代表删除）  */
    var delFlag: String? = null

    /** 用户是否存在此角色标识 默认不存在  */
    var flag = false

    /** 菜单组  */
    lateinit var menuIds: Array<Long>

    /** 部门组（数据权限）  */
    lateinit var deptIds: Array<Long>


}