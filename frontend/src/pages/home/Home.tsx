import { Loading } from './components/Loading';
import { useGithubInfo } from './data/useGithubInfo';

export const Home = () => {
  const { isLoading, data } = useGithubInfo();

  if (isLoading) {
    return <Loading />;
  }

  return (
    <main>
      <pre>{JSON.stringify(data, null, 2)}</pre>
    </main>
  );
};
