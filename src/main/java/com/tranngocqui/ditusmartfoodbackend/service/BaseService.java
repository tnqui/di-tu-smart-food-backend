package com.tranngocqui.ditusmartfoodbackend.service;

import com.tranngocqui.ditusmartfoodbackend.entity.BaseEntity;
import com.tranngocqui.ditusmartfoodbackend.enums.ErrorCode;
import com.tranngocqui.ditusmartfoodbackend.exception.AppException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
public abstract class BaseService<E extends BaseEntity, ID> {
    protected final JpaRepository<E, ID> repository;

    protected Page<E> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    protected final Optional<E> findOptionalById(ID id) {
        return repository.findById(id);
    }

    protected final E findByIdOrThrow(ID id) {
        return repository.findById(id).orElseThrow(() -> new AppException(getNotFoundErrorCode()));
    }

    protected final E save(E entity) {
        return repository.save(entity);
    }

    protected final void deleteById(ID id) {
        E entity = repository.findById(id).orElseThrow(() -> new AppException(getNotFoundErrorCode()));
        entity.setDeleted(true);
        entity.setDeletedAt(Instant.now());
        repository.save(entity);
    }

    protected final List<E> findAllById(List<ID> ids) {
        return repository.findAllById(ids);
    }

    protected abstract ErrorCode getNotFoundErrorCode();

    protected final boolean existsById(ID id) {
        return repository.existsById(id);
    }

}
