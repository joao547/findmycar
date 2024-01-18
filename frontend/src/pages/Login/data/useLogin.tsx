import { api } from '../../../service/api';
import { CityOpt, GeneralOption } from './signupMultiStepSelectOptions';

export type MultiStepDataInput = {
  avatar: File;
  nome: string;
  email: string;
  senha: string;
  tipo: string;
  precoServicoBuscador: number;
  precoServicoMecanico: number;
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
    precoServicoBuscador: body.precoServicoBuscador,
    precoServicoMecanico: body.precoServicoMecanico,
    locais: body.locais.map((location) => {
      return {
        ibgeCode: location.value,
        name: location.label,
        uf: location.uf,
      };
    }),
    carroMarcas: body.areasBuscador.map((areaBuscador) => {
      return {
        id: areaBuscador.value,
        nome: areaBuscador.label,
      };
    }),
    servicosBuscador: body.areasConsultor.map((areasConsultor) => {
      return {
        id: areasConsultor.value,
        nome: areasConsultor.label,
      };
    }),
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

  const handleCreateUser = async (user: MultiStepDataInput) => {
    try {
      const body = mapRequestBody(user);

      console.log(user.avatar);

      const formData = new FormData();
      formData.append('file', user.avatar);
      formData.append('pessoaJson', JSON.stringify(body));

      const response = await api.post('/api/auth/criar', formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
        },
      });

      console.log(response.data);

      return response.data;
    } catch (err) {
      console.error('Error:', err);
      throw new Error(err.message || 'An error occurred during user creation.');
    }
  };

  const handleCreateUserold = async (user: MultiStepDataInput) => {
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
