import { api } from '../../../service/api';
import { MultiStepData } from '../components/SignupMultiStep/StepBody';

export const mapRequestBody = (body: MultiStepData) => {
  const result = {
    nome: body.name,
    email: body.email,
    senha: body.senha,
    tipo: body.tipo,
    precoDoServico: body.precoDoServico ? body.precoDoServico : undefined,
    areaDeAtuacao: body.areaDeAtuacao ? body.areaDeAtuacao : undefined,
    disponibilidade: body.disponibilidade ? body.disponibilidade : undefined,
  };

  return result;
};

function useLogin() {
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

  const handleCreateUser = async (user: MultiStepData) => {
    try {
      const body = mapRequestBody(user);
      const { data } = await api.post('/api/auth/criar', body);

      return data;
    } catch (err) {
      throw new Error(err as string);
    }
  };

  return { handleCreateUser, handleLogin };
}

export default useLogin;
