import { Input } from "./components/Input";
import { Form } from "./components/Form";
import { FormGroup } from "./components/FormGroup";
import { useGetUserData } from "./data/useGetUserData";
import { Button } from "./components/Button";
import { useUpdateUser } from "./data/useUpdateUser";
import { toast } from 'react-toastify';
import { useState } from "react";

const MINIMUM_PASS_SIZE = 8;

const initialErrors = {
	nome: null,
	senha: null,
	confirmarSenha: null,
	precoDoServico: null,
	areaDeAtuacao: null 
}

interface ErrorType {
	nome: string | null,
	senha: string | null,
	confirmarSenha: string | null,
	precoDoServico: string | null,
	areaDeAtuacao: string | null 
}

interface UserEdit {
	nome: string;
	senha: string;
	email: string;
	precoDoServico: string;
	areaDeAtuacao: string;
	confirmarSenha: string;
}

export function Profile () {
	const [errorSchema, setErrorSchema] = useState<ErrorType>(initialErrors)

	const { loading, userData } = useGetUserData()
	const { updateUser } = useUpdateUser();

	if (loading) return <h2>Loading</h2>
	if (!userData) return <h2>Data not found</h2> 

	const handleSubmit = ({ nome, senha, precoDoServico, areaDeAtuacao, confirmarSenha }: UserEdit) => {
		
		if (senha !== "") {
			if (senha !== confirmarSenha) {
				setErrorSchema(err => ({...err, senha: 'Senhas não coincidem', confirmarSenha: 'Senhas não coincidem'}))
				return;
			}

			if (senha.length < MINIMUM_PASS_SIZE) {
				setErrorSchema(err => ({...err, senha: `Senha deve ter mais de ${MINIMUM_PASS_SIZE} caracteres`}))
				return;
			} 
		}

		toast.promise(updateUser({ nome, senha, tipo: userData.tipo, precoDoServico, areaDeAtuacao }), {
      success: 'Dados atualizados',
      pending: 'atualizando...',
      error: 'Erro ao atualizar',
		})
	}

	const resetErrors = () => setErrorSchema(initialErrors);

	return (
		<>
			<Form<UserEdit>
				onSubmit={handleSubmit}
				onBlur={resetErrors}
			>
				<FormGroup>
					<Input 
						name="nome"
						label="Nome"
						type="text"
						value={userData?.nome}
					/>
				</FormGroup>

				{userData && userData.tipo === 'CONSULTOR' && (
					<FormGroup>
						<Input 
							name="precoDoServico"
							label="Preco de Servico"
							type="number"
							value={userData?.precoServico}
						/>

						<Input 
							name="areaDeAtuacao"
							label="Area de atuacao"
							type="text"
							value={userData.areaAtuacao}
						/>
					</FormGroup>
				)}

				<FormGroup>
					<Input 
						name="senha"
						label="Senha"
						type="password"
						error={errorSchema.senha}
					/>

					<Input 
						name="confirmarSenha"
						label="Confirmar senha"
						type="password"
						error={errorSchema.confirmarSenha}
					/>
				</FormGroup>

				<FormGroup>
					<Button>
						Atualizar
					</Button>
				</FormGroup>
			</Form>
		</>
	)
}
