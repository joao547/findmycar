import { MultiStepFields } from '../StepBody';
import { IdentificationRadioGroup } from './components/IdentificationRadioGroup';

export function IdentificationStep({ updateFieldHandler }: MultiStepFields) {
  return (
    <>
      <h1 className='text-3xl font-bold mt-4'>Identificação</h1>
      <IdentificationRadioGroup updateFieldHandler={updateFieldHandler} />
    </>
  );
}
