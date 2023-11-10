import { api } from '../../../service/api';
import { useEffect, useState } from "react"

type ConsultorType = {
	id: number;
	nome: string;
	email: string;
	precoServico: number;
	tipo: "CONSULTOR";
	areaAtuacao: string;
}

type ClienteType = {
	id: number;
	nome: string;
	email: string;
	tipo: "CLIENTE";
}

export const useGetUserData = () => {
	const [userData, setUserData] = useState<null | ClienteType | ConsultorType>(null);
	const [loading, setLoading] = useState(true);

	useEffect(() => {
		const findUserInfo = async () => {
    	const token = localStorage.getItem('@token');

			const { data } = await api.get<ClienteType | ConsultorType>('/api/user/me', {
				headers: {
					'Authorization': `Bearer ${token}`
				}
			});
			setUserData(data);
			setLoading(false)
		}

		findUserInfo();
	}, [])

	return {
		userData,
		loading
	}
}
