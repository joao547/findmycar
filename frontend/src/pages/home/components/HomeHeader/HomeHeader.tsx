import { Bell, EnvelopeSimple } from '@phosphor-icons/react';
import { useHome } from '../../data/useHome';

export function HomeHeader() {
  const { currentRouteName } = useHome();
  return (
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
  );
}
