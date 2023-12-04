import { PropsWithChildren } from 'react';
import { PageAside } from '../PageAside/PageAside';
import { PageHeader } from '../PageHeader/PageHeader';

export function PageLayout({ children }: PropsWithChildren) {
  return (
    <div className='w-full h-full flex'>
      <PageAside />
      <div className='w-full grid grid-rows-[100px,1fr] bg-slate-100'>
        <PageHeader />
        <main className='p-4'>{children}</main>
      </div>
    </div>
  );
}
