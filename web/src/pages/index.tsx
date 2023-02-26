import WeekCalender from '@components/weekCalendar';
import { useState } from 'react';
import { useSpace } from '@repository/space/useSpace';
import SocketJs from '@components/sockJs';

export default function Home() {
  const [selectDate, setSelectDate] = useState<Date>(new Date());
  const { data: spaceData } = useSpace(selectDate);

  return (
    <>
      <SocketJs spaceData={spaceData} />
      <WeekCalender selectDate={selectDate} setSelectDate={setSelectDate} />
    </>
  );
}
