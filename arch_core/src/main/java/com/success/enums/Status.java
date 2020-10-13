package com.success.enums;


/**
 * 处理状态
 * @create 2020-02-29 09:38
 */
public enum Status {

    Pending(0, "未处理"),
    Processing(1, "处理中"),
    Success(2, "成功"),
    Failure(3, "失败"),
    PartSuccess(4,"部分成功"),
    Refused(5,"审核拒绝"),
    Unknown(6, "未知");

    private int code;
    private String desc;

    Status(int code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public int code(){
        return this.code;
    }

    public String desc(){
        return this.desc;
    }

    public static String getStatus(int code){
        Status[] arr = Status.values();
        for(int i=0; i<arr.length; i++){
            if(arr[i].code==code){
                return arr[i].desc();
            }
        }
        return "";
    }
}
