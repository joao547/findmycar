/* eslint-disable max-len */
import { MagnifyingGlass, MapPin } from '@phosphor-icons/react';
import Select, { SingleValue } from 'react-select';
import { optionsBuscador, ufOptions } from '../../helpers/selectOptions';
import ToggleSwitch from './components/ToggleSwitch';

export function HomePage() {
  return (
    <div>
      <section className='flex items-center justify-center'>
        <div className='bg-white h-20 w-full rounded-md flex items-center p-4 gap-4'>
          <div className='flex gap-4 w-full'>
            <MagnifyingGlass size={32} />
            <input
              type='text'
              placeholder='Buscar por nome'
              className='w-full outline-none'
            />
          </div>
          <div className='w-1 h-full bg-gray-500' />
          <div className='flex gap-4 w-full'>
            <MapPin size={32} />
            <div className='w-full'>
              <Select options={ufOptions} placeholder='Local de atuação' />
            </div>
          </div>

          <button className='bg-orange-500 h-full w-40 rounded-md'>
            Buscar
          </button>
        </div>
      </section>
      <section className='mt-4 flex items-center gap-6'>
        <ToggleSwitch />
        <div className=''>
          <Select
            options={optionsBuscador}
            placeholder='Tipo de consultoria'
            isMulti
          />
        </div>
        <button className='text-sm text-gray-500'>Limpar Filtros</button>
      </section>
    </div>
  );
}
