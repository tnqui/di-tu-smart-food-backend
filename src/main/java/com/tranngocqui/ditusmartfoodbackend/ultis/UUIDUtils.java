package com.tranngocqui.ditusmartfoodbackend.ultis;

import com.beust.jcommander.internal.Sets;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class UUIDUtils {
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

}
