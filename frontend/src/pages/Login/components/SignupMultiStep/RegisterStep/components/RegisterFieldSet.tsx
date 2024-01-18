import {
  CurrencyDollar,
  EnvelopeSimple,
  Lock,
  SuitcaseSimple,
  User,
  Wrench,
} from '@phosphor-icons/react';
import { TextInput } from '../../../../../../components';
import { MultiStepFields } from '../../StepBody';
import Select from 'react-select';
import { useState } from 'react';
import { AvatarInput } from './AvatarInput';
import { MechanicConsultantInputs } from './MechanicConsultantInputs';
import useSelectData from '../../../../data/useRegisterSelectData';

type RegisterFieldSetProps = MultiStepFields & {
  registerType: string;
};

export function RegisterFieldSet({
  registerType,
  updateFieldHandler,
  data,
}: RegisterFieldSetProps) {
  const [isMecanicalConsultant, setIsMecanicalConsultant] = useState(false);
  const { servicosBuscador, carroMarcas } = useSelectData();

  if (registerType === 'CLIENTE') {
    return (
      <fieldset className='flex flex-col gap-4 mt-0'>
        <AvatarInput handleUploadUserAvatar={updateFieldHandler} />
        <TextInput.layout>
          <TextInput.icon Icon={User} />
          <TextInput.inputText
            type='text'
            placeholder='Seu nome'
            name='name'
            id='name'
            onChange={(e) => updateFieldHandler('nome', e.target.value)}
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
    );
  }

  return (
    <fieldset className='grid grid-cols-2 gap-4'>
      <AvatarInput handleUploadUserAvatar={updateFieldHandler} />
      <TextInput.layout>
        <TextInput.icon Icon={User} />
        <TextInput.inputText
          type='text'
          placeholder='Seu nome'
          name='name'
          id='name'
          value={data.nome}
          onChange={(e) => updateFieldHandler('nome', e.target.value)}
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

      <div
        className='flex gap-2 border-2 mr-3 py-1 px-2 
				items-center text-gray-500 rounded-lg col-span-2'
      >
        <TextInput.icon Icon={SuitcaseSimple} />
        <Select
          options={servicosBuscador}
          placeholder='Tipo de Atuação'
          isMulti
          onChange={(newValue) =>
            updateFieldHandler('areasConsultor', newValue)
          }
        />

        <div className='flex items-center justify-center ml-auto border shadow-md'>
          <TextInput.icon Icon={CurrencyDollar} />
          <TextInput.inputText
            type='number'
            placeholder='valor Da Busca'
            name='inputBusca'
            id='inputBusca'
            onChange={(e) =>
              updateFieldHandler('precoServicoBuscador', e.target.value)
            }
          />
        </div>
      </div>
      <div
        className='flex gap-2 border-2 mr-3 py-1 px-2 
				items-center text-gray-500 rounded-lg col-span-2'
      >
        <TextInput.icon Icon={Wrench} />
        <span className='text-gray-500'>consultor mecanico?</span>
        <input
          type='checkbox'
          onChange={(e) => setIsMecanicalConsultant(e.target.checked)}
        />

        {isMecanicalConsultant && (
          <div className='flex items-center justify-center ml-auto border shadow-md rounded'>
            <TextInput.icon Icon={CurrencyDollar} />
            <TextInput.inputText
              type='number'
              placeholder='valor Da Consulta Mecanica'
              name='inputMecanico'
              id='inputMecanico'
              onChange={(e) =>
                updateFieldHandler('precoServicoMecanico', e.target.value)
              }
            />
          </div>
        )}
      </div>
      <MechanicConsultantInputs
        data={data}
        isMecanicalConsultant={isMecanicalConsultant}
        updateFieldHandler={updateFieldHandler}
        mechanicalOptions={carroMarcas}
      />
    </fieldset>
  );
}
