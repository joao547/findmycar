import { FormEvent, useState } from 'react';
import { IdentificationStep } from '../IdentificationStep';
import { RegisterStep } from '../RegisterStep';
import { SendStep } from '../SendStep';
import { StepActions } from '../StepActions';
import { toast } from 'react-toastify';
import useLogin, { MultiStepDataInput } from '../../../data/useLogin';

export type ConsultantLocation = {
  ibgeCode: number;
  name: string;
  uf: string;
};

export type MultiStepFields = {
  data: MultiStepDataInput;
  updateFieldHandler: (key: string, value: string | number | object) => void;
};

type StepBodyProps = {
  currentStep: number;
  handlerChangeForm: (value: React.SetStateAction<'login' | 'signup'>) => void;
  handlerChangeStep: (value: React.SetStateAction<number>) => void;
};

const stepsBody = [IdentificationStep, RegisterStep, SendStep];

const multiStepData = {
  nome: '',
  email: '',
  senha: '',
  tipo: '',
  locais: [],
  areasBuscador: [],
  areasConsultor: [],
};

export function StepBody({
  currentStep,
  handlerChangeStep,
  handlerChangeForm,
}: StepBodyProps) {
  const { handleCreateUser } = useLogin();
  const [data, setData] = useState(multiStepData);

  function updateFieldHandler(key: string, value: string | number | object) {
    setData((prev) => {
      return {
        ...prev,
        [key]: value,
      };
    });
  }

  async function handleSubmit(e: FormEvent) {
    e.preventDefault();
    try {
      await toast.promise(handleCreateUser(data), {
        success: 'Created successful',
        pending: 'loading...',
        error: 'Error creating user',
      });

      handlerChangeForm('login');
    } catch (err) {
      console.log(err);
    }
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
