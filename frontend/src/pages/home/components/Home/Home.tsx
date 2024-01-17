import { ClientHome } from '../clientHome/ClientHome';
import { ConsultantHome } from '../consultantHome/consultantHome';

type HomeProps = {
  homeType: string;
};

export function Home({ homeType }: HomeProps) {
  if (homeType === 'CLIENTE') {
    return <ClientHome />;
  }
  return <ConsultantHome />;
}
