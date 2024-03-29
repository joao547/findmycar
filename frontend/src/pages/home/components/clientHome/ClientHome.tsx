/* eslint-disable @typescript-eslint/no-explicit-any */
import { MagnifyingGlass, MapPin } from '@phosphor-icons/react';
import { useEffect, useRef, useState } from 'react';
import Select from 'react-select';
import { GeneralOption } from '../../../Login/data/signupMultiStepSelectOptions';
import ToggleSwitch from '../ToggleSwitch';
import ConsultantProfile from '../ConsultantProfile';
import useSelectData from '../../../Login/data/useRegisterSelectData';
import { api } from '../../../../service/api';
import { ufOptions } from '../../../../helpers/selectOptions';

type ConsultantService = {
  id: number;
  nome: string;
};

export type Consultant = {
  idConsultor: number;
  nome: string;
  localidades: Array<{
    id: number;
    ibgeCode: number;
    uf: string;
    name: string;
  }>;
  servicosBuscador: Array<ConsultantService> | null;
  carroMarcas: Array<ConsultantService> | null;
  mediaNota: number;
  precoServico: number;
  fotoBase64: string;
  localidade: Array<{
    id: number;
    ibgeCode: number;
    uf: string;
    name: string;
  }>;
};
type QueryParams = {
  tipoConsultor: string;
  servicoBuscador: string;
  locaisAtuacao: string;
  carroMarcas: string;
};

function removeUndefinedProps(obj: any): any {
  const newObj: any = {};
  for (const key in obj) {
    if (obj[key] !== undefined) {
      newObj[key] = obj[key];
    }
  }
  return newObj;
}

export function ClientHome() {
  const { servicosBuscador, carroMarcas } = useSelectData();
  const [consultants, setConsultants] = useState<Consultant[]>([]);

  const [isChecked, setIsChecked] = useState(false);

  const ufSelectRef = useRef<any>(null);
  const selectCarroModelo = useRef<any>(null);
  const selectBuscador = useRef<any>(null);

  function handleToggle() {
    if (selectBuscador.current) {
      selectBuscador.current.clearValue();
    }

    setIsChecked(!isChecked);
  }

  function handlerClearFilter() {
    setIsChecked(false);

    if (selectBuscador.current) {
      selectBuscador.current.clearValue();
    }

    if (ufSelectRef.current) {
      ufSelectRef.current.clearValue();
    }

    if (selectCarroModelo.current) {
      selectCarroModelo.current.clearValue();
    }

    setConsultants([]);
  }

  async function handlerSearch() {
    const token = localStorage.getItem('@token');

    const searchStates = ufSelectRef.current
      .getValue()
      .map((opt: GeneralOption) => opt.value);

    const buscadorParams = selectBuscador.current
      .getValue()
      .map((opt: GeneralOption) => opt.value);

    const carroMarcasParams = selectCarroModelo.current
      .getValue()
      .map((opt: GeneralOption) => opt.value);

    const queryParams: QueryParams = {
      tipoConsultor: isChecked ? 'mecanico' : 'buscador',
      servicoBuscador:
        buscadorParams.length > 0 ? buscadorParams.join(',') : undefined,
      locaisAtuacao:
        searchStates.length > 0 ? searchStates.join(',') : undefined,
      carroMarcas:
        carroMarcasParams.length > 0 ? carroMarcasParams.join(',') : undefined,
    };

    try {
      const { data } = await api.get('/api/consultor/buscar', {
        headers: {
          Authorization: `Bearer ${token}`,
        },
        params: removeUndefinedProps(queryParams),
      });

      setConsultants(data);
    } catch (error) {
      console.log(error);
    }
  }
  return (
    <div>
      <section className='flex items-center justify-center'>
        <div className='bg-white h-20 w-full rounded-md flex items-center p-4 gap-4'>
          <div className='w-full'>
            <Select
              id='two'
              options={servicosBuscador}
              placeholder='Tipo de consultoria'
              ref={selectBuscador}
              isMulti
              isDisabled={isChecked}
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
                isDisabled={!isChecked}
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
            options={carroMarcas}
            placeholder='Tipo de consultoria'
            ref={selectCarroModelo}
            isMulti
            isDisabled={!isChecked}
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

        <ul className='grid grid-cols-4 gap-4'>
          {consultants.length > 0 &&
            consultants.map((consultant) => (
              <ConsultantProfile
                key={consultant.idConsultor}
                consultant={consultant}
                isMecanicalConsultant={!isChecked}
              />
            ))}
        </ul>
      </div>
    </div>
  );
}
