package com.tranngocqui.ditusmartfoodbackend.controller;

import com.tranngocqui.ditusmartfoodbackend.dto.TestDtoRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.TestDtoResponse;
import com.tranngocqui.ditusmartfoodbackend.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @PostMapping
    TestDtoResponse create(@RequestBody TestDtoRequest request) {
        return testService.create(request);
    }
}
