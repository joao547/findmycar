import { MultiStepFields } from '../StepBody';

export function SendStep({ data }: MultiStepFields) {
  return (
    <div>
      <h1 className='text-3xl font-bold mt-4'>Final</h1>
      <p>{JSON.stringify(data, null, 2)}</p>
    </div>
  );
}
