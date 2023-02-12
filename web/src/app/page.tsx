import Image from 'next/image';
import { Inter } from '@next/font/google';
import styles from '@styles/page.module.css';
import Calendar from '@/components/calendar';
import Reservation from '@components/reservation';
import SocketJs from '@components/sockJs';

const inter = Inter({ subsets: ['cyrillic-ext'] });

export default function Home() {
  return (
    <main className={styles.main}>
      <Calendar isView={true} />
      <SocketJs />
      <Reservation />
    </main>
  );
}
