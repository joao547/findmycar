import { Laptop, User } from '@phosphor-icons/react';

export function Identification() {
  return (
    <>
      <h1 className='text-3xl font-bold mt-4'>Identificação</h1>

      <fieldset className='flex gap-4 mt-8'>
        <button
          type='button'
          className='w-1/2 h-60 flex items-center justify-center 
        border-4 border-gray-200 rounded-md shadow-lg'
        >
          <User className='w-40 h-40 text-gray-200' />
        </button>
        <button
          type='button'
          className='w-1/2 h-60 flex items-center justify-center 
        border-4 border-gray-200 rounded-md shadow-lg'
        >
          <Laptop className='w-40 h-40 text-gray-200' />
        </button>
      </fieldset>
    </>
  );
}
