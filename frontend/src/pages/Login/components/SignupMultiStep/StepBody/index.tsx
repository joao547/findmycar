/* eslint-disable complexity */
import { FormEvent, useState } from 'react';
import { IdentificationStep } from '../IdentificationStep';
import { RegisterStep } from '../RegisterStep';
import { SendStep } from '../SendStep';
import { StepActions } from '../StepActions';

export type MultiStepFields = {
  data: {
    name: string;
    email: string;
    password: string;
    site: string;
    type: string;
  };
  updateFieldHandler: (key: string, value: string) => void;
};

type StepBodyProps = {
  currentStep: number;
  handlerChangeStep: (value: React.SetStateAction<number>) => void;
};

const stepsBody = [IdentificationStep, RegisterStep, SendStep];

const multiStepData = {
  name: '',
  email: '',
  password: '',
  site: '',
  type: '',
};

export function StepBody({ currentStep, handlerChangeStep }: StepBodyProps) {
  const [data, setData] = useState(multiStepData);

  function updateFieldHandler(key: string, value: string) {
    setData((prev) => {
      return {
        ...prev,
        [key]: value,
      };
    });
  }

  function handleSubmit(e: FormEvent) {
    e.preventDefault();
    alert('Enviando...');
  }

  const CurrentStepBody = stepsBody[currentStep];

  return (
    <form className='flex flex-col h-2/3 gap-4' onSubmit={handleSubmit}>
      <span className='block text-sm text-gray-400 mt-8'>
        passo {currentStep + 1}/3
      </span>

      <CurrentStepBody data={data} updateFieldHandler={updateFieldHandler} />

      <StepActions
        currentStep={currentStep}
        handlerChangeStep={handlerChangeStep}
      />
    </form>
  );
}
