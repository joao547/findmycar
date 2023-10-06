import { useEffect, useState } from 'react';

export const useGithubInfo = () => {
  const [data, setData] = useState(null);
  const [isLoading, setIsLoading] = useState(true);

  const fetchData = async () => {
    try {
      const data = await fetch(`https://api.github.com/users/rennanprysthon`);
      const json = await data.json();

      setIsLoading(false);
      setData(json);
    } catch (er) {
      setIsLoading(false);
    }
  };

  useEffect(() => {
    fetchData();
  }, []);

  return {
    data,
    isLoading,
  };
};
