import { PropsWithChildren } from 'react';
import { HomeAside } from '../HomeAside/HomeAside';
import { HomeHeader } from '../HomeHeader/HomeHeader';

export function HomeLayout({ children }: PropsWithChildren) {
  return (
    <div className='w-full h-full flex'>
      <HomeAside />
      <div className='w-full grid grid-rows-[100px,1fr] bg-slate-100'>
        <HomeHeader />
        <main className='p-4'>{children}</main>
      </div>
    </div>
  );
}
