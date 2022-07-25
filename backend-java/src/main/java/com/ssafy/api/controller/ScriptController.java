package com.ssafy.api.controller;


import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "스크립트 API", tags = {"Script."})
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class ScriptController {

}
