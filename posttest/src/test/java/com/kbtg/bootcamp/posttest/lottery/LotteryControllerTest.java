package com.kbtg.bootcamp.posttest.lottery;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({MockitoExtension.class})
public class LotteryControllerTest {
    MockMvc mockMvc;

    @Mock
    LotteryService lotteryService;

    @BeforeEach
    public void init(){
        LotteryController lotteryController = new LotteryController(lotteryService);
        mockMvc = MockMvcBuilders.standaloneSetup(lotteryController).build();
    }

    @Test
    @DisplayName("When Perform on Get: /Lotteries should be status code 200")
    public void getLotteries() throws Exception {
        mockMvc.perform(get("/lotteries")).andExpect(status().isOk());
    }

}
