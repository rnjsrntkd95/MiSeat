'use client';
import { FC, useEffect, useState } from 'react';
import RCalendar from 'react-calendar';
import 'react-calendar/dist/Calendar.css';
import '@components/calendar/calendarCustom.scss';
import styles from '@components/calendar/calendar.module.scss';
import NoSsr from '@components/noSsr';

type ViewType = 'WHOLE' | 'RANGE';

interface CalendarViewProps {
  viewType?: ViewType;
  isView?: boolean;
  daySelect?: () => void;
}

const Calendar: FC<CalendarViewProps> = ({ viewType, isView, daySelect }) => {
  let calenderProps;
  if (viewType)
    calenderProps = {
      onClickDay: daySelect,
    };

  /**
   * TODO:
   * formatDay로 주간 달력 만들자 */
  return (
    <NoSsr>
      <div className={styles.wrap}>
        <RCalendar
          onChange={(e) => console.log(e)}
          activeStartDate={new Date(2022, 1, 31)}
          formatDay={(locale, date) => {
            console.log(locale, date);
            return '';
          }}
          {...calenderProps}
        />
      </div>
    </NoSsr>
  );
};

export default Calendar;
