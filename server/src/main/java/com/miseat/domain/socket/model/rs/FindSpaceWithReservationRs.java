package com.miseat.domain.socket.model.rs;

import com.miseat.domain.socket.model.dto.ReservationDto;
import com.miseat.entity.Space;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class FindSpaceWithReservationRs {

    @Schema(description = "스페이스 X 크기")
    private Integer xSize;

    @Schema(description = "스페이스 Y 크기")
    private Integer ySize;

    @Schema(description = "예약 일자")
    private LocalDate reservationDate;

    @Schema(description = "예약 정보")
    private List<ReservationDto> reservations = new ArrayList<>();

    public static FindSpaceWithReservationRs create(Space space) {
        FindSpaceWithReservationRs rs = new FindSpaceWithReservationRs();
        rs.xSize = space.getXSize();
        rs.ySize = space.getYSize();
        rs.reservationDate = space.getReservationDate();
        rs.reservations = ReservationDto.createAll(space.getReservations());

        return rs;
    }
}
