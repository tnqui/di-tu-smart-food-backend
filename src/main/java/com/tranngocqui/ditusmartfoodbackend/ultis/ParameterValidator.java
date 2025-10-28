package com.tranngocqui.ditusmartfoodbackend.ultis;

import com.tranngocqui.ditusmartfoodbackend.enums.ErrorCode;
import com.tranngocqui.ditusmartfoodbackend.exception.AppException;

import java.util.List;

public class ParameterValidator {
    public static <T> List<T> requireNonEmptyList(List<T> list, ErrorCode code) {
        if (list == null || list.isEmpty()) throw new AppException(code);
        return list;
    }
}
