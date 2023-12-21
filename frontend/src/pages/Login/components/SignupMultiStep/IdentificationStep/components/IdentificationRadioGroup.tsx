import { RadioItem } from './RadioItem';
import * as RadioGroup from '@radix-ui/react-radio-group';

type IdentificationRadioGroupProps = {
  updateFieldHandler: (key: string, value: string) => void;
};
export function IdentificationRadioGroup({
  updateFieldHandler,
}: IdentificationRadioGroupProps) {
  return (
    <RadioGroup.Root
      onValueChange={(value) => updateFieldHandler('tipo', value)}
      className='grid grid-cols-2 gap-4 h-full mt-4'
    >
      <RadioItem
        title='Cliente'
        itemValue='CLIENTE'
        description='Pesquise por consultores em diversas regiões do Brasil e receba
            assistência especializada para encontrar o veículo ideal para suas
            necessidades.'
      />

      <RadioItem
        title='Consultor'
        itemValue='CONSULTOR'
        description='Junte-se a nós como consultor para ajudar clientes na compra de
        automóveis.'
      />
    </RadioGroup.Root>
  );
}
