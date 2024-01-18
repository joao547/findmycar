import { useEffect, useState } from 'react';
import { ClientUser } from '../../../home/components/ConsultantProfile';
import { api } from '../../../../service/api';
import { ListaProposta } from '../ListaProposta';

export type PropostaCliente = {
  idProposta: number;
  nomeConsultor: string;
  idConsultor: number;
  nomeCliente: string;
  idCliente: number;
  valorAcordado: number;
  dataProposta: null;
  tipoServico: string;
  servicoContratado: string;
  localServico: string | null;
  statusAtual: string;
};

export default function PropostasCliente() {
  const [propostas, setPropostas] = useState<Array<PropostaCliente>>([]);

  async function fetchData() {
    const token = localStorage.getItem('@token');

    const response = await api.get<ClientUser>('/api/user/me', {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });

    const { id } = response.data;

    const { data } = await api.get('/api/proposta/propostasCliente', {
      headers: {
        Authorization: `Bearer ${token}`,
      },
      params: {
        idDono: id,
        statusBuscado: 'ALL',
      },
    });

    setPropostas(data);
  }

  useEffect(() => {
    const fetchDataAsync = async () => {
      await fetchData();
    };

    fetchDataAsync();
  }, []);

  return (
    <div>
      <section>
        <ListaProposta
          propostas={propostas}
          fetchClientePropostas={fetchData}
        />
      </section>
    </div>
  );
}
