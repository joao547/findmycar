
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
				className="block uppercase tracking-wide text-gray-500 text-xs font-bold mb-2"
				htmlFor={name}
			>{label}</label>
			
			<input 
				name={name}
				type={type}
				aria-label={name}
				defaultValue={value || ""}
				className={`
					w-full appearance-none bg-white border-2 rounded
					text-gray-500 mr-3 py-4 px-3 leading-tight focus:outline-none 
					${hasError ? 'border-red-500' : 'border-white' }
				`}
			/>

			{hasError && <p className="text-xs italic text-red-500">{error}</p>}
		</div>
	)
}

