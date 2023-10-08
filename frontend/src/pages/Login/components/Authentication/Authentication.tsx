import { TextInput } from '../../../../components';
import { Logo } from '../Logo/Logo';

import { Lock } from '@phosphor-icons/react/dist/ssr/Lock';
import { UserCircle } from '@phosphor-icons/react';

type AuthenticationProps = {
  handleCreateAccount: (
    value: React.SetStateAction<'login' | 'signup'>,
  ) => void;
};

export function Authentication({ handleCreateAccount }: AuthenticationProps) {
  return (
    <div className='p-4 mt-24'>
      <Logo />
      <form className='flex flex-col gap-8 mt-8'>
        <TextInput.layout>
          <TextInput.icon Icon={UserCircle} />
          <TextInput.inputText type='email' placeholder='Email' />
        </TextInput.layout>

        <TextInput.layout>
          <TextInput.icon Icon={Lock} />
          <TextInput.inputText type='password' placeholder='Password' />
        </TextInput.layout>

        <div className='flex items-center'>
          <button
            type='button'
            onClick={() => handleCreateAccount('signup')}
            className='text-orange-700 cursor-pointer text-sm'
          >
            Criar uma conta
          </button>
          <button
            className='w-[200px] h-10 rounded-full bg-orange-600 text-white 
                ml-auto'
          >
            Login
          </button>
        </div>
      </form>
    </div>
  );
}
