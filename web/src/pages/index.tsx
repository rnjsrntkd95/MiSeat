import WeekCalender from '@components/weekCalendar';
import { useState } from 'react';
import * as StompJS from '@stomp/stompjs';
import { useSpace } from '@repository/space/useSpace';
import SocketJs from '@components/sockJs';

export default function Home() {
  const [client, setClient] = useState<StompJS.Client>();
  const [selectDate, setSelectDate] = useState<Date>(new Date());
  const { data } = useSpace(selectDate);
  return (
    <>
      {/* <SocketJs client={client} setClient={setClient} /> */}
      <WeekCalender selectDate={selectDate} setSelectDate={setSelectDate} />
    </>
  );
}
