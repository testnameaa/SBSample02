package com.example.doma2test2.common;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;

public class CheckUtil {
    // 必須入力チェック
    public static boolean isEmpty(String inp) {
        return StringUtils.isEmpty(inp);
    }

    // 必須入力チェック（数値）
    public static boolean isEmptyNumber(String inp) {
        boolean enpty;
        if ("0".equals(inp)) {
            enpty = true;
        } else {
            enpty = StringUtils.isEmpty(inp);
        }
        return enpty;
    }

    // 日付チェック
    public static boolean isDate(String inp) {
        List<DateTimeFormatter> formatters = DateUtil.getFormatters();
        for (DateTimeFormatter formatter : formatters) {
            try {
                LocalDate.parse(inp, formatter);
                return true;
            } catch (DateTimeParseException ignored) {
                // 次のパターンへ。。。
            }
        }

        // どのパターンにも該当しない場合はエラー
        return false;
    }

    // 数値チェック
    public static boolean isNumber(String inp) {
        return NumberUtils.isCreatable(inp);
    }
}
