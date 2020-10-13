package com.success.test;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;

/**
 * @Title：
 * @Author：wangchenggong
 * @Date 2020/8/14 16:59
 * @Description
 * @Version
 */
public class TestStr {

    public static void main(String[] args) {

        String a = "abc";
        String b = "bbb";
        String c = "abc";
        String d = "ddd";

        int i = StringUtils.indexOfAny(a, b, c, d);
        System.out.println(i);

    }
}
