import { ReactNode } from 'react';

interface InputTextLayoutProps {
  children: ReactNode;
}

export function InputTextLayout({
  children,
}: InputTextLayoutProps): JSX.Element {
  return (
    <div className='flex gap-2 border-b-2 p-1 items-center shadow-md'>
      {children}
    </div>
  );
}
