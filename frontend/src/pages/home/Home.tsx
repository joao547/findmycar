import jwt_decode from 'jwt-decode';
import { useEffect, useState } from 'react';

type User = {
  email: string;
  exp: number;
};

export const Home = () => {
  const [userToken, setUserToken] = useState<User>();
  useEffect(() => {
    const token = localStorage.getItem('@token');
    if (token) {
      const decode = jwt_decode(token);
      setUserToken(decode as User);
    }
  }, []);

  return (
    <main>
      <h1>Bem vindo</h1>
      <p>{userToken?.email}</p>
    </main>
  );
};
