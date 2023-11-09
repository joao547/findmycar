import {
  CurrencyCircleDollar,
  EnvelopeSimple,
  MapPin,
  Toolbox,
  User,
} from '@phosphor-icons/react';
import { ReviewField } from './components/ReviewField';
import { MultiStepFields } from '../StepBody';

export function SendStep({ data }: MultiStepFields) {
  return (
    <div>
      <h1 className='text-3xl font-bold mt-4'>Confirme seus dados:</h1>
      <ReviewField Icon={User} fieldName='Nome:' fieldValue={data.name} />
      <ReviewField
        Icon={EnvelopeSimple}
        fieldName='Email:'
        fieldValue={data.email}
      />

      {data.tipo === 'CONSULTOR' && (
        <>
          <ReviewField
            Icon={Toolbox}
            fieldName='Area de atuação:'
            fieldValue={data.areaDeAtuacao}
          />

          <ReviewField
            Icon={MapPin}
            fieldName='Local de Atuação:'
            fieldValue={data.disponibilidade}
          />

          <ReviewField
            Icon={CurrencyCircleDollar}
            fieldName='Preço:'
            fieldValue={`$${data.precoDoServico}`}
          />
        </>
      )}
    </div>
  );
}
