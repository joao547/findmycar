/* eslint-disable max-len */

import { User } from '@phosphor-icons/react';
import { PropostaCliente } from '../Proposta';

type ListaPropostaProps = {
  propostas: PropostaCliente[];
};

export function ListaProposta({ propostas }: ListaPropostaProps) {
  return (
    <div className='w-full max-w-md p-4 bg-white border border-gray-200 rounded-lg shadow sm:p-8 dark:bg-gray-800 dark:border-gray-700'>
      <div className='flex items-center justify-between mb-4'>
        <h5 className='text-xl font-bold leading-none text-gray-900 dark:text-white'>
          Ultimas Propostas
        </h5>
      </div>
      <div className='flow-root'>
        <ul className='divide-y divide-gray-200 dark:divide-gray-700'>
          {propostas?.map((proposta) => (
            <>
              <li className='py-3 sm:py-4' key={proposta.idProposta}>
                <div className='flex items-center'>
                  <div className='flex-shrink-0'>
                    <User className='w-8 h-8 rounded-full' />
                  </div>
                  <div className='flex-1 min-w-0 ms-4'>
                    <p className='text-sm font-medium text-gray-900 truncate dark:text-white'>
                      {proposta.nomeConsultor}
                    </p>
                    <p className='text-sm text-gray-500 truncate dark:text-gray-400'>
                      {proposta.tipoServico}
                    </p>
                    <p className='text-sm text-gray-500 truncate dark:text-gray-400'>
                      {proposta.servicoContratado}
                    </p>
                    <p className='text-sm text-yellow-500 truncate dark:text-gray-400'>
                      {proposta.statusAtual}
                    </p>
                  </div>
                  <div className='inline-flex items-center text-base font-semibold text-gray-900 dark:text-white'>
                    ${proposta.valorAcordado}
                  </div>
                </div>
              </li>
            </>
          ))}
        </ul>
      </div>
    </div>
  );
}
