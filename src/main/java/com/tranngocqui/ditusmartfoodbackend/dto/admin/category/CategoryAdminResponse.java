package com.tranngocqui.ditusmartfoodbackend.dto.admin.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryAdminResponse {
    private String name;
    private String description;
}
