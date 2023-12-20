import { EnvelopeSimple, User } from '@phosphor-icons/react';
import { ReviewField } from './components/ReviewField';
import { MultiStepFields } from '../StepBody';

export function SendStep({ data }: MultiStepFields) {
  return (
    <div>
      <h1 className='text-3xl font-bold mt-4'>Confirme seus dados:</h1>
      <ReviewField Icon={User} fieldName='Nome:' fieldValue={data.nome} />
      <ReviewField
        Icon={EnvelopeSimple}
        fieldName='Email:'
        fieldValue={data.email}
      />

      {data.tipo === 'CONSULTOR' && (
        <>
          <h1>é um consultor</h1>
          {JSON.stringify(data)}
        </>
      )}
    </div>
  );
}
