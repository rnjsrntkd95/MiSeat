'use client';
import { FC, useState } from 'react';
import {
  FieldArrayWithId,
  FormProvider,
  useController,
  useFieldArray,
  useForm,
  useFormContext,
} from 'react-hook-form';
import {
  reservationDefaultValue,
  ReservationForm,
} from '@constants/reservation';
import Calendar from '@components/calendar';

import styles from '@components/reservation/reservation.module.scss';

interface ReservationElProps {
  prefixName: string;
  field: FieldArrayWithId<ReservationForm, 'reservation', 'id'>;
  index: number;
}

const ReservationEl: FC<ReservationElProps> = ({
  prefixName,
  field,
  index,
}) => {
  const methods = useFormContext();
  const { control } = methods;
  const [isCalendarView, setIsCalendarView] = useState(false);

  const { field: dateField } = useController({
    name: `${prefixName}.date`,
    control,
    defaultValue: undefined,
  });
  const { field: seatNumField } = useController({
    name: `${prefixName}.seatNum`,
    control,
    defaultValue: undefined,
  });

  const daySelect = () => setIsCalendarView(false);
  return (
    <div className={styles.table} key={field.id}>
      <span>{index + 1}순위</span>
      <div className={styles.input_Wrap}>
        <div className={styles.date}>
          <span>날짜</span>
          <input
            {...dateField}
            onClick={() => setIsCalendarView((prev) => !prev)}
          />
          {isCalendarView && (
            <Calendar viewType={'RANGE'} daySelect={daySelect} />
          )}
        </div>
        <div className={styles.seatNum}>
          <span>좌석 번호</span>
          <input {...seatNumField} />
        </div>
      </div>
    </div>
  );
};
const Reservation = () => {
  const methods = useForm<ReservationForm>({
    mode: 'onChange',
    defaultValues: reservationDefaultValue,
  });

  const { handleSubmit, control } = methods;
  const onSubmit = (data: any) => console.log(data);

  const { fields } = useFieldArray({
    control,
    name: 'reservation',
  });

  const reservationEl = fields.map((field, index) => (
    <ReservationEl
      key={field.id}
      prefixName={`reservation[${index}]`}
      field={field}
      index={index}
    />
  ));

  return (
    <FormProvider {...methods}>
      <form onSubmit={handleSubmit(onSubmit)}>
        <div className={styles.wrap}>
          {reservationEl}
          <button type="submit">예약 신청</button>
        </div>
      </form>
    </FormProvider>
  );
};

export default Reservation;
