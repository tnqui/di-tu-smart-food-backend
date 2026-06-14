package com.tranngocqui.ditusmartfoodbackend.ultis;

import com.fasterxml.uuid.Generators;
import com.tranngocqui.ditusmartfoodbackend.enums.ErrorCode;
import com.tranngocqui.ditusmartfoodbackend.exception.AppException;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class UUIDUtils {

    public static UUID generateUUID() {
        return Generators.timeBasedEpochRandomGenerator().generate();
    }

    public static Set<UUID> fromSetString(Set<String> stringSet) {
        if (stringSet == null) return Set.of();
        return stringSet.stream().map(UUID::fromString)
                .collect(Collectors.toSet());
    }

    public static List<UUID> fromListString(List<String> stringList) {
        if (stringList == null) return List.of();
        return stringList.stream().map(UUID::fromString)
                .collect(Collectors.toList());
    }

    public static UUID parseUUUIDFromString(String id) {
        if (id == null || id.isBlank()) {
            throw new AppException(ErrorCode.REQUIRED_FIELD_MISSING);
        }
        try {
            return UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            throw new AppException(ErrorCode.INVALID_INPUT_FORMAT);
        }
    }

}
