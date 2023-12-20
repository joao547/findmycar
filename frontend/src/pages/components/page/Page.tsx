import jwt_decode from 'jwt-decode';
import { useEffect, useState } from 'react';
import { PageLayout } from './components/PageLayout/PageLayout';
import { Outlet } from 'react-router-dom';

type User = {
  email: string;
  exp: number;
};
export function Page() {
  const [userToken, setUserToken] = useState<User>();

  useEffect(() => {
    const token = localStorage.getItem('@token');
    if (token) {
      const decode = jwt_decode(token);
      setUserToken(decode as User);
    }
  }, []);

  return (
    <PageLayout>
      <Outlet />
    </PageLayout>
  );
}
