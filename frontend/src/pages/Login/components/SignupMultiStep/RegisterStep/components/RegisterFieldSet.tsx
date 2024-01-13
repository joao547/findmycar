import {
  EnvelopeSimple,
  Lock,
  MapPin,
  SuitcaseSimple,
  User,
  Wrench,
} from '@phosphor-icons/react';
import { TextInput } from '../../../../../../components';
import { MultiStepFields } from '../../StepBody';
import {
  optionsBuscador,
  groupedOptions,
  statesOptions,
  CityOpt,
} from '../../../../data/signupMultiStepSelectOptions';
import Select, { SingleValue } from 'react-select';
import { useState } from 'react';
import useRegisterStep from '../../../../data/useRegisterStep';
import { AvatarInput } from './AvatarInput';

type RegisterFieldSetProps = MultiStepFields & {
  registerType: string;
};

export function RegisterFieldSet({
  registerType,
  updateFieldHandler,
  data,
}: RegisterFieldSetProps) {
  const { handlerLoadCitiesOptions } = useRegisterStep();
  const [isMecanicalConsultant, setIsMecanicalConsultant] = useState(false);

  const [selectedUf, setSelectedUf] = useState('');
  const [cityOptions, setCityOptions] = useState<Array<CityOpt>>([]);

  async function handlerSelectUf(
    newValue: SingleValue<{
      label: string;
      value: string;
    }>,
  ) {
    if (newValue?.value) {
      try {
        const data = await handlerLoadCitiesOptions(newValue?.value as string);

        setCityOptions(data);
        setSelectedUf(newValue?.value);
      } catch (err) {
        console.log(err);
      }
    }
  }
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
    <fieldset className='flex flex-col gap-4 mt-0'>
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
        <TextInput.icon Icon={Wrench} />
        <span className='text-gray-500'>Deseja dar consultoria mecanica ?</span>
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
          onChange={(newValue) =>
            updateFieldHandler('areasConsultor', newValue)
          }
        />
        {isMecanicalConsultant && (
          <Select
            options={groupedOptions}
            placeholder='Opções de Consulta'
            isMulti
            onChange={(newValue) =>
              updateFieldHandler('areasBuscador', newValue)
            }
          />
        )}
      </TextInput.layout>

      <TextInput.layout>
        <TextInput.icon Icon={MapPin} />
        <Select
          options={statesOptions}
          placeholder='Local de atuação'
          onChange={handlerSelectUf}
        />

        {selectedUf && (
          <Select
            options={cityOptions}
            placeholder='Cidades de atuação'
            onChange={(newValue) => updateFieldHandler('locais', newValue)}
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
  );
}
