import { RegisterFieldSet } from './components/RegisterFieldSet';
import { MultiStepFields } from '../StepBody';

export function RegisterStep({ data, updateFieldHandler }: MultiStepFields) {
  return (
    <>
      <h1 className='text-3xl mt-4 flex items-center gap-2'>
        Cadastro{' '}
        <span className='text-gray-600 font-light text-2xl'>
          &gt; {data.tipo === 'CLIENTE' ? 'Cliente' : 'Consultor'}
        </span>
      </h1>
      <RegisterFieldSet
        data={data}
        updateFieldHandler={updateFieldHandler}
        registerType={data.tipo}
      />
    </>
  );
}
