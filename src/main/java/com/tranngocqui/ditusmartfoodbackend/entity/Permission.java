package com.tranngocqui.ditusmartfoodbackend.entity;

import com.tranngocqui.ditusmartfoodbackend.enums.ErrorCode;
import com.tranngocqui.ditusmartfoodbackend.exception.AppException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "permission@")
@Getter
@Setter(AccessLevel.PRIVATE)
@SuperBuilder(toBuilder = true)
@Where(clause = "deleted = false")
@NoArgsConstructor
public class Permission extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String description;


    private Permission(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public static Permission create(String name, String description) {
        validate(name);
        return new Permission(name, description);
    }

    public void update(String name, String description) {
        if (name != null) {
            validate(name);
            this.name = name.trim();
        }
        if (description != null) {
            validate(description);
            this.description = description.trim();
        }
    }

    private static void validate(String name) {
        if (name == null || name.isBlank()) {
            throw new AppException(ErrorCode.PERMISSION_INVALID);
        }
    }
}
