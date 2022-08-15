package com.ssafy.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.api.service.ConsultService;
import com.ssafy.api.service.ScriptService;
import com.ssafy.api.service.UserService;
import com.ssafy.common.auth.SsafyUserDetailService;
import com.ssafy.db.entity.Script;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.awt.*;
import java.util.HashMap;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ConsultController.class)
class ConsultControllerTest {

    @MockBean
    UserService userService;

    @MockBean
    SsafyUserDetailService ssafyUserDetailService;

    @MockBean
    PasswordEncoder passwordEncoder;

    @MockBean
    ConsultService consultService;

    @MockBean
    ScriptService scriptService;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void 상담생성() throws Exception {
        HashMap<String, String> input = new HashMap<>();

        input.put("scriptId", "13");
        input.put("room", "randomroomnumber");

        HashMap<String, String> output = new HashMap<>();
        output.put("message", "Success created consult");
        given(scriptService.getScriptByScriptId(13L)).willReturn(Optional.of(new Script()));
        mockMvc.perform(post("/api/v1/consult")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(objectMapper.writeValueAsString(input))
                )
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(objectMapper.writeValueAsString(output)));
    }

    @Test
    void 상담생성실패() throws Exception {
        HashMap<String, String> input = new HashMap<>();
        input.put("scriptId", "1");

        input.put("room", "randomroomnumber");

        HashMap<String, String> output = new HashMap<>();
        output.put("message", "No value present");
        given(scriptService.getScriptByScriptId(1L)).willReturn(Optional.ofNullable(null));
        mockMvc.perform(post("/api/v1/consult")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(objectMapper.writeValueAsString(input))
                )
                .andExpect(status().is5xxServerError())
                .andExpect(content().json(objectMapper.writeValueAsString(output)));
    }

    @Test
    void 대기중인상담조회() throws Exception {
        HashMap<String, String> output = new HashMap<>();
        output.put("message", "Success to get consult list");
        mockMvc.perform(get("/api/v1/consult"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(output)));
    }

    @Test
    void 상담완료성공() throws Exception {
        HashMap<String, String> output = new HashMap<>();
        output.put("message", "Consult completed!");
        given(consultService.exist(1L)).willReturn(true);
        mockMvc.perform(delete("/api/v1/consult/complete/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(output)));
    }

    @Test
    void 상담완료실패() throws Exception {
        HashMap<String, String> output = new HashMap<>();
        output.put("message", "존재하지 않는 상담입니다.");
        given(consultService.exist(1L)).willReturn(false);
        mockMvc.perform(delete("/api/v1/consult/complete/1"))
                .andExpect(status().is4xxClientError())
                .andExpect(content().json(objectMapper.writeValueAsString(output)));
    }
}