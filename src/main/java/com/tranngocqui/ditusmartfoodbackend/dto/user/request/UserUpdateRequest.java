package com.tranngocqui.ditusmartfoodbackend.dto.user.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UserUpdateRequest {
    private String fullName;
    private String password;
    private MultipartFile avatar;
    private String avatarUrl;
    private String language;
    private String timezone;
}
