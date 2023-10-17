import { ReactNode } from 'react';

interface InputTextLayoutProps {
  children: ReactNode;
}

export function InputTextLayout({
  children,
}: InputTextLayoutProps): JSX.Element {
  return (
    <div className='flex gap-2 border rounded-xl p-1 items-center shadow-xl'>
      {children}
    </div>
  );
}
