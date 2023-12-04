import { MagnifyingGlass } from '@phosphor-icons/react';

export function HomePage() {
  return (
    <div>
      <section className='flex items-center justify-center'>
        <div className='bg-white h-20 w-full rounded-md flex items-center justify-between p-4'>
          <div className='flex gap-4 w-full'>
            <MagnifyingGlass size={32} />
            <input
              type='text'
              placeholder='Buscar por nome'
              className='w-full outline-none'
            />
          </div>
          <button className='bg-orange-500 h-full w-40 rounded-md'>
            Buscar
          </button>
        </div>
      </section>
      <section className='mt-4 flex gap-10'>
        <div>
          <p>Sort By:</p>
          <input type='text' />
        </div>

        <div>
          <p>Type:</p>
          <input type='text' />
        </div>

        <div>
          <p>Sort By:</p>
          <input type='text' />
        </div>

        <div>
          <p>Tipo de Serviço:</p>
          <select name='' id=''>
            <option value='1'>consultoria mecanica</option>
            <option value=''>consultoria veiculo</option>
          </select>
        </div>
      </section>
      <hr className='mt-4' />
      <main className='mt-4 flex justify-between flex-wrap'>
        <ul className='flex w-full justify-between'>
          <li>
            <div className='w-60 h-60 bg-gray-500 rounded-md'></div>
          </li>
          <li>
            <div className='w-60 h-60 bg-gray-500 rounded-md'></div>
          </li>
          <li>
            <div className='w-60 h-60 bg-gray-500 rounded-md'></div>
          </li>
          <li>
            <div className='w-60 h-60 bg-gray-500 rounded-md'></div>
          </li>
          <li>
            <div className='w-60 h-60 bg-gray-500 rounded-md'></div>
          </li>
        </ul>
      </main>
    </div>
  );
}
