package com.success.enums;

/**
* 任务日志类型
* @create 2020-04-10 08:48
*/
public enum TasklogType {

    /**
     * 启动
     */
    Start("S", "启动"),

    Running("R", "运行"),

    Stop("P", "停止");

    private String code;
    private String desc;

    TasklogType(String code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public String code(){
        return this.code;
    }

    public String desc(){
        return this.desc;
    }

    /**
     * 获取枚举描述
     * @param code
     * @return
     */
    public static String getDesc(String code){
        TasklogType[] arr = TasklogType.values();
        for(TasklogType type : arr){
            if(type.code.equalsIgnoreCase(code)){
                return type.desc();
            }
        }
        return "";
    }
}
