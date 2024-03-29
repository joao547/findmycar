/* eslint-disable @typescript-eslint/no-explicit-any */
import { useRef, useState } from 'react';
import { PropostaCliente } from '../../components/PropostasCliente';
import { ClientUser } from '../../../home/components/ConsultantProfile';
import { api } from '../../../../service/api';
import { GeneralOption } from '../../../Login/data/signupMultiStepSelectOptions';
import Select from 'react-select';
import { ListaProposta } from '../ListaProposta';

const statusOptions = [
  { label: 'Todos', value: 'ALL' },
  { label: 'Aceito', value: 'ACEITO' },
  { label: 'Não Aceito', value: 'NAO_ACEITO' },
  { label: 'Esperando', value: 'ESPERANDO' },
  { label: 'Finalizado', value: 'FINALIZADO' },
];

export default function PropostasConsultor() {
  const [propostas, setPropostas] = useState<Array<PropostaCliente>>([]);
  const propostaStatusSelect = useRef<any>(null);

  async function handlerSearch() {
    const token = localStorage.getItem('@token');

    const response = await api.get<ClientUser>('/api/user/me', {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });

    const { id } = response.data;
    const status = propostaStatusSelect.current
      .getValue()
      .map((opt: GeneralOption) => opt.value)
      .join(',');

    const { data } = await api.get('/api/proposta/propostasConsultor', {
      headers: {
        Authorization: `Bearer ${token}`,
      },
      params: {
        idDono: id,
        statusBuscado: status,
      },
    });

    setPropostas(data);
  }

  return (
    <div>
      <section className='flex items-center justify-center'>
        <div className='bg-white h-20 w-full rounded-md flex items-center p-4 gap-4'>
          <div className='w-full'>
            <Select
              options={statusOptions}
              placeholder='Status da Proposta'
              ref={propostaStatusSelect}
            />
          </div>

          <button
            className='bg-orange-500 h-full w-40 rounded-md text-white'
            onClick={handlerSearch}
          >
            Buscar
          </button>
        </div>
      </section>
      <div className='mt-4'>
        <ListaProposta
          propostas={propostas}
          onOptionClicked={() => setPropostas([])}
        />
      </div>
    </div>
  );
}
