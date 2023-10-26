import { PropsWithChildren } from 'react';

type StepProps = {
  active: boolean;
  stepDescription: string;
};

export function Step({
  stepDescription,
  active = false,
  children,
}: PropsWithChildren<StepProps>) {
  return (
    <div className='flex items-center gap-2'>
      <span
        className={`h-8 w-8 rounded-md 
            flex items-center justify-center text-white font-bold text-lg
            ${active ? 'bg-orange-400' : 'bg-orange-200'}
            `}
      >
        {children}
      </span>
      <p>{stepDescription}</p>
    </div>
  );
}
