/* eslint-disable @typescript-eslint/no-explicit-any */
import { MagnifyingGlass, MapPin } from '@phosphor-icons/react';
import Select from 'react-select';
import { optionsBuscador, ufOptions } from '../../helpers/selectOptions';
import ToggleSwitch from './components/ToggleSwitch';
import ConsultantProfile from './components/ConsultantProfile';
import { useRef, useState } from 'react';

interface Option {
  label: string;
  value: string;
}

export function HomePage() {
  const [consultants, setConsultants] = useState<
    Array<{
      consultantName: string;
      consultantCities: string;
      consultantArea: string;
    }>
  >([]);

  const [isChecked, setIsChecked] = useState(false);
  const ufSelectRef = useRef<any>(null);
  const typeSelect = useRef<any>(null);

  function handleToggle() {
    setIsChecked(!isChecked);
  }

  function handlerClearFilter() {
    setIsChecked(false);

    if (ufSelectRef.current) {
      ufSelectRef.current.clearValue();
    }

    if (typeSelect.current) {
      typeSelect.current.clearValue();
    }

    setConsultants([]);
  }

  function handlerSearch() {
    const searchStates = ufSelectRef.current
      .getValue()
      .map((opt: Option) => opt.value);

    const consultantType = typeSelect.current
      .getValue()
      .map((opt: Option) => opt.value);

    console.log({
      query: { isChecked, state: searchStates, type: consultantType },
    });

    const data = [
      {
        consultantName: 'lucas',
        consultantCities: 'Recife, Pernambuco',
        consultantArea:
          'carros antigos, carros comuns, carros economicos, carros raros',
      },
      {
        consultantName: 'Luiz Marcel',
        consultantCities: 'Recife, Pernambuco',
        consultantArea:
          'carros antigos, carros comuns, carros economicos, carros raros',
      },
      {
        consultantName: 'Renan Pryston',
        consultantCities: 'Recife, Pernambuco',
        consultantArea:
          'carros antigos, carros comuns, carros economicos, carros raros',
      },
      {
        consultantName: 'Joao Vitor',
        consultantCities: 'Recife, Pernambuco',
        consultantArea:
          'carros antigos, carros comuns, carros economicos, carros raros',
      },
    ];

    setConsultants(data);
  }
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
              <Select
                id='one'
                options={ufOptions}
                placeholder='Local de atuação'
                ref={ufSelectRef}
              />
            </div>
          </div>

          <button
            className='bg-orange-500 h-full w-40 rounded-md text-white'
            onClick={handlerSearch}
          >
            Buscar
          </button>
        </div>
      </section>
      <section className='mt-4 flex items-center gap-6'>
        <ToggleSwitch isChecked={isChecked}>
          <input type='checkbox' className='hidden' onChange={handleToggle} />
        </ToggleSwitch>
        <div className=''>
          <Select
            id='two'
            options={optionsBuscador}
            placeholder='Tipo de consultoria'
            ref={typeSelect}
            isMulti
          />
        </div>
        <button className='text-sm text-gray-500' onClick={handlerClearFilter}>
          Limpar Filtros
        </button>
      </section>

      <div className='mt-8'>
        <span className='font-medium'>
          {consultants.length > 0 ? consultants.length : 'Nenhum'} consultor
          disponíveis
        </span>
        <ul className='grid grid-cols-4 gap-y-4 '>
          {consultants.length > 0 &&
            consultants.map((consultant) => (
              <ConsultantProfile
                key={consultant.consultantName}
                consultantName={consultant.consultantName}
                consultantArea={consultant.consultantArea}
                consultantCities={consultant.consultantCities}
              />
            ))}
        </ul>
      </div>
    </div>
  );
}
