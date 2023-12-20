type City = {
  id: number;
  nome: string;
  municipio: {
    microrregiao: {
      mesorregiao: {
        UF: {
          id: number;
          sigla: string;
        };
      };
    };
  };
};

function useRegisterStep() {
  const handlerLoadCitiesOptions = async (uf: string) => {
    try {
      const response = await fetch(
        `https://servicodados.ibge.gov.br/api/v1/localidades/estados/${uf}/distritos`,
      );
      const data = await response.json();

      const options = data.map((c: City) => {
        return {
          label: c.nome,
          value: c.id,
          uf: c.municipio.microrregiao.mesorregiao.UF.sigla,
        };
      });

      return options;
    } catch (error) {
      console.error('Erro ao buscar dados da API:', error);
    }
  };

  return {
    handlerLoadCitiesOptions,
  };
}

export default useRegisterStep;
