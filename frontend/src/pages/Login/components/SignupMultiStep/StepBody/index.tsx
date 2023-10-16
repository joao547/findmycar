import { useState } from 'react';
import { IdentificationStep } from '../IdentificationStep';
import { RegisterStep } from '../RegisterStep';
import { SendStep } from '../SendStep';

export type MultiStepFields = {
  data: {
    name: string;
    email: string;
    password: string;
    site: string;
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

  const CurrentStepBody = stepsBody[currentStep];

  return (
    <form className='flex flex-col h-2/3 gap-4'>
      <span className='block text-sm text-gray-400 mt-8'>
        passo {currentStep + 1}/3
      </span>

      <CurrentStepBody data={data} updateFieldHandler={updateFieldHandler} />

      <div className='flex gap-4 ml-auto mt-auto'>
        <button
          type='button'
          onClick={() => handlerChangeStep((prev) => Math.max(prev - 1, 0))}
          className='w-[150px] h-16 font-bold'
        >
          Voltar
        </button>
        <button
          type='button'
          onClick={() =>
            handlerChangeStep((prevNumber) => Math.min(prevNumber + 1, 2))
          }
          className='w-[150px] h-16 rounded-md bg-orange-600 text-white'
        >
          {currentStep === 2 ? 'Finalizar' : 'Avançar'}
        </button>
      </div>
    </form>
  );
}
