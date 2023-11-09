import jwt_decode from 'jwt-decode';
import { useEffect, useState } from 'react';
import { HomeLayout } from './components/HomeLayout/HomeLayout';
import { Outlet } from 'react-router-dom';

type User = {
  email: string;
  exp: number;
};
export function Home() {
  const [userToken, setUserToken] = useState<User>();

  useEffect(() => {
    const token = localStorage.getItem('@token');
    if (token) {
      const decode = jwt_decode(token);
      setUserToken(decode as User);
    }
  }, []);

  return (
    <HomeLayout>
      <Outlet />
    </HomeLayout>
  );
}
