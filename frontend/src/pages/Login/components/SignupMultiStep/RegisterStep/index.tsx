import {
  EnvelopeSimple,
  Lock,
  MapPin,
  SuitcaseSimple,
  User,
  Wrench,
} from '@phosphor-icons/react';
import { TextInput } from '../../../../../components';
import { MultiStepFields } from '../StepBody';
import Select from 'react-select';
import useRegisterStep from '../../../data/useRegisterStep';
import { useState } from 'react';

export function RegisterStep({ data, updateFieldHandler }: MultiStepFields) {
  const [isMecanicalConsultant, setIsMecanicalConsultant] = useState(false);

  const {
    statesOptions,
    cityOptions,
    optionsBuscador,
    groupedOptions,
    selectedUf,
    handlerChangeSelect,
  } = useRegisterStep();

  if (data.tipo === 'CLIENTE') {
    return (
      <>
        <h1 className='text-3xl mt-4 flex items-center gap-2'>
          Cadastro{' '}
          <span className='text-gray-600 font-light text-2xl'>
            &gt; Cliente
          </span>
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
            <TextInput.icon Icon={Lock} />
            <TextInput.inputText
              type='password'
              placeholder='Senha'
              name='password'
              id='password'
              value={data.senha}
              onChange={(e) => updateFieldHandler('senha', e.target.value)}
            />
          </TextInput.layout>
        </fieldset>
      </>
    );
  }
  return (
    <>
      <h1 className='text-3xl mt-4 flex items-center gap-2'>
        Cadastro{' '}
        <span className='text-gray-600 font-light text-2xl'>
          &gt; Consultor
        </span>
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
          <TextInput.icon Icon={Wrench} />
          <span className='text-gray-500'>
            Deseja dar consultoria mecanica ?
          </span>
          <input
            type='checkbox'
            onChange={(e) => setIsMecanicalConsultant(e.target.checked)}
          />
        </TextInput.layout>

        <TextInput.layout>
          <TextInput.icon Icon={SuitcaseSimple} />
          <Select
            options={optionsBuscador}
            placeholder='Area de Atuação'
            isMulti
          />
          {isMecanicalConsultant && (
            <Select
              options={groupedOptions}
              placeholder='Opções de Consulta'
              isMulti
            />
          )}
        </TextInput.layout>

        <TextInput.layout>
          <TextInput.icon Icon={MapPin} />
          <Select
            options={statesOptions}
            placeholder='Local de atuação'
            onChange={handlerChangeSelect}
          />
          {selectedUf && (
            <Select
              options={cityOptions}
              placeholder='Cidades de atuação'
              onChange={(newValue) => console.log(newValue)}
              isMulti
            />
          )}
        </TextInput.layout>

        <TextInput.layout>
          <TextInput.icon Icon={Lock} />
          <TextInput.inputText
            type='password'
            placeholder='Senha'
            name='password'
            id='password'
            value={data.senha}
            onChange={(e) => updateFieldHandler('senha', e.target.value)}
          />
        </TextInput.layout>
      </fieldset>
    </>
  );
}
