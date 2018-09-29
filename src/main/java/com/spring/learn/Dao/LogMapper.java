package com.spring.learn.Dao;

import com.spring.learn.model.SysLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogMapper {
    void saveLog(SysLog sysLog);
}
