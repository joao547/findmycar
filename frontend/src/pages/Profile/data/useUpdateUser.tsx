import { useState } from "react"
import { api } from "../../../service/api";

interface UpdateUserFunctionProps {
	nome: string;
	senha: string;
	tipo: string;
	precoDoServico: string;
	areaDeAtuacao: string;
}

export const useUpdateUser = () => {
	const [loading, setLoading] = useState(false);

	const updateUser = async ({
		nome,
		senha,
		tipo,
		precoDoServico,
		areaDeAtuacao
	}: UpdateUserFunctionProps) => {
		const token = localStorage.getItem('@token')
		const user = {
			nome, senha, tipo, precoDoServico, areaDeAtuacao
		}

		await api.put('/api/user', user, {
			headers: {
				'Authorization': `Bearer ${token}`
			}
		})

		setLoading(false)
	}

	return {
		loading,
		updateUser
	}
}
