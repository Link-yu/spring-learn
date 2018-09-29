package com.spring.learn.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 维斯
 */
@Data
public class SysLog implements Serializable {
    private static final long serialVersionUID = -2842014845946914960L;
    private Integer id;
    private String username;
    private String operation;
    private Integer time;
    private String method;
    private String params;
    private String ip;
    private Date createTime;
}
