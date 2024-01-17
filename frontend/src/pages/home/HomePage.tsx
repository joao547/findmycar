import { Home } from './components/Home/Home';

export function HomePage() {
  const { tipo } = JSON.parse(localStorage.getItem('@user') as string);

  return <Home homeType={tipo} />;
}
