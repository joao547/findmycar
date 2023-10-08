import { Aside } from './components/Aside/Aside';
import { Logo } from './components/Logo/Logo';
import { TextInput } from '../../components';

import { Lock } from '@phosphor-icons/react/dist/ssr/Lock';
import { UserCircle } from '@phosphor-icons/react';

export default function LoginPage() {
  return (
    <div className='flex h-screen gap-4'>
      <Aside />
      <main className='flex-1 flex flex-col items-center'>
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
              <span className='text-orange-700 cursor-pointer text-sm'>
                Criar uma conta
              </span>
              <button
                className='w-[200px] h-10 rounded-full bg-orange-600 text-white 
                ml-auto'
              >
                Login
              </button>
            </div>
          </form>
        </div>
      </main>
    </div>
  );
}
