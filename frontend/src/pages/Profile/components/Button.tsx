import { PropsWithChildren } from "react";

export const Button = ({children}: PropsWithChildren) => {
	return (
		<div className="w-full flex align-center justify-end  px-3 mb-6 md:mb-0">
			<button 
				type="submit"
				className="font-bold bg-amber-500 hover:bg-amber-700 border-amber-500 hover:border-amber-700 text-sm border-4 text-white py-2 px-3 rounded place-self-center"
			>
				{children}
			</button>
		</div>
	)
}
