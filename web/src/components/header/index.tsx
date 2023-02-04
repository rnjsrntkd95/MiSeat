import Image from 'next/image';
import Link from 'next/link';
import styles from '@styles/header.module.scss';

const Header = () => {
  return (
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
      <Link href="/login" className="">
        Login
      </Link>
    </header>
  );
};

export default Header;
