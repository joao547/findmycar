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

export type CityOpt = {
  label: string;
  value: number;
  uf: string;
};

type MarcaOption = {
  readonly value: string;
  readonly label: string;
};

type MecanicaOption = {
  readonly value: string;
  readonly label: string;
};

type GroupedOption = {
  readonly label: string;
  readonly options: readonly MarcaOption[] | readonly MecanicaOption[];
};

const statesOptions = [
  { label: 'Acre', value: 'AC' },
  { label: 'Alagoas', value: 'AL' },
  { label: 'Amapá', value: 'AP' },
  { label: 'Amazonas', value: 'AM' },
  { label: 'Bahia', value: 'BA' },
  { label: 'Ceará', value: 'CE' },
  { label: 'Distrito Federal', value: 'DF' },
  { label: 'Espírito Santo', value: 'ES' },
  { label: 'Goiás', value: 'GO' },
  { label: 'Maranhão', value: 'MA' },
  { label: 'Mato Grosso', value: 'MT' },
  { label: 'Mato Grosso do Sul', value: 'MS' },
  { label: 'Minas Gerais', value: 'MG' },
  { label: 'Pará', value: 'PA' },
  { label: 'Paraíba', value: 'PB' },
  { label: 'Paraná', value: 'PR' },
  { label: 'Pernambuco', value: 'PE' },
  { label: 'Piauí', value: 'PI' },
  { label: 'Rio de Janeiro', value: 'RJ' },
  { label: 'Rio Grande do Norte', value: 'RN' },
  { label: 'Rio Grande do Sul', value: 'RS' },
  { label: 'Rondônia', value: 'RO' },
  { label: 'Roraima', value: 'RR' },
  { label: 'Santa Catarina', value: 'SC' },
  { label: 'São Paulo', value: 'SP' },
  { label: 'Sergipe', value: 'SE' },
  { label: 'Tocantins', value: 'TO' },
];

const marcaOptions: readonly MarcaOption[] = [
  { value: 'BMW', label: 'BMW' },
  { value: 'Chevrolet', label: 'Chevrolet' },
  { value: 'Citroen', label: 'Citroen' },
  { value: 'FIAT', label: 'FIAT' },
  { value: 'Ford', label: 'Ford' },
  { value: 'Honda', label: 'Honda' },
  { value: 'Hyundai', label: 'Hyundai' },
  { value: 'Peugout', label: 'Peugout' },
  { value: 'Renault', label: 'Renault' },
  { value: 'Volkswagen', label: 'Volkswagen' },
];

const mecanicaOptions: readonly MecanicaOption[] = [
  { value: '3 cilindros', label: '3 cilindros' },
  { value: 'Automático', label: 'Automático' },
  { value: 'Manual', label: 'Manual' },
  { value: 'Motor turbo', label: 'Motor turbo' },
];

const groupedOptions: readonly GroupedOption[] = [
  {
    label: 'Marca',
    options: marcaOptions,
  },
  {
    label: 'Mecanica',
    options: mecanicaOptions,
  },
];

const optionsBuscador = [
  { label: 'carros antigos', value: 'carros antigos' },
  { label: 'carros raros', value: 'carros raros' },
  { label: 'carros comuns', value: 'carros comuns' },
  { label: 'carros econômicos', value: 'carros econômicos' },
  { label: 'carros esportivos', value: 'carros esportivos' },
  { label: 'carros zero', value: 'carros zero' },
];

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
    statesOptions,
    optionsBuscador,
    groupedOptions,
  };
}

export default useRegisterStep;
