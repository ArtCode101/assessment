package com.kbtg.bootcamp.posttest.admin;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AdminAddTicketRequest (
        @NotNull
                @Size(min = 6,max = 6)
        String ticket,

        Integer price,

        Integer amount
) {

}
