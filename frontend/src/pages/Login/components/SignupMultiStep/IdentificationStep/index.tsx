import { IdentificationBadge, User } from '@phosphor-icons/react';
import { IdentificationCheckBox } from './components/IdentificationCheckBox';

export function IdentificationStep() {
  return (
    <>
      <h1 className='text-3xl font-bold mt-4'>Identificação</h1>
      <fieldset className='grid grid-cols-2 gap-4 h-full mt-4'>
        <IdentificationCheckBox
          Icon={User}
          label='Cliente'
          description='Encontre consultores ao redor do Brasil!'
        />

        <IdentificationCheckBox
          Icon={IdentificationBadge}
          label='Consultor'
          description='Consultor especializado em carros!'
        />
      </fieldset>
    </>
  );
}
