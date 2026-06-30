package com.tranngocqui.ditusmartfoodbackend.ultis;

import java.math.BigDecimal;

public class BigDecimalUtils {
    public static boolean isGreater(BigDecimal a, BigDecimal b) {
        return a.compareTo(b) > 0;
    }

    public static boolean isLess(BigDecimal a, BigDecimal b) {
        return a.compareTo(b) < 0;
    }

    public static boolean isEqual(BigDecimal a, BigDecimal b) {
        return a.compareTo(b) == 0;
    }

    public static boolean isGreaterOrEqual(BigDecimal a, BigDecimal b) {
        return a.compareTo(b) >= 0;
    }

    public static boolean isLessOrEqual(BigDecimal a, BigDecimal b) {
        return a.compareTo(b) <= 0;
    }

}
