import { PropostasPage } from './components/PropostaPage/PropostasPage';

export function Proposta() {
  const { tipo } = JSON.parse(localStorage.getItem('@user') as string);

  return <PropostasPage propostaPageType={tipo} />;
}
