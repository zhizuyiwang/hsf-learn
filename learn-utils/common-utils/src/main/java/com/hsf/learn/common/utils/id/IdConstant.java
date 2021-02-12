package com.hsf.learn.common.utils.id;

public class IdConstant {

    /**
     * 用户ID，hashcode偏移量
     */
    public static final int DEFAULT_USER_ID_SHIFT = 4;

    /**
     * 起始的时间戳
     * 2019-01-01
     */
    public static final long IDEPOCH = 1546272000000L;

    /**
     * 机器标识占用的位数
     */
    public static final long MACHINE_BIT = 3L;

    /**
     * 序列号占用的位数
     */
    public static final long SEQUENCE_BIT = 9L;

    /**
     * 时间戳占用的位数
     */
    public static final long TIMESTAMP_BIT = 41L;

    /**
     * 12 时间戳移动的位数
     */
    public static final long TIMESTAMP_LEFT_SHIFT = SEQUENCE_BIT + MACHINE_BIT;

    /**
     * 53 用户id移动的位数
     */
    public static final long USER_ID_LEFT_SHIFT = TIMESTAMP_BIT + TIMESTAMP_LEFT_SHIFT;

    /**
     * 机器标识最大值 7
     */
    public static final long MAX_MACHINE_NUM = -1L ^ (-1L << MACHINE_BIT);

    /**
     * 序列最大值 511
     */
    public static final long MAX_SEQUENCE_NUM = -1L ^ (-1L << SEQUENCE_BIT);

    /**
     * userId取模的基数
     */
    public static final int DEFAULT_USER_HASH_SIZE = 1024;

    public static final String ID_MODE_LINE = "Line_Code";
    public static final String ID_MODE_TOWER= "Tower_Code";
    public static final String ID_MODE_SUPPLIER= "Supplier_Code";
    public static final String ID_MODE_DEV  = "Dev_Code";
    public static final String ID_MODE_USER = "User_Code";
    public static final String ID_MODE_EVENT= "Event_Code";

    public static final String ID_MODE_ORG= "Org_Code";
    public static final String ID_MODE_DEPT= "Dept_Code";
    public static final String ID_MODE_TEAM= "Team_Code";

    public static final String ID_MODE_EMPLOYEE = "Employee_Code";

    public static final String ID_MODE_ROLE = "Role_Code";
    public static final String ID_MODE_MODULE = "Module_Code";
    public static final String ID_MODE_RESOURCE = "Resource_Code";
    public static final String ID_MODE_DEV_BASIC = "DevBasic_Code";


}
