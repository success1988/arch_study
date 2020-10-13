package com.success.enums;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public enum DateFormatEnum {
    YMD_HMS("yyyy-MM-dd HH:mm:ss"),
    YMDHMS("yyyyMMddHHmmss"),
    Y_MD("yyyy-MM-dd"),
    YMD("yyyyMMdd");

    private static final Map<String, SimpleDateFormat> sdfMap = new HashMap(16);
    private String pattern;

    private DateFormatEnum(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return this.pattern;
    }

    private SimpleDateFormat getFormat(String pattern) {
        SimpleDateFormat sdf = (SimpleDateFormat)sdfMap.get(pattern);
        if (sdf == null) {
            sdf = new SimpleDateFormat(pattern);
            sdfMap.put(pattern, sdf);
        }

        return sdf;
    }

    public String dateToString(Date date) {
        return date != null ? this.getFormat(this.pattern).format(date) : "";
    }

    public Date stringToDate(String dateStr) throws ParseException {
        return this.getFormat(this.pattern).parse(dateStr);
    }

    public String localDateToString(LocalDate date) {
        return date != null ? date.format(DateTimeFormatter.ofPattern(this.getPattern())) : "";
    }

    public LocalDate stringToLocalDate(String dateStr) {
        return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(this.getPattern()));
    }
}

