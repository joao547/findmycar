import * as RadioGroup from '@radix-ui/react-radio-group';
import { User } from '@phosphor-icons/react';

type RadioItemProps = {
  title: string;
  description: string;
  itemValue: string;
};
export function RadioItem({ description, title, itemValue }: RadioItemProps) {
  return (
    <RadioGroup.Item value={itemValue}>
      <div
        className='w-full h-full 
          border rounded-xl shadow-xl flex flex-col'
      >
        <div className='w-full h-[18.75rem] flex flex-col items-center mt-10 p-4'>
          <User className='w-32 h-32 text-gray-400' />
          <h1 className='font-medium text-xl mt-4 text-center'>{title}</h1>
          <p className='text-center font-light mt-4'>{description}</p>
        </div>
        <RadioGroup.Indicator className='h-4 w-full bg-orange-500 mt-auto' />
      </div>
    </RadioGroup.Item>
  );
}
