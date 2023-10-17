import { PropsWithChildren } from 'react';

export function StepsWrapper({ children }: PropsWithChildren) {
  return (
    <>
      <div className='flex gap-10 mt-12'>{children}</div>
      <hr className='mt-6' />
    </>
  );
}
