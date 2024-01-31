package com.edu.board.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import com.fasterxml.jackson.databind.ObjectMapper;

import mypage.domain.Mypage;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class MyPageControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    private final String myPageControllerURL = "/mypage";

    @Test
    void myPageTest() throws Exception {
        // 사용자 생성
        Mypage user = new Mypage();
        
        user.setUserId("test1");
        user.setUserName("testman");
        user.setUserMoney(10000); 
        user.setUserSubscribeStatus(false); 

        // 사용자 생성 API 호출
        mvc.perform(MockMvcRequestBuilders
                        .post("/users")
                        .content(objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").exists());

        // 사용자 정보 조회 API 호출
        mvc.perform(MockMvcRequestBuilders
                        .get(myPageControllerURL + "/user/test1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value("test1"))
                .andExpect(jsonPath("$.userName").value("testman"))
                .andExpect(jsonPath("$.userMoney").value(10000))
                .andExpect(jsonPath("$.status").value(0)) 
                .andReturn();
    }
}
