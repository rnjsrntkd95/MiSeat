package com.miseat.domain.space.model.rq;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class FindSpaceWithSeatsRq {

    @NotNull
    private LocalDate date;
}
