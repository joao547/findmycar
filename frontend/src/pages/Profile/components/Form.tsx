import { FormEvent, PropsWithChildren } from 'react'

interface FormProps<T> {
	onSubmit: (obj: T) => void;
	onBlur: () => void;
}

export const Form = <T,>({children, onSubmit, onBlur}: PropsWithChildren<FormProps<T>>) => {
	const handleSubmit = (ev: FormEvent<HTMLFormElement>) => {
		ev.preventDefault();

		const data = new FormData(ev.currentTarget)
		const values = Object.fromEntries(data.entries());

		onSubmit(values as T)
	}

	return (
		<form 
			onSubmit={handleSubmit}
			onBlur={onBlur}
			className="w-full p-2 m-2 h-full"
		>
			{children}
		</form>
	)
}
