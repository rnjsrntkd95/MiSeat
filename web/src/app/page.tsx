import Image from 'next/image';
import { Inter } from '@next/font/google';
import styles from '@styles/page.module.css';
import Calendar from '@/components/calendar';

const inter = Inter({ subsets: ['latin'] });

export default function Home() {
  return (
    <main className={styles.main}>
      <Calendar />
    </main>
  );
}
