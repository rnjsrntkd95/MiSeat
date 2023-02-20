'use client';
import Image from 'next/image';
import { useEffect, useState } from 'react';
import { WeekData, WEEK_DATE } from '@constants/weekCalendar';
import styles from '@components/weekCalendar/weekCalendar.module.scss';
import classnames from 'classnames/bind';
const cx = classnames.bind(styles);

const DaysView = (weekData: WeekData[]) => {
  const dayEl = weekData.map((e) => {
    const isWeekend = ['토', '일'].includes(e.day);

    return (
      <div key={e.day} className={styles.dayWrap}>
        <div className={cx('title', { weekend: isWeekend })}>{e.day}</div>
        <div className={cx('date', { weekend: isWeekend })}>
          {e.date.getDate()}
        </div>
      </div>
    );
  });

  return dayEl;
};

const WeekCalender = () => {
  const [date, setDate] = useState<Date>(new Date());
  const [week, setWeek] = useState<WeekData[]>([]);
  const headerTitle = `${date.getFullYear()}.${date.getMonth() + 1}`;

  const makeWeek = (date: Date) => {
    let day = date.getDay();

    const week = WEEK_DATE.map((e, i) => {
      let newDate = new Date(date.valueOf() + 86400000 * (i - day));
      return { day: e, date: newDate };
    });

    return week;
  };

  const clickLeftArrow = () => {
    let newDate = new Date(date.valueOf() - 86400000 * 7);
    setDate(newDate);
  };
  const clickRightArrow = () => {
    let newDate = new Date(date.valueOf() + 86400000 * 7);
    setDate(newDate);
  };

  useEffect(() => {
    setWeek(makeWeek(date));
  }, [date]);

  return (
    <div className={styles.wrap}>
      <div className={styles.headerWrap}>
        <Image
          src='/img/weekCalendar/leftArrow.svg'
          alt='Left Arrow'
          className={styles.leftArrow}
          width={28}
          height={28}
          priority
          onClick={clickLeftArrow}
        />
        <div className={styles.headerTitle}>{headerTitle}</div>
        <Image
          src='/img/weekCalendar/rightArrow.svg'
          alt='Right Arrow'
          className={styles.rightArrow}
          width={28}
          height={28}
          priority
          onClick={clickRightArrow}
        />
      </div>
      <div className={styles.weekWrap}>{DaysView(week)}</div>
    </div>
  );
};

export default WeekCalender;
