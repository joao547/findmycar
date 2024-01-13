import { EnvelopeSimple, User } from '@phosphor-icons/react';
import { ReviewField } from './components/ReviewField';
import { MultiStepFields } from '../StepBody';
import { Images } from '@phosphor-icons/react/dist/ssr/Images';

export function SendStep({ data }: MultiStepFields) {
  const url = URL.createObjectURL(data.avatar);
  return (
    <div>
      <h1 className='text-3xl font-bold mt-4'>Confirme seus dados:</h1>
      <div
        className='w-full border-b-2 border-gray-300 flex gap-1 
                     mt-4 h-20 items-center shadow-md p-4'
      >
        <Images size={32} />
        <p className='font-semibold'>Avatar:</p>
        <div className='w-16 h-16 inline-block rounded-full overflow-hidden shadow-lg'>
          <img
            src={url}
            alt='avatar'
            className='w-full h-full object-cover rounded-full'
          />
        </div>
      </div>
      <ReviewField Icon={User} fieldName='Nome:' fieldValue={data.nome} />
      <ReviewField
        Icon={EnvelopeSimple}
        fieldName='Email:'
        fieldValue={data.email}
      />
    </div>
  );
}
