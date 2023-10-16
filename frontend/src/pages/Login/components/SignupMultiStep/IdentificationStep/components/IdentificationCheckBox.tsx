import { Check, Icon } from '@phosphor-icons/react';
import * as Checkbox from '@radix-ui/react-checkbox';

type IdentificationCheckBoxProps = {
  label: string;
  description: string;
  Icon: Icon;
};

export function IdentificationCheckBox({
  label,
  description,
  Icon,
}: IdentificationCheckBoxProps) {
  return (
    <Checkbox.Root
      className='w-full h-full 
      border rounded-xl shadow-xl flex flex-col'
    >
      <div className='w-full h-4/6 flex flex-col items-center mt-10'>
        <Icon className='w-32 h-32 text-gray-400' />
        <h1 className='font-medium text-xl mt-4 text-center'>{label}</h1>
        <p className='text-center'>{description}</p>
      </div>
      <Checkbox.Indicator className='h-4 w-full bg-orange-500 mt-auto' />
    </Checkbox.Root>
  );
}
