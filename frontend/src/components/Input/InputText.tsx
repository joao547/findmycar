import { ComponentProps } from 'react';

type InputTextProps = ComponentProps<'input'>;

export function InputText({ ...props }: InputTextProps): JSX.Element {
  return <input {...props} className='w-full outline-none' />;
}
