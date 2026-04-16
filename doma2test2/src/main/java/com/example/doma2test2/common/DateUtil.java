package com.example.doma2test2.common;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoField;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class DateUtil {
    // システム日付をStringで返す
    public static String GetSysString() {
        LocalDate now = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return now.format(dateFormatter);
    }

    // システム日付をTimestampで返す
    public static Timestamp GetSysTimeStamp() {
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);
        return timestamp;
    }

    // 日付変換(String -> sql.Date)
    public static Date parseDate(String inp) {
        if (StringUtils.isBlank(inp)) {
            throw new IllegalArgumentException("日付文字列が空またはnullです");
        }

        inp = inp.trim();
        List<DateTimeFormatter> formatters = DateUtil.getFormatters();
        for (DateTimeFormatter formatter : formatters) {
            try {
                LocalDate localDate = LocalDate.parse(inp, formatter);
                return Date.valueOf(localDate);
            } catch (DateTimeParseException ignored) {
                // 次のパターンへ。。。
            }
        }

        throw new IllegalArgumentException("未対応の日付形式です：" + inp);
    }

    // 日付変換(sql.Date -> String)
    public static String parseString(Date inp, String pattern) {
        if (inp == null) {return "";}

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return inp.toLocalDate().format(formatter);
    }

    // 日付チェック
    public static List<DateTimeFormatter> getFormatters() {
        List<DateTimeFormatter> formatters = Arrays.asList(
            DateTimeFormatter.ofPattern("yyyyMMdd"),
            new DateTimeFormatterBuilder()
                .appendValue(ChronoField.YEAR, 4)
                .appendLiteral('/')
                .appendValue(ChronoField.MONTH_OF_YEAR)
                .appendLiteral('/')
                .appendValue(ChronoField.DAY_OF_MONTH)
                .toFormatter()
                .withResolverStyle(ResolverStyle.STRICT),
            new DateTimeFormatterBuilder()
                .appendValue(ChronoField.YEAR, 4)
                .appendLiteral('-')
                .appendValue(ChronoField.MONTH_OF_YEAR)
                .appendLiteral('-')
                .appendValue(ChronoField.DAY_OF_MONTH)
                .toFormatter()
                .withResolverStyle(ResolverStyle.STRICT)

        );
        return formatters;
    }

    // 日付変換＆日単位減算(String -> sql.Date)
    public static Date minusDays(String inp, Integer minus) {
        Date date = parseDate(inp);
        LocalDate localDate = date.toLocalDate();
        localDate = localDate.minusDays(minus);
        return Date.valueOf(localDate);
    }
}
