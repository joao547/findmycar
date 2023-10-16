import {
  BracketsAngle,
  EnvelopeSimple,
  Lock,
  User,
} from '@phosphor-icons/react';
import { TextInput } from '../../../../../components';
import { MultiStepFields } from '../StepBody';

export function RegisterStep({ data, updateFieldHandler }: MultiStepFields) {
  if (data.type === 'consultant') {
    return (
      <>
        <h1 className='text-3xl mt-4 flex items-center gap-2'>
          Cadastro{' '}
          <span className='text-gray-600 font-light text-2xl'>
            &gt; Consultor
          </span>
        </h1>
        <fieldset className='flex flex-col gap-4 mt-8' />
      </>
    );
  }
  return (
    <>
      <h1 className='text-3xl mt-4 flex items-center gap-2'>
        Cadastro{' '}
        <span className='text-gray-600 font-light text-2xl'>&gt; Cliente</span>
      </h1>
      <fieldset className='flex flex-col gap-4 mt-8'>
        <TextInput.layout>
          <TextInput.icon Icon={User} />
          <TextInput.inputText
            type='text'
            placeholder='Seu nome'
            name='name'
            id='name'
            value={data.name}
            onChange={(e) => updateFieldHandler('name', e.target.value)}
          />
        </TextInput.layout>

        <TextInput.layout>
          <TextInput.icon Icon={EnvelopeSimple} />
          <TextInput.inputText
            type='Email'
            placeholder='Seu Email'
            name='email'
            id='email'
            value={data.email}
            onChange={(e) => updateFieldHandler('email', e.target.value)}
          />
        </TextInput.layout>

        <TextInput.layout>
          <TextInput.icon Icon={BracketsAngle} />
          <TextInput.inputText
            type='text'
            placeholder='Seu Site'
            name='site'
            id='site'
            value={data.site}
            onChange={(e) => updateFieldHandler('site', e.target.value)}
          />
        </TextInput.layout>

        <TextInput.layout>
          <TextInput.icon Icon={Lock} />
          <TextInput.inputText
            type='password'
            placeholder='Senha'
            name='password'
            id='password'
            value={data.password}
            onChange={(e) => updateFieldHandler('password', e.target.value)}
          />
        </TextInput.layout>
      </fieldset>
    </>
  );
}
