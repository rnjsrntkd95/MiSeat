package com.miseat.scheduler;

import com.miseat.domain.socket.service.SpaceInfoService;
import com.miseat.domain.space.service.SpaceService;
import com.miseat.domain.team.service.TeamFindService;
import com.miseat.entity.Team;
import com.miseat.global.util.LocalDateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class BatchScheduler {

    private final TeamFindService teamFindService;
    private final SpaceInfoService spaceInfoService;
    private final SpaceService spaceService;

    /**
     * 금요일 오전 11시 다다음 횟차 좌석 생성 (잠금 상태)
     **/
    @Scheduled(cron = "0 0 11 * * 6")
    public void createSpacesTheWeekAfterNext() {
        List<Team> teams = teamFindService.getTeams();

        for (Team team : teams) {
            Integer reservationTerm = team.getReservationTerm();
            LocalDate nextMonday = LocalDateUtils.nextDayOfWeek(LocalDate.now(), DayOfWeek.MONDAY);
            LocalDate from = nextMonday.plusDays(reservationTerm);
            LocalDate to = from.plusDays(reservationTerm);
            from.datesUntil(to).forEach(createDate -> spaceInfoService.createSpaceAndSeats(team, createDate));
        }
    }


    /**
     * 금요일 오전 11시 다음 횟차 좌석 잠금 해제
     **/
    @Scheduled(cron = "0 0 11 * * 6")
    public void unlockedSpacesInNextWeek() {
        List<Team> teams = teamFindService.getTeams();

        for (Team team : teams) {
            Integer reservationTerm = team.getReservationTerm();
            LocalDate from = LocalDateUtils.nextDayOfWeek(LocalDate.now(), DayOfWeek.MONDAY);
            LocalDate to = from.plusDays(reservationTerm);
            List<LocalDate> reservationDates = from.datesUntil(to).toList();

            spaceService.unlockMap(team, reservationDates);
        }
    }
}
