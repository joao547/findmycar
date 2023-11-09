import { PropsWithChildren } from 'react';
import { HomeAside } from '../HomeAside/HomeAside';
import { HomeHeader } from '../HomeHeader/HomeHeader';
import { Bell, EnvelopeSimple } from '@phosphor-icons/react';
import { useLocation } from 'react-router-dom';

export function HomeLayout({ children }: PropsWithChildren) {
  const location = useLocation();
  const currentRouteName = location.pathname;

  return (
    <div className='w-full h-full flex'>
      <HomeAside />
      <div className='w-full grid grid-rows-[100px,1fr] bg-gray-200'>
        <HomeHeader currentRouteName={currentRouteName} />
        <main className='p-4'>{children}</main>
      </div>
    </div>
  );
}
