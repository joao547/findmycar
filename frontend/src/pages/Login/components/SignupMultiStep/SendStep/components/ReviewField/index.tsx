import { Icon } from '@phosphor-icons/react';

type ReviewFieldProps = {
  fieldName: string;
  fieldValue: string;
  Icon: Icon;
};

export function ReviewField({ Icon, fieldName, fieldValue }: ReviewFieldProps) {
  return (
    <>
      <div
        className='w-full border-b-2 border-gray-300 flex gap-1 
                     mt-4 h-10 items-center shadow-md p-4'
      >
        <Icon size={32} />
        <p className='font-semibold'>{fieldName}</p>
        <span className='font-light'>{fieldValue}</span>
      </div>
    </>
  );
}
