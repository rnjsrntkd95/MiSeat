import Image from 'next/image';
import { FC, useEffect, useState } from 'react';
import { WeekData, WEEK_DATE } from '@constants/weekCalendar';
import styles from '@components/weekCalendar/weekCalendar.module.scss';
import classnames from 'classnames/bind';
import * as StompJS from '@stomp/stompjs';
const cx = classnames.bind(styles);

const DaysView = (
  weekData: WeekData[],
  selectDate: Date,
  setSelectDate: (date: Date) => void
) => {
  const dayEl = weekData.map((e) => {
    const isWeekend = ['토', '일'].includes(e.day);
    const isSelect = e.date.valueOf() === selectDate?.valueOf();
    const onDaySelect = () => setSelectDate(e.date);

    return (
      <div key={e.day} className={styles.dayWrap}>
        <div className={cx('title', { weekend: isWeekend })}>{e.day}</div>
        <div
          className={cx('date', { weekend: isWeekend }, { select: isSelect })}
          onClick={onDaySelect}
        >
          {e.date.getDate()}
        </div>
      </div>
    );
  });

  return dayEl;
};

interface WeekCalenderProps {
  client?: StompJS.Client;
  selectDate: Date;
  setSelectDate: (date: Date) => void;
}

const WeekCalender: FC<WeekCalenderProps> = ({
  client,
  selectDate,
  setSelectDate,
}) => {
  const [date, setDate] = useState<Date>(selectDate);
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
          src="/img/weekCalendar/leftArrow.svg"
          alt="Left Arrow"
          className={styles.leftArrow}
          width={28}
          height={28}
          priority
          onClick={clickLeftArrow}
        />
        <div className={styles.headerTitle}>{headerTitle}</div>
        <Image
          src="/img/weekCalendar/rightArrow.svg"
          alt="Right Arrow"
          className={styles.rightArrow}
          width={28}
          height={28}
          priority
          onClick={clickRightArrow}
        />
      </div>
      <div className={styles.weekWrap}>
        {DaysView(week, selectDate, setSelectDate)}
      </div>
    </div>
  );
};

export default WeekCalender;
