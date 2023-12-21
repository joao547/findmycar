import { ReactNode } from 'react';

interface InputTextLayoutProps {
  children: ReactNode;
  hasError?: boolean;
}

export function InputTextLayout({
  children,
  hasError = false,
}: InputTextLayoutProps): JSX.Element {
  return (
    <div
      className={`
				flex gap-2 border-2 mr-3 py-3 px-2 
				items-center text-gray-500 rounded-lg
				${hasError ? 'border-red-500' : 'border-gray-200'}
		`}
    >
      {children}
    </div>
  );
}
