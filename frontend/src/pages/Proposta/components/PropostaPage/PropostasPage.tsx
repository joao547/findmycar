import PropostasCliente from '../PropostasCliente';
import PropostasConsultor from '../PropostasConsultor';

type PropostasPageProps = {
  propostaPageType: string;
};

export function PropostasPage({ propostaPageType }: PropostasPageProps) {
  if (propostaPageType === 'CLIENTE') {
    return <PropostasCliente />;
  }
  return <PropostasConsultor />;
}
