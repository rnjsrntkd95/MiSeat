import Image from 'next/image';
import Link from 'next/link';
import styles from '@components/header/header.module.scss';
import React, { useState } from 'react';
import LoginModal from '@components/loginModal';

const Header = () => {
  const [visibleLoginModal, setVisibleLoginModal] = useState(false);
  const onClick = (e: React.MouseEvent<HTMLDivElement, MouseEvent>) => {
    e.stopPropagation();
    setVisibleLoginModal(true);
  };
  const onClose = () => setVisibleLoginModal(false);

  return (
    <>
      <header className={styles.header}>
        <Link href="/">
          <Image
            src="/img/header/logo.svg"
            alt="13"
            width={40}
            height={40}
            priority
          />
        </Link>
        <div className={styles.login} onClick={onClick}>
          Login
        </div>
        {visibleLoginModal && <LoginModal onClose={onClose} />}
      </header>
    </>
  );
};

export default Header;
