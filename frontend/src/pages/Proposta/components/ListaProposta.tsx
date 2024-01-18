import { User } from '@phosphor-icons/react';
import { PropostaCliente } from '../components/PropostasCliente';
import { api } from '../../../service/api';
import { toast } from 'react-toastify';

type ListaPropostaProps = {
  propostas: PropostaCliente[];
  onOptionClicked?: () => void;
};

export function ListaProposta({
  propostas,
  onOptionClicked,
}: ListaPropostaProps) {
  const { tipo } = JSON.parse(localStorage.getItem('@user') as string);
  const token = localStorage.getItem('@token');

  async function handleAcceptDeal(idProposta: number) {
    try {
      if (onOptionClicked) {
        await api.put(
          '/api/proposta/aceitarNegar',
          {
            idProposta,
            foiaceita: true,
          },
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          },
        );

        onOptionClicked();
        toast.success('Proposta Aceita com Sucesso!!');
      }
    } catch (error) {
      console.log(error);
    }
  }

  async function handleDeclineDeal(idProposta: number) {
    try {
      if (onOptionClicked) {
        await api.put(
          '/api/proposta/aceitarNegar',
          {
            idProposta,
            foiaceita: false,
          },
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          },
        );

        onOptionClicked();
        toast.success('Proposta Recusada com Sucesso!!');
      }
    } catch (error) {
      console.log(error);
    }
  }

  if (propostas.length > 0) {
    return (
      <div className='w-full max-w-md p-4 bg-white border border-gray-200 rounded-lg shadow sm:p-8'>
        <div className='flex items-center justify-between mb-4'>
          <h5 className='text-xl font-bold leading-none text-gray-900'>
            Ultimas Propostas
          </h5>
        </div>
        <div className='flow-root'>
          <ul className='divide-y divide-gray-200'>
            {propostas?.map((proposta) => (
              <li
                className='flex flex-col py-3 sm:py-4'
                key={proposta.idProposta}
              >
                <div className='flex items-center'>
                  <div className='flex-shrink-0'>
                    <User className='w-8 h-8 rounded-full' />
                  </div>
                  <div className='flex-1 min-w-0 ms-4'>
                    <p className='text-sm font-medium text-gray-900 truncate'>
                      {tipo === 'CLIENTE'
                        ? proposta.nomeConsultor
                        : proposta.nomeCliente}
                    </p>
                    <p className='text-sm text-gray-500 truncate'>
                      {proposta.tipoServico}
                    </p>
                    <p className='text-sm text-gray-500 truncate'>
                      {proposta.servicoContratado}
                    </p>
                    <p className='text-sm text-yellow-500 truncate'>
                      {proposta.statusAtual}
                    </p>
                  </div>
                  <div className='inline-flex items-center text-base font-semibold text-gray-900'>
                    ${proposta.valorAcordado}
                  </div>
                </div>
                {tipo === 'CONSULTOR' && (
                  <div className='ml-auto'>
                    <button
                      className='focus:outline-none text-white bg-green-700 hover:bg-green-800 
                                focus:ring-4 focus:ring-green-300 font-medium 
                                rounded-lg text-sm px-5 py-2.5 me-2 mb-2 
                              dark:bg-green-600 dark:hover:bg-green-700 dark:focus:ring-green-800
                                disabled:bg-gray-500 disabled:hover:bg-gray-500
                              '
                      onClick={() => handleAcceptDeal(proposta.idProposta)}
                      disabled={proposta.statusAtual !== 'ESPERANDO'}
                    >
                      Aceitar
                    </button>

                    <button
                      type='button'
                      className='focus:outline-none text-white bg-red-700 
                      hover:bg-red-800 focus:ring-4 focus:ring-red-300 
                      font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 
                      dark:bg-red-600 dark:hover:bg-red-700 dark:focus:ring-red-900
                      disabled:bg-gray-500 disabled:hover:bg-gray-500'
                      onClick={() => handleDeclineDeal(proposta.idProposta)}
                      disabled={proposta.statusAtual !== 'ESPERANDO'}
                    >
                      Recusar
                    </button>
                  </div>
                )}
              </li>
            ))}
          </ul>
        </div>
      </div>
    );
  }
  return (
    <div className='w-full max-w-md p-4 bg-white border border-gray-200 rounded-lg shadow sm:p-8'>
      <h5 className='text-xl font-bold leading-none text-gray-900'>
        nenhuma proposta encontrada!
      </h5>
    </div>
  );
}
