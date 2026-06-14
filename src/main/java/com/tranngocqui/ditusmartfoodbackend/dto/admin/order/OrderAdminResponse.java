package com.tranngocqui.ditusmartfoodbackend.dto.admin.order;


import lombok.Builder;

import java.util.UUID;

@Builder
public record OrderAdminResponse(UUID id, String orderId) {
}
