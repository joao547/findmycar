import { CurrencyDollar } from '@phosphor-icons/react';
import ConsultantRate from '../ConsultantRate';
import { Consultant } from '../clientHome/ClientHome';

type ConsultantProfileProps = {
  consultant: Consultant;
};

export default function ConsultantProfile({
  consultant,
}: ConsultantProfileProps) {
  const locations = consultant?.localidade
    ?.map((place) => place.name)
    .join(',');
  const services = consultant?.servicosBuscador
    ?.map((service) => service.nome)
    .join(',');

  const carModels = consultant?.carroMarcas?.map((car) => car.nome).join(',');

  return (
    <li
      className='group bg-white w-full h-80 p-4 rounded-md 
          hover:bg-orange-500 hover:text-white transition-colors duration-300 ease-in-out'
    >
      <div className='flex flex-col h-full w-full'>
        <h1 className='text-black font-bold group-hover:text-white '>
          {consultant.nome}
        </h1>
        <span className='text-gray-500 font-light group-hover:text-white'>
          {locations ? locations : 'consultor buscador'}
        </span>
        <hr />
        <section className='text-gray-500 mt-6 group-hover:text-white'>
          {services ? services : carModels}
        </section>

        <section className='text-gray-500 mt-6 group-hover:text-white flex items-center'>
          <p className='text-green-500 font-medium text-2xl'>
            {consultant.precoServico}
          </p>{' '}
          <CurrencyDollar size={24} />
        </section>

        <ConsultantRate />

        <button className='w-full border p-2 font-bold mt-auto rounded-sm'>
          Fazer Proposta
        </button>
      </div>
    </li>
  );
}
