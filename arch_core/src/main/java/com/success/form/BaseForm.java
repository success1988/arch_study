package com.success.form;


import com.success.common.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BaseForm extends BaseEntity {

    /**
     * 系统标识
     */
    private String systemSign;

    /**
     * 子系统标识
     */
    private String subSystemSign;

    /**
     * 子账户编号
     */
    private String subAccountNo;

    /**
     * 账户编号
     */
    private String accountNo;

    /**
     * 创建起始时间
     */
    private String startCreateTime;

    /**
     * 创建结束时间
     */
    private String endCreateTime;

}
