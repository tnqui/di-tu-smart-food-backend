package com.tranngocqui.ditusmartfoodbackend.tempservice;

import com.tranngocqui.ditusmartfoodbackend.entity.BaseEntity;
import com.tranngocqui.ditusmartfoodbackend.enums.ErrorCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BaseService<E extends BaseEntity, ID extends UUID> {

    List<E> findAll();

    Page<E> findAll(Pageable pageable);

    Page<E> findPaginationById(ID id, Pageable pageable);

    Optional<E> findById(ID id);

    Optional<E> findOptionalById(ID id);

    E findByIdOrThrow(ID id);

    E save(E entity);

    void deleteById(ID id);

    List<E> findAllInIds(List<ID> ids);

    boolean existsById(ID id);


    // Cho phép mỗi entity override error code "not found"
    default ErrorCode getNotFoundErrorCode() {
        return ErrorCode.ENTITY_NOT_FOUND;
    }
}
