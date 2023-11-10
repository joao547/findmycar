import { PropsWithChildren } from "react";

export const Button = ({children}: PropsWithChildren) => {
	return (
		<button 
			type="submit"
			className="flex-shrink-0 bg-amber-500 hover:bg-amber-700 border-amber-500 hover:border-amber-700 text-sm border-4 text-white py-2 px-3 rounded"
		>
			{children}
		</button>
	)
}
