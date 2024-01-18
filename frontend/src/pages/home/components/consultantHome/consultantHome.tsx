import { PropostaAceitaCard } from './components/PropostaAceitaCard';
import { api } from '../../../../service/api';
import { ClientUser } from '../ConsultantProfile';
import { useEffect, useState } from 'react';

export type PropostaAceitaCardData = {
  idCliente: number;
  nome: string;
  email: string;
  fotoBase64: '';
  valorFechado: number;
  tipoServico: string;
  servicoContratado: string;
};

export function ConsultantHome() {
  const [propostas, setPropostas] = useState<Array<PropostaAceitaCardData>>([]);
  useEffect(() => {
    async function fetchData() {
      const token = localStorage.getItem('@token');

      const response = await api.get<ClientUser>('/api/user/me', {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });

      const { id } = response.data;

      const { data } = await api.get('/api/consultor/buscarclientes', {
        headers: {
          Authorization: `Bearer ${token}`,
        },
        params: {
          idConsultor: id,
        },
      });

      setPropostas(data);
    }

    fetchData();
  }, []);
  return (
    <div>
      <h1 className='text-2xl font-medium'>
        Clientes com Propostas em aberto:{' '}
      </h1>
      <div className='grid grid-cols-4 gap-4 mt-4'>
        {propostas.map((proposta) => (
          <PropostaAceitaCard key={proposta.idCliente} proposta={proposta} />
        ))}
      </div>
    </div>
  );
}
