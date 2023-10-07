import { UserCircle, Lock } from '@phosphor-icons/react';

export default function LoginPage() {
  return (
    <main>
      <form className='w-[350px]'>
        <div className='flex gap-2 border-b-2 p-1 items-center'>
          <UserCircle size={42} color='' />
          <input type='text' placeholder='Email' className='w-full outline-none' />
        </div>
      </form>
    </main>
  );
}
