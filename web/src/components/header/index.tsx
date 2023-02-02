import Link from 'next/link';
import styles from '@styles/header.module.scss';

const Header = () => {
  return (
    <header className={styles.header}>
      <div className={styles.logo}>LOGO</div>
      <div className={styles.login_Wrap}>
        <Link href='/login' className=''>
          Login
        </Link>
      </div>
    </header>
  );
};

export default Header;
