package com.ssafy.api.controller;

import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.api.service.UserService;
import com.ssafy.db.entity.User;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import javax.security.auth.login.AccountException;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;

@SpringBootTest
//@ExtendWith(MockitoExtension.class)
//@AutoConfigureMockMvc
//@WebMvcTest
public class AuthControllerTest {

    @Autowired UserService userService;
//    @Autowired
//    private MockMvc mockMvc;

    @DisplayName("로그인 테스트")
    @Test
    public void login() throws Exception {
        UserRegisterPostReq userRegisterInfo = new UserRegisterPostReq();
        userRegisterInfo.setId("test");
        userRegisterInfo.setPassword("1234");
        }

    // 테스트 유저 생성
    // private
//    @Test
//    public void login() throws Exception {
//        final String username = "test";
//        final String password = "1234";
//
//        mockMvc.perform(formLogin().user("test").password("1234"))
//            .andExpect(authenticated());
//    }
//
//    private void createUser(String username, String password) {
//
//    }


//    private User createUser(String username, String password) {
//        User user = new User();
//        user.setUserId(username);
//        user.setPassword(password);
//        return userService.createUser(user);
//    }
//
//    @Test
//    public void login() throws Exception {
//        //given
//        final String username = "test";
//        final String password = "1234";
//        User createduser = createUser(username, password);
//        //when
//        mockMvc.perform(formLogin().user(username).password("2345"))
//                .andExpect(authenticated());
//        //then
//    }

}