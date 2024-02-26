package com.kbtg.bootcamp.posttest.lottery;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

@ExtendWith({MockitoExtension.class})
public class LotteryTest {

    @Test
    @DisplayName("When set Lottery Entity : must be {\"id\":\"123456\",\"ticket\":\"123456\",\"price\":80,\"amount\":1}")
    public void lotteryEntityTest() throws JsonProcessingException, JSONException {
        Lottery lottery = new Lottery();
        lottery.setId("123456");
        lottery.setTicket("123456");
        lottery.setPrice(80);
        lottery.setAmount(1);

        JSONAssert.assertEquals("{\"id\":\"123456\",\"ticket\":\"123456\",\"price\":80,\"amount\":1}",(new ObjectMapper()).writeValueAsString(lottery), JSONCompareMode.NON_EXTENSIBLE);

    }
}
