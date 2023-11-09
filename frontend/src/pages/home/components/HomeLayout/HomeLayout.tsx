import { PropsWithChildren } from 'react';
import { HomeAside } from '../HomeAside/HomeAside';
import { Bell, EnvelopeSimple } from '@phosphor-icons/react';
import { useLocation } from 'react-router-dom';

export function HomeLayout({ children }: PropsWithChildren) {
  const location = useLocation();
  const currentRouteName = location.pathname;

  return (
    <div className='w-full h-full flex'>
      <HomeAside />
      <div className='w-full grid grid-rows-[100px,1fr] bg-gray-200'>
        <header className='h-ful flex items-center p-4 justify-between'>
          <h1 className='font-semibold text-2xl'>{currentRouteName}</h1>
          <div className='flex items-center gap-8'>
            <button>
              <EnvelopeSimple size={32} />
            </button>
            <button>
              <Bell size={32} />
            </button>

            <div className='w-16 h-16 inline-block rounded-full overflow-hidden'>
              <img
                src='https://github.com/IgorRS-2302.png'
                alt=''
                className='w-full h-full object-cover rounded-full'
              />
            </div>
          </div>
        </header>
        <main className='p-4'>{children}</main>
      </div>
    </div>
  );
}
