//package com.zp.gmall.module.system.controller.admin;
//
//import com.alibaba.nacos.common.http.param.MediaType;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Tag;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import static org.junit.jupiter.api.Assertions.*;
//
///**
// * Author : zhengpanone
// * Date : 2025/4/1 18:55
// * Version : v1.0.0
// * Description:
// */
//@SpringBootTest
//@AutoConfigureMockMvc
//@DisplayName("用户管理单元测试")
//class UserControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Tag("local")
//    @DisplayName("新增用户")
//    @Test
//    void createUser() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.post("/system/user/create").accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
//    }
//
//    @DisplayName("更新用户")
//    @Test
//    void updateUser() {
//    }
//
//    @DisplayName("获取用户")
//    @Test
//    void getUserList() {
//    }
//}