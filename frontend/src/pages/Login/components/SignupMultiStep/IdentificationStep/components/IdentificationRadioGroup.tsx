import { IdentificationBadge, User } from '@phosphor-icons/react';
import * as RadioGroup from '@radix-ui/react-radio-group';

type IdentificationRadioGroupProps = {
  updateFieldHandler: (key: string, value: string) => void;
};
export function IdentificationRadioGroup({
  updateFieldHandler,
}: IdentificationRadioGroupProps) {
  return (
    <RadioGroup.Root
      onValueChange={(value) => updateFieldHandler('type', value)}
      className='grid grid-cols-2 gap-4 h-full mt-4'
    >
      <RadioGroup.Item value='client'>
        <div
          className='w-full h-full 
          border rounded-xl shadow-xl flex flex-col'
        >
          <div className='w-full h-4/6 flex flex-col items-center mt-10 p-4'>
            <User className='w-32 h-32 text-gray-400' />
            <h1 className='font-medium text-xl mt-4 text-center'>Cliente</h1>
            <p className='text-center font-light mt-4'>
              Pesquise por consultores em diversas regiões do Brasil e receba
              assistência especializada para encontrar o veículo ideal para suas
              necessidades.
            </p>
          </div>
          <RadioGroup.Indicator className='h-4 w-full bg-orange-500 mt-auto' />
        </div>
      </RadioGroup.Item>

      <RadioGroup.Item value='consultant'>
        <div
          className='w-full h-full 
          border rounded-xl shadow-xl flex flex-col'
        >
          <div className='w-full h-4/6 flex flex-col items-center mt-10 p-4'>
            <IdentificationBadge className='w-32 h-32 text-gray-400' />
            <h1 className='font-medium text-xl mt-4 text-center'>Consultor</h1>
            <p className='text-center font-light mt-4'>
              Junte-se a nós como consultor para ajudar clientes na compra de
              automóveis.
            </p>
          </div>
          <RadioGroup.Indicator className='h-4 w-full bg-orange-500 mt-auto' />
        </div>
      </RadioGroup.Item>
    </RadioGroup.Root>
  );
}
