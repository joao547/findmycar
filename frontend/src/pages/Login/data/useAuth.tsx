import { api } from '../../../service/api';

function useAuth() {
  const handleLogin = async (email: string, password: string) => {
    try {
      const { data } = await api.post('/api/auth/login', {
        email,
        password,
      });

      return data;
    } catch (err) {
      throw new Error(err as string);
    }
  };

  return { handleLogin };
}

export default useAuth;
