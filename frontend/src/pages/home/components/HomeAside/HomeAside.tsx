import logoImg from '../../../../assets/findmycar_logo.png';
import {
  ChatCircleText,
  House,
  SignOut,
  UserCircleGear,
} from '@phosphor-icons/react';
import { NavLink } from 'react-router-dom';

export function HomeAside() {
  return (
    <aside className='bg-white w-[18rem] h-screen flex flex-col items-center p-4'>
      <img src={logoImg} alt='logo' className='w-28' />
      <nav className='mt-16 w-full'>
        <ul className='w-full'>
          <li className='flex items-center justify-center'>
            <NavLink
              to='/home/dashboard'
              className='flex gap-6 items-center text-orange-500 font-semibold h-24'
            >
              <House className='w-8 h-8 text-orange-500' /> Home
            </NavLink>
          </li>
          <li className='flex items-center justify-center'>
            <NavLink
              to=''
              className='flex gap-6 items-center text-gray-500 font-semibold h-24'
            >
              <ChatCircleText className='w-8 h-8 text-gray-500' /> Chat
            </NavLink>
          </li>
          <li className='flex items-center justify-center'>
            <NavLink
              to='/home/profile'
              className='flex gap-6 items-center text-gray-500 font-semibold h-24'
            >
              <UserCircleGear className='w-8 h-8 text-gray-500' /> Perfil
            </NavLink>
          </li>
          <li className='flex items-center justify-center'>
            <NavLink
              to='/'
              className='flex gap-6 items-center text-gray-500 font-semibold h-24'
            >
              <SignOut className='w-8 h-8 text-gray-500' /> Sair
            </NavLink>
          </li>
        </ul>
      </nav>
    </aside>
  );
}
