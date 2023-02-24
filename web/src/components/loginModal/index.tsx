'use client';
import Link from 'next/link';
import { FC, useEffect, useRef } from 'react';
import { useForm, FormProvider } from 'react-hook-form';
import styles from '@components/loginModal/loginModal.module.scss';
import useClickAway from '@utils/hook/useClickAway';

interface LoginModalProps {
  onClose: () => void;
}

const LoginModal: FC<LoginModalProps> = ({ onClose }) => {
  const methods = useForm();
  const { handleSubmit } = methods;
  const onSubmit = (data: any) => console.log(data);
  const loginModalEl = useRef(null);
  useClickAway(onClose, loginModalEl);

  return (
    <div className={styles.layer}>
      <FormProvider {...methods}>
        <form onSubmit={handleSubmit(onSubmit)}>
          <div className={styles.loginModal} ref={loginModalEl}>
            <input className={styles.id} placeholder='id' />
            <input className={styles.password} placeholder='password' />
            <button className={styles.loginBtn} type='submit'>
              로그인
            </button>
            <Link className={styles.joinLink} href='/join'>
              <button className={styles.joinBtn}>회원가입</button>
            </Link>
          </div>
        </form>
      </FormProvider>
    </div>
  );
};

export default LoginModal;
