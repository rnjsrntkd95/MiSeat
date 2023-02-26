export default interface FindSpaceWithSeatsRs {
  teamCode: number;
  reservationDate: string;
  seats: SeatDto;
  ysize: number;
  xsize: number;
}

interface SeatDto {
  seatNumber: number;
  x: number;
  y: number;
  workerName: string;
}
