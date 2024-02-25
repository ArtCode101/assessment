package com.kbtg.bootcamp.posttest.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@ExtendWith({MockitoExtension.class})
public class AdminControllerTest {

    MockMvc mockMvc;

    @Mock
    AdminService adminService;

    @BeforeEach
    public void init(){
        AdminController adminController = new AdminController(adminService);

        mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
    }

    @Test
    @DisplayName("When Perform on POST: /admin/lotteries should be status code 201")
    public void addLotteriesTest() throws Exception {
        Mockito.when(adminService.addLottery(Mockito.any())).thenReturn(new AdminAddTicketResponse("123456"));

        AdminAddTicketRequest request = new AdminAddTicketRequest("123456",80,1);
        String content = (new ObjectMapper()).writeValueAsString(request);

        mockMvc.perform(post("/admin/lotteries")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
        )
                .andExpect(status().isCreated())
                .andExpect(content().string("{\"ticket\":\"123456\"}"));
    }
}
