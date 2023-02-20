'use client';
import { Inter } from '@next/font/google';
import styles from '@styles/page.module.css';
import WeekCalender from '@components/weekCalendar';
import SocketJs from '@components/sockJs';
import { useRef, useState } from 'react';
import * as StompJS from '@stomp/stompjs';

const inter = Inter({ subsets: ['cyrillic-ext'] });

export default function Home() {
  const [client, setClient] = useState<StompJS.Client>();

  return (
    <main className={styles.main}>
      <SocketJs client={client} setClient={setClient} />
      <WeekCalender client={client} />
      {/* <Calendar /> */}
    </main>
  );
}
