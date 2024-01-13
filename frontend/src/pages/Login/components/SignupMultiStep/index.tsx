import { CaretLeft, Check } from '@phosphor-icons/react';
import { Step } from './Step';
import { StepsWrapper } from './StepsWrapper';
import { StepBody } from './StepBody';
import { useState } from 'react';

type SignupMultiStepProps = {
  handleAuthenticateUser: (
    value: React.SetStateAction<'login' | 'signup'>,
  ) => void;
};

const stepDescription = ['Identificação', 'Cadastro', 'Envio'];

export function SignupMultiStep({
  handleAuthenticateUser,
}: SignupMultiStepProps) {
  const [currentStep, setCurrentStep] = useState(0);

  return (
    <div className='w-full h-full flex flex-col p-4'>
      <button className='h-8'>
        <CaretLeft size={32} onClick={() => handleAuthenticateUser('login')} />
      </button>
      <StepsWrapper>
        {Array.from({ length: 3 }, (_, i) => i).map((step) => (
          <Step
            key={step}
            active={currentStep >= step}
            stepDescription={stepDescription[step]}
          >
            {currentStep > step ? <Check /> : step + 1}
          </Step>
        ))}
      </StepsWrapper>
      <StepBody
        currentStep={currentStep}
        handlerChangeStep={setCurrentStep}
        handlerChangeForm={handleAuthenticateUser}
      />
    </div>
  );
}
