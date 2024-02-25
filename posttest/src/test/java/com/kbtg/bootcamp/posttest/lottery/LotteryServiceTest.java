package com.kbtg.bootcamp.posttest.lottery;

import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.util.ArrayList;
import java.util.List;

@ExtendWith({MockitoExtension.class})
public class LotteryServiceTest {

    @Mock
    LotteryRepository lotteryRepository;

    LotteryService lotteryService;

    @BeforeEach
    public void init(){
        lotteryService = new LotteryService(lotteryRepository);
    }

    @Test
    @DisplayName("When get all lottery : must be list ticket")
    public void getLotteryTest() throws JSONException {
        List<Lottery> lotteries = new ArrayList<>(
                List.of(
                        new Lottery("123458","123456",80,1),
                        new Lottery("123457","123457",80,1)
                )
        );
        Mockito.when(lotteryRepository.findAll()).thenReturn(lotteries);
        GetLotteriesResponse response = lotteryService.getLotteries();

        JSONAssert.assertEquals("[123456, 123457]",response.tickets().toString(), JSONCompareMode.NON_EXTENSIBLE);
    }
}
