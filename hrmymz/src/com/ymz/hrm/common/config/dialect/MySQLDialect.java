package com.ymz.hrm.common.config.dialect;

/**
 * Created by yemingzhu on 2016/11/10.
 */
public class MySQLDialect extends org.hibernate.dialect.MySQLDialect{
    @Override
    public String getTableTypeString() {
        return " ENGINE=InnoDB DEFAULT CHARSET=utf8";
    }
}
