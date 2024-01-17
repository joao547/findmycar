import ConsultantRate from '../ConsultantRate';

type ConsultantProfileProps = {
  consultantName: string;
  consultantCities: string;
  consultantArea: string;
};

export default function ConsultantProfile({
  consultantArea,
  consultantCities,
  consultantName,
}: ConsultantProfileProps) {
  return (
    <li
      className='group bg-white w-full h-80 p-4 rounded-md 
          hover:bg-orange-500 hover:text-white transition-colors duration-300 ease-in-out'
    >
      <div className='flex flex-col h-full w-full'>
        <h1 className='text-black font-bold group-hover:text-white '>
          {consultantName}
        </h1>
        <span className='text-gray-500 font-light group-hover:text-white'>
          {consultantCities}
        </span>
        <hr />
        <section className='text-gray-500 mt-6 group-hover:text-white'>
          {consultantArea}
        </section>

        <ConsultantRate />

        <button className='w-full border p-2 font-bold mt-auto rounded-sm'>
          Fazer Proposta
        </button>
      </div>
    </li>
  );
}
