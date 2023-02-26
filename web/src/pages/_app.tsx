import '@/styles/globals.css';
import Header from '@components/header';
import type { AppProps } from 'next/app';
import { QueryClient, QueryClientProvider } from 'react-query';

export default function App({ Component, pageProps }: AppProps) {
  const queryClient = new QueryClient();

  return (
    <QueryClientProvider client={queryClient}>
      <Header />
      <Component {...pageProps} />
    </QueryClientProvider>
  );
}
