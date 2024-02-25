package com.kbtg.bootcamp.posttest.admin;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigInteger;

public record AdminAddTicketRequest (
        @NotNull
                @Size(min = 6,max = 6)
        String ticket,

        BigInteger price,

        BigInteger amount
) {

}
