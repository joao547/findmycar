import { PropsWithChildren } from "react"

export const FormGroup = ({children}: PropsWithChildren) => {

	return (
		<div className="flex flex-wrap -mx-3 mb-12">{children}</div>
	)
}
