package com.tranngocqui.ditusmartfoodbackend.dto.admin.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryAdminRequest {
    private String name;
    private String description;
}
