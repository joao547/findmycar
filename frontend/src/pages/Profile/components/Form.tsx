import { FormEvent, PropsWithChildren } from 'react'

interface FormProps {
	onSubmit: (obj: object) => void;
	onBlur: () => void;
}

export const Form = ({children, onSubmit, onBlur}: PropsWithChildren<FormProps>) => {
	const handleSubmit = (ev: FormEvent<HTMLFormElement>) => {
		ev.preventDefault();

		const data = new FormData(ev.currentTarget)
		const values = Object.fromEntries(data.entries());

		onSubmit(values)
	}

	return (
		<form 
			onSubmit={handleSubmit}
			onBlur={onBlur}
			className="w-full px-3"
		>
			{children}
		</form>
	)
}
