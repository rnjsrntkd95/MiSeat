'use client';
import { useEffect, useState } from 'react';
import RCalendar from 'react-calendar';
import 'react-calendar/dist/Calendar.css';
import '@components/calendar/calendarCustom.scss';
import styles from '@components/calendar/calendar.module.scss';

const Calendar = () => {
  const [isRender, setIsRender] = useState<boolean>(false);
  useEffect(() => {
    setIsRender(true);
  }, []);

  /**
   * 서버사이드에서 pre-rendering된 React 트리와
   * 브라우저에서 처음 rendering되는 React 트리가 달랐기 때문에 발생한 에러
   */

  if (!isRender) return null;

  return (
    <div className={styles.wrap}>
      <RCalendar />
    </div>
  );
};

export default Calendar;
