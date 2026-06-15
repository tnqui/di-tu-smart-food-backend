package com.tranngocqui.ditusmartfoodbackend.tempservice;


import com.tranngocqui.ditusmartfoodbackend.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class BaseServiceFactory {

    public <E extends BaseEntity, ID extends UUID> BaseService<E, ID> create(JpaRepository<E, ID> repository) {
        return new BaseServiceImpl<>(repository);
    }

}
