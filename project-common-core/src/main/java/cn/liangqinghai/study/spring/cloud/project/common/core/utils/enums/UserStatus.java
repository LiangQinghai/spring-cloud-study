package cn.liangqinghai.study.spring.cloud.project.common.core.utils.enums;

/**
 * @author LiangQinghai
 * @title UserStatus
 * @projectName spring-cloud-study
 * @description
 * @date 2020/8/12 16:20
 */
public enum UserStatus {
    OK("0", "正常"), DISABLE("1", "停用"), DELETED("2", "删除");

    private final String code;
    private final String info;

    UserStatus(String code, String info)
    {
        this.code = code;
        this.info = info;
    }

    public String getCode()
    {
        return code;
    }

    public String getInfo()
    {
        return info;
    }
}
