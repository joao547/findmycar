import { TextInput } from '../../../../components';
import { Logo } from './components/Logo/Logo';
import useAuth from '../../data/useAuth';
import { useNavigate } from 'react-router-dom';

import { Lock } from '@phosphor-icons/react/dist/ssr/Lock';
import { UserCircle } from '@phosphor-icons/react';
import { FormEvent } from 'react';
import { toast } from 'react-toastify';

type AuthenticationProps = {
  handleCreateAccount: (
    value: React.SetStateAction<'login' | 'signup'>,
  ) => void;
};

export function Authentication({ handleCreateAccount }: AuthenticationProps) {
  const navigate = useNavigate();
  const { handleLogin } = useAuth();

  async function onLoginAction(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();

    const formData = new FormData(event.currentTarget);
    const email = formData.get('email')?.toString() || '';
    const password = formData.get('password')?.toString() || '';

    const { token } = await toast.promise(handleLogin(email, password), {
      success: 'Login successful',
      pending: 'loading...',
      error: 'Login failed',
    });

    if (token) {
      localStorage.setItem('@token', token);
      navigate('/home');
    }
  }
  return (
    <div className='p-4 mt-24'>
      <Logo />
      <form className='flex flex-col gap-8 mt-8' onSubmit={onLoginAction}>
        <TextInput.layout>
          <TextInput.icon Icon={UserCircle} />
          <TextInput.inputText name='email' type='email' placeholder='Email' />
        </TextInput.layout>

        <TextInput.layout>
          <TextInput.icon Icon={Lock} />
          <TextInput.inputText
            name='password'
            type='password'
            placeholder='Password'
          />
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
