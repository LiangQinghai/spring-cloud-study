package cn.liangqinghai.study.spring.cloud.project.api.domain

import cn.liangqinghai.study.spring.cloud.project.common.core.utils.annos.Excel
import cn.liangqinghai.study.spring.cloud.project.common.core.utils.web.domain.BaseEntity
import com.fasterxml.jackson.annotation.JsonFormat
import java.util.*

class SysOperLog : BaseEntity(){

    /** 日志主键  */
    @Excel(name = "操作序号", cellType = Excel.ColumnType.NUMERIC)
    var operId: Long? = null

    /** 操作模块  */
    @Excel(name = "操作模块")
    var title: String? = null

    /** 业务类型（0其它 1新增 2修改 3删除）  */
    @Excel(name = "业务类型", readConverterExp = "0=其它,1=新增,2=修改,3=删除,4=授权,5=导出,6=导入,7=强退,8=生成代码,9=清空数据")
    var businessType: Int? = null

    /** 业务类型数组  */
    lateinit var businessTypes: Array<Int>

    /** 请求方法  */
    @Excel(name = "请求方法")
    var method: String? = null

    /** 请求方式  */
    @Excel(name = "请求方式")
    var requestMethod: String? = null

    /** 操作类别（0其它 1后台用户 2手机端用户）  */
    @Excel(name = "操作类别", readConverterExp = "0=其它,1=后台用户,2=手机端用户")
    var operatorType: Int? = null

    /** 操作人员  */
    @Excel(name = "操作人员")
    var operName: String? = null

    /** 部门名称  */
    @Excel(name = "部门名称")
    var deptName: String? = null

    /** 请求url  */
    @Excel(name = "请求地址")
    var operUrl: String? = null

    /** 操作地址  */
    @Excel(name = "操作地址")
    var operIp: String? = null

    /** 请求参数  */
    @Excel(name = "请求参数")
    var operParam: String? = null

    /** 返回参数  */
    @Excel(name = "返回参数")
    var jsonResult: String? = null

    /** 操作状态（0正常 1异常）  */
    @Excel(name = "状态", readConverterExp = "0=正常,1=异常")
    var status: Int? = null

    /** 错误消息  */
    @Excel(name = "错误消息")
    var errorMsg: String? = null

    /** 操作时间  */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "操作时间", width = 30.0, dateFormat = "yyyy-MM-dd HH:mm:ss")
    var operTime: Date? = null

}