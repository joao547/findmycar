/* eslint-disable max-len */
import { CurrencyDollar, User } from '@phosphor-icons/react';
import { PropostaAceitaCardData } from '../consultantHome';

type PropostaAceitaCardProps = {
  proposta: PropostaAceitaCardData;
};
export function PropostaAceitaCard({ proposta }: PropostaAceitaCardProps) {
  return (
    <div className='mb-6 w-full rounded-lg bg-white p-6'>
      <div className='flex items-center justify-between'>
        <div className='flex items-center'>
          <User className='w-8 h-8 rounded-full' />
          <div>
            <h3 className='text-base font-semibold text-gray-900'>
              {proposta.nome}
            </h3>
            <span className='block text-xs font-normal text-gray-500'>
              {proposta.email}
            </span>
          </div>
        </div>
      </div>
      <div className='mt-6 flex items-center justify-between text-sm font-semibold text-gray-900'>
        <div className='flex items-center'>
          <CurrencyDollar className='w-8 h-8' />
          <span className='mr-1 text-2xl text-green-500'>
            {proposta.valorFechado}
          </span>
        </div>
        <div className='flex items-center flex-col'>
          <p>{proposta.tipoServico}</p>
          <p>{proposta.servicoContratado}</p>
        </div>
      </div>
    </div>
  );
}
