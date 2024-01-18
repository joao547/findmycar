/* eslint-disable @typescript-eslint/no-explicit-any */
import { CurrencyDollar } from '@phosphor-icons/react';
import CreateDealModal from '../../../../components/Modal';
import ConsultantRate from '../ConsultantRate';
import { Consultant } from '../clientHome/ClientHome';
import Select from 'react-select';
import { api } from '../../../../service/api';
import { useRef, useState } from 'react';
import { toast } from 'react-toastify';

export type ClientUser = {
  id: number;
  nome: string;
  email: string;
  tipo: string;
  imagemBase64: string;
};
type ConsultantProfileProps = {
  consultant: Consultant;
  isMecanicalConsultant: boolean;
};

export default function ConsultantProfile({
  consultant,
  isMecanicalConsultant,
}: ConsultantProfileProps) {
  const locationSelect = useRef<any>(null);
  const serviceTypeSelect = useRef<any>(null);

  const [isOpen, setIsOpen] = useState(false);

  const locations = consultant?.localidade
    ?.map((place) => place.name)
    .join(',');
  const services = consultant?.servicosBuscador
    ?.map((service) => service.nome)
    .join(',');

  const carModels = consultant?.carroMarcas?.map((car) => car.nome).join(',');

  const consultantServiceOptions = consultant.servicosBuscador
    ? consultant.servicosBuscador
    : consultant.carroMarcas;

  const consultantServiceSelectOptions = consultantServiceOptions?.map(
    (item) => {
      return {
        label: item.nome,
        value: item.id,
      };
    },
  );

  const locationSelectOptions = consultant?.localidades
    ? consultant?.localidades.map((location) => {
        return {
          label: location.name,
          value: location.id,
        };
      })
    : undefined;

  function handleOpenModal() {
    setIsOpen(true);
  }

  async function handleCreateDeal() {
    const token = localStorage.getItem('@token');
    try {
      const { data } = await api.get<ClientUser>('/api/user/me', {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      const selectedLocation = locationSelect.current
        .getValue()
        .map((l: any) => l.label);
      const selectedType = serviceTypeSelect.current
        .getValue()
        .map((t: any) => t.label);

      const { id } = data;

      const tipoServico = isMecanicalConsultant ? 'buscador' : 'mecanico';

      const body = {
        idCliente: id,
        idConsultor: consultant.idConsultor,
        tipoServico,
        valorFechado: consultant.precoServico,
        servicoContratado:
          selectedType.length > 0 ? selectedType.join(',') : undefined,
        localServico:
          selectedLocation.length > 0 ? selectedLocation.join(',') : undefined,
      };

      await api.post('/api/proposta/criar', body, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });

      setIsOpen(false);
      toast.success('Proposta realizada com sucesso!!');
    } catch (error) {
      console.log(error);
    }
  }

  return (
    <>
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

          <button
            onClick={handleOpenModal}
            className='w-full border p-2 font-bold mt-auto rounded-sm'
          >
            Fazer Proposta
          </button>
        </div>
      </li>
      <CreateDealModal isOpen={isOpen} onClose={() => setIsOpen(false)}>
        <section className='p-4'>
          <h1 className='text-1xl font-medium'>Fazer Proposta: </h1>
          <div className='mt-4 w-full h-full flex flex-col gap-4'>
            <div>
              <p>Selecione o tipo de serviço</p>
              <Select
                options={consultantServiceSelectOptions}
                placeholder='selecione o tipo de serviço'
                ref={serviceTypeSelect}
              />
            </div>
            <div>
              <p>Selecione a localidade</p>
              <Select
                options={locationSelectOptions}
                placeholder='selecione a localidade'
                isDisabled={isMecanicalConsultant}
                ref={locationSelect}
              />
            </div>
            <button
              className='bg-orange-500 h-10 rounded-md text-white'
              onClick={handleCreateDeal}
            >
              Enviar Proposta
            </button>
          </div>
        </section>
      </CreateDealModal>
    </>
  );
}
