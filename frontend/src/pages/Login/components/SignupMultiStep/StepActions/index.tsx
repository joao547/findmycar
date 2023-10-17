/* eslint-disable complexity */
type StepActionsProps = {
  currentStep: number;
  handlerChangeStep: (value: React.SetStateAction<number>) => void;
};

export function StepActions({
  currentStep,
  handlerChangeStep,
}: StepActionsProps) {
  return (
    <div className='flex gap-2 ml-auto mt-auto'>
      <button
        type='button'
        onClick={() => handlerChangeStep((prev) => Math.max(prev - 1, 0))}
        className='w-[150px] h-16 font-bold'
      >
        Voltar
      </button>

      {currentStep === 2 ? (
        <button
          type='submit'
          className='w-[150px] h-16 rounded-md bg-orange-600 text-white'
        >
          Finalizar
        </button>
      ) : (
        <button
          type='button'
          onClick={(e) => {
            e.preventDefault();
            return handlerChangeStep((prevNumber) =>
              Math.min(prevNumber + 1, 2),
            );
          }}
          className='w-[150px] h-16 rounded-md bg-orange-600 text-white'
        >
          Avançar
        </button>
      )}
    </div>
  );
}
