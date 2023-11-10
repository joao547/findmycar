
interface InputProps {
	name: string;
	type: "text" | "email" | "password" | "number";
	label: string;
	value?: string | number;
	error?: null | string;
}

export const Input = ({name, type, label, value, error = ''}: InputProps) => {
	const hasError = !!error;

	return (
		<div className="w-full md:w-1/2 px-3 mb-6 md:mb-0">
			<label 
				className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
				htmlFor={name}
			>{label}</label>
			
			<input 
				name={name}
				type={type}
				aria-label={name}
				defaultValue={value || ""}
				className={`
					appearance-none bg-transparent border-b-2
					${ hasError ? 'border-red-500' : 'border-amber-500' }
					w-full text-gray-700 mr-3 py-1 px-2 leading-tight focus:outline-none
				`}
			/>

			{hasError && <p className="text-xs italic text-red-500">{error}</p>}
		</div>
	)
}

