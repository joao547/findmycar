import { useEffect, useState } from 'react';
import { api } from '../../../service/api';
import { GeneralOption } from './signupMultiStepSelectOptions';

type ServicosConsulta = {
  id: number;
  nome: string;
};

function useSelectData() {
  const [servicosBuscador, setServicosBuscador] = useState<
    Array<GeneralOption>
  >([]);
  const [carroMarcas, setCarroMarcas] = useState<Array<GeneralOption>>([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const [responseServicos, responseMarcas] = await Promise.all([
          api.get<Array<ServicosConsulta>>('/api/consultas/servicos_buscador'),
          api.get<Array<ServicosConsulta>>('/api/consultas/carro_marcas'),
        ]);

        const servicosBuscadorOptions = responseServicos.data.map((servico) => {
          return {
            label: servico.nome,
            value: servico.id,
          };
        });

        const servicosMarcasOptions = responseMarcas.data.map((marca) => {
          return {
            label: marca.nome,
            value: marca.id,
          };
        });
        setServicosBuscador(servicosBuscadorOptions);
        setCarroMarcas(servicosMarcasOptions);
        setLoading(false);
      } catch (error) {
        console.error('Erro ao buscar dados:', error);
        setLoading(false);
      }
    };

    fetchData();
  }, []);

  return { servicosBuscador, carroMarcas, loading };
}

export default useSelectData;
