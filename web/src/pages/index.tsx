import SocketJs from '@components/sockJs';
import WeekCalender from '@components/weekCalendar';
import { useState } from 'react';
import * as StompJS from '@stomp/stompjs';

export default function Home() {
  const [client, setClient] = useState<StompJS.Client>();
  return (
    <>
      <SocketJs client={client} setClient={setClient} />
      <WeekCalender />
    </>
  );
}
