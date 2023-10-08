import logoImg from '../../../../assets/findmycar_logo.png';

export function Logo() {
  return (
    <header className='w-full flex items-center justify-center'>
      <img src={logoImg} alt='logo img' className='w-[26.25rem] h-[18.75rem]' />
    </header>
  );
}
