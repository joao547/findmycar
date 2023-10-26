import {
  CurrencyCircleDollar,
  EnvelopeSimple,
  MapPin,
  Toolbox,
  User,
} from '@phosphor-icons/react';
import { MultiStepFields } from '../StepBody';

export function SendStep({ data }: MultiStepFields) {
  return (
    <div>
      <h1 className='text-3xl font-bold mt-4'>Confirme seus dados:</h1>
      <div
        className='w-full border-b-2 border-gray-300 flex gap-1 
                   mt-4 h-10 items-center shadow-md p-4'
      >
        <User size={32} />
        <p className='font-semibold'>Nome:</p>
        <span className='font-light'>{data.name}</span>
      </div>
      <div
        className='w-full border-b-2 border-gray-300 flex gap-1 
                   mt-4 h-10 items-center shadow-md p-4'
      >
        <EnvelopeSimple size={32} />
        <p className='font-semibold'>Email:</p>
        <span className='font-light'>{data.email}</span>
      </div>
      {data.tipo === 'CONSULTOR' && (
        <>
          <div
            className='w-full border-b-2 border-gray-300 flex gap-1 
                   mt-4 h-10 items-center shadow-md p-4'
          >
            <Toolbox size={32} />
            <p className='font-semibold'>Area de atuação:</p>
            <span className='font-light'>{data.areaDeAtuacao}</span>
          </div>

          <div
            className='w-full border-b-2 border-gray-300 flex gap-1 
                   mt-4 h-10 items-center shadow-md p-4'
          >
            <MapPin size={32} />
            <p className='font-semibold'>Local de Atuação:</p>
            <span className='font-light'>{data.disponibilidade}</span>
          </div>

          <div
            className='w-full border-b-2 border-gray-300 flex gap-1 
                   mt-4 h-10 items-center shadow-md p-4'
          >
            <CurrencyCircleDollar size={32} />
            <p className='font-semibold'>Preço:</p>
            <span className='font-light'>${data.precoDoServico}</span>
          </div>
        </>
      )}
    </div>
  );
}
