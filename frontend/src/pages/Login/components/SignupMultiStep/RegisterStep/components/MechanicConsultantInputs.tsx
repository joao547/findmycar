import { GearSix, MapPin } from '@phosphor-icons/react';
import { TextInput } from '../../../../../../components';
import Select, { SingleValue } from 'react-select';
import {
  CityOpt,
  GeneralOption,
  statesOptions,
} from '../../../../data/signupMultiStepSelectOptions';
import useRegisterStep from '../../../../data/useRegisterStep';
import { useState } from 'react';
import { MultiStepFields } from '../../StepBody';

type MechanicConsultantInputsProps = MultiStepFields & {
  isMecanicalConsultant: boolean;
  mechanicalOptions: GeneralOption[];
};

export function MechanicConsultantInputs({
  isMecanicalConsultant,
  updateFieldHandler,
  mechanicalOptions,
}: MechanicConsultantInputsProps) {
  const { handlerLoadCitiesOptions } = useRegisterStep();

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

  if (!isMecanicalConsultant) return null;

  return (
    <>
      <div
        className='flex gap-2 border-2 mr-3 py-1 px-2 
				items-center text-gray-500 rounded-lg col-span-2'
      >
        <TextInput.icon Icon={GearSix} />
        <Select
          className=''
          options={mechanicalOptions}
          placeholder='Opções de Carros'
          isMulti
          onChange={(newValue) => updateFieldHandler('areasBuscador', newValue)}
        />
      </div>
      <div
        className='flex gap-2 border-2 mr-3 py-1 px-2 
				items-center text-gray-500 rounded-lg col-span-2'
      >
        <TextInput.icon Icon={MapPin} />
        <Select
          options={statesOptions}
          placeholder='Local de atuação'
          onChange={handlerSelectUf}
          menuPlacement='top'
        />
        {selectedUf && (
          <Select
            options={cityOptions}
            placeholder='Cidades de atuação'
            onChange={(newValue) => updateFieldHandler('locais', newValue)}
            isMulti
            menuPlacement='top'
          />
        )}
      </div>
    </>
  );
}
