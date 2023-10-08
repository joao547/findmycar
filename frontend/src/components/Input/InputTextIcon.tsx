import { Icon } from '@phosphor-icons/react';

interface InputTextIconProps {
  Icon: Icon;
}

export function InputTextIcon({ Icon }: InputTextIconProps): JSX.Element {
  return <Icon className='w-10 h-10 text-gray-500' />;
}
