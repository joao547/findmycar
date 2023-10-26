import {
  CurrencyCircleDollar,
  EnvelopeSimple,
  Lock,
  MapPin,
  Toolbox,
  User,
} from '@phosphor-icons/react';
import { TextInput } from '../../../../../components';
import { MultiStepFields } from '../StepBody';

export function RegisterStep({ data, updateFieldHandler }: MultiStepFields) {
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
          <TextInput.icon Icon={CurrencyCircleDollar} />
          <TextInput.inputText
            type='number'
            placeholder='Valor do serviço'
            name='precoDoServico'
            id='precoDoServico'
            value={data.precoDoServico}
            onChange={(e) =>
              updateFieldHandler('precoDoServico', e.target.value)
            }
          />
        </TextInput.layout>

        <TextInput.layout>
          <TextInput.icon Icon={Toolbox} />
          <TextInput.inputText
            type='text'
            placeholder='Area de atuação'
            name='areaDeAtuacao'
            id='areaDeAtuacao'
            value={data.areaDeAtuacao}
            onChange={(e) =>
              updateFieldHandler('areaDeAtuacao', e.target.value)
            }
          />
        </TextInput.layout>

        <TextInput.layout>
          <TextInput.icon Icon={MapPin} />
          <TextInput.inputText
            type='text'
            placeholder='Local de atuação'
            name='disponibilidade'
            id='disponibilidade'
            value={data.disponibilidade}
            onChange={(e) =>
              updateFieldHandler('disponibilidade', e.target.value)
            }
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
