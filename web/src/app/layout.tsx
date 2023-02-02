import Header from '@/components/header';
import '@styles/globals.css';

export default function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <html lang='ko'>
      <head />
      <body>
        <Header />
        {children}
      </body>
    </html>
  );
}
