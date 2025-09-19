package com.tranngocqui.ditusmartfoodbackend.service;

import com.tranngocqui.ditusmartfoodbackend.dto.TestDtoRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.TestDtoResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.Test;
import com.tranngocqui.ditusmartfoodbackend.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestService {
    private final TestRepository testRepository;

    public TestDtoResponse create(TestDtoRequest request) {
        Test test = new Test();
        test.setName(request.getName());

        Test saved = testRepository.save(test);

        return new TestDtoResponse(saved.getId(), saved.getName());
    }

}
