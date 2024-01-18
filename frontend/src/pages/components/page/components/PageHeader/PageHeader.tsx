import { Bell, EnvelopeSimple } from '@phosphor-icons/react';
import { useHome } from '../../data/useHome';
import { ImagemBase64 } from './components/ImagemBase64';
import { useEffect, useState } from 'react';
import { api } from '../../../../../service/api';

export function PageHeader() {
  const { currentRouteName } = useHome();
  const [base64String, setBase64String] = useState<string | null>(null);

  useEffect(() => {
    (async () => {
      const token = localStorage.getItem('@token');

      const { data } = await api.get('/api/user/me', {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });

      setBase64String(data.imagemBase64);
    })();
  }, []);

  return (
    <header className='h-ful flex items-center p-4 justify-between'>
      <h1 className='font-semibold text-2xl'>{currentRouteName}</h1>
      <div className='flex items-center gap-8'>
        <button className='shadow-md p-2 bg-slate-50 rounded-full'>
          <EnvelopeSimple size={32} />
        </button>
        <button className='shadow-md p-2 bg-slate-50 rounded-full'>
          <Bell size={32} />
        </button>

        <ImagemBase64 base64String={base64String as string} />
      </div>
    </header>
  );
}
