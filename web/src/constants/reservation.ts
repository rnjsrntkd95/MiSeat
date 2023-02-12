export interface ReserveFieldType {
  date?: string;
  seatNum?: number;
}

export interface ReservationForm {
  reservation: ReserveFieldType[];
}

export const reservationDefaultValue: ReservationForm = {
  reservation: [
    { date: undefined, seatNum: undefined },
    { date: undefined, seatNum: undefined },
    { date: undefined, seatNum: undefined },
    { date: undefined, seatNum: undefined },
    { date: undefined, seatNum: undefined },
  ],
};
