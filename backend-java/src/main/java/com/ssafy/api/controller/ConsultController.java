package com.ssafy.api.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "상담 API", tags = {"Consult."})
@RestController
@RequestMapping("/api/v1/consult")
@RequiredArgsConstructor
public class ConsultController {
    
}
