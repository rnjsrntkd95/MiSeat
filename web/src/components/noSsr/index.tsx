'use client';
import dynamic from 'next/dynamic';
import { FC, ReactNode } from 'react';

interface WrapperProps {
  children: ReactNode;
}

const Wrapper: FC<WrapperProps> = ({ children }) => <>{children}</>;

const NoSsr = dynamic(() => Promise.resolve(Wrapper), {
  ssr: false,
});

export default NoSsr;
