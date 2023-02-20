import Image from 'next/image';
import { Inter } from '@next/font/google';
import styles from '@styles/page.module.css';
import Calendar from '@/components/calendar';
import WeekCalender from '@components/weekCalendar';

const inter = Inter({ subsets: ['cyrillic-ext'] });

export default function Home() {
  return (
    <main className={styles.main}>
      <WeekCalender />
      {/* <Calendar /> */}
    </main>
  );
}
