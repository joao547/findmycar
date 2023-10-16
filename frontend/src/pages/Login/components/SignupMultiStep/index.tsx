import { Check } from '@phosphor-icons/react';
import { Step } from './Step';
import { StepsWrapper } from './StepsWrapper';
import { StepBody } from './StepBody';
import { useState } from 'react';

const stepDescription = ['Identificação', 'Cadastro', 'Envio'];

export function SignupMultiStep() {
  const [currentStep, setCurrentStep] = useState(0);

  return (
    <div className='mt-24 w-full h-full flex flex-col p-4'>
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
      <StepBody currentStep={currentStep} handlerChangeStep={setCurrentStep} />
    </div>
  );
}
