package com.tranngocqui.ditusmartfoodbackend.service;

import com.tranngocqui.ditusmartfoodbackend.entity.BaseEntity;
import com.tranngocqui.ditusmartfoodbackend.exception.AppException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BaseServiceImpl<E extends BaseEntity, ID extends UUID> implements BaseService<E, ID> {
    private final JpaRepository<E, ID> repository;

    @Override
    public List<E> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<E> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<E> findPaginationById(ID id, Pageable pageable) {
        return null;
    }

    @Override
    public Optional<E> findById(ID id) {
        return Optional.empty();
    }

    @Override
    public Optional<E> findOptionalById(ID id) {
        return repository.findById(id);
    }

    @Override
    public E findByIdOrThrow(ID id) {
        return repository.findById(id).orElseThrow(() -> new AppException(getNotFoundErrorCode()));
    }

    @Override
    @Transactional
    public E save(E entity) {
        return repository.save(entity);
    }

    @Override
    @Transactional
    public void deleteById(ID id) {
        E entity = repository.findById(id).orElseThrow(() -> new AppException(getNotFoundErrorCode()));
        entity.setDeleted(true);
        entity.setDeletedAt(Instant.now());
        repository.save(entity);
    }

    @Override
    public List<E> findAllInIds(List<ID> ids) {
        return repository.findAllById(ids);
    }

    @Override
    public boolean existsById(ID id) {
        return repository.existsById(id);
    }
}
