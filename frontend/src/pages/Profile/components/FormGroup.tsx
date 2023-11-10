import { PropsWithChildren } from "react"

export const FormGroup = ({children}: PropsWithChildren) => {

	return (
		<div className="flex flex-wrap mb-12">{children}</div>
	)
}
