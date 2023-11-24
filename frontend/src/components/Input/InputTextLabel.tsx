import { PropsWithChildren } from "react";
import { ComponentProps } from 'react';

export function InputTextLabel({children, ...props}: PropsWithChildren<ComponentProps<'label'>>) {
	return (
		<label
			{...props}
			className="block uppercase tracking-wide text-gray-500 text-xs font-bold mb-2"
		>
			{children}
		</label>
	)
}
