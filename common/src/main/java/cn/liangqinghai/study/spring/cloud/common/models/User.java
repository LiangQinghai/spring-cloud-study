package cn.liangqinghai.study.spring.cloud.common.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author LiangQinghai
 * @title User
 * @projectName spring-cloud-study
 * @description
 * @date 2020/8/3 14:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements Serializable {

    private static final long serialVersionUID = -1394929948879509153L;

    private String id;

    private String username;

    private String password;

}
