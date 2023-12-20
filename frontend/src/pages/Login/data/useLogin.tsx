import { api } from '../../../service/api';
import { CityOpt, GeneralOption } from './signupMultiStepSelectOptions';

export type MultiStepDataInput = {
  nome: string;
  email: string;
  senha: string;
  tipo: string;
  locais: Array<CityOpt>;
  areasBuscador: Array<GeneralOption>;
  areasConsultor: Array<GeneralOption>;
};

export const mapRequestBody = (body: MultiStepDataInput) => {
  const result = {
    nome: body.nome,
    email: body.email,
    senha: body.senha,
    tipo: body.tipo,
    locais: body.locais.map((location) => {
      return {
        ibgeCode: location.value,
        name: location.label,
        uf: location.uf,
      };
    }),
    areasBuscador: body.areasBuscador.map((areaBuscador) => areaBuscador.value),
    areasConsultor: body.areasConsultor.map(
      (areaConsultor) => areaConsultor.value,
    ),
  };

  console.log(result);
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

  const handleCreateUser = async (user: MultiStepDataInput) => {
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
