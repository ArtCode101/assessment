package com.kbtg.bootcamp.posttest.userTicket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kbtg.bootcamp.posttest.lottery.Lottery;
import org.json.JSONException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
@ExtendWith({MockitoExtension.class})
public class UserTicketTest {

    @Test
    @DisplayName("When set UserTicket Entity : must be {\"id\":1,\"userId\":\"1234567890\",\"lottery\":{\"id\":\"123456\",\"ticket\":\"123456\",\"price\":80,\"amount\":1}}")
    public void userTicketEntityTest() throws JsonProcessingException, JSONException {
        Lottery lottery = new Lottery();
        lottery.setId("123456");
        lottery.setTicket("123456");
        lottery.setPrice(80);
        lottery.setAmount(1);

        UserTicket userTicket = new UserTicket();
        userTicket.setId(1);
        userTicket.setUserId("1234567890");
        userTicket.setLottery(lottery);

        JSONAssert.assertEquals("{\"id\":1,\"userId\":\"1234567890\",\"lottery\":{\"id\":\"123456\",\"ticket\":\"123456\",\"price\":80,\"amount\":1}}",(new ObjectMapper()).writeValueAsString(userTicket), JSONCompareMode.NON_EXTENSIBLE);

    }
}
