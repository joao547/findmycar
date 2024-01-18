/* eslint-disable max-len */
import { PropsWithChildren } from 'react';

type ModalProps = {
  isOpen: boolean;
  onClose: () => void;
};

export default function Modal({
  isOpen,
  onClose,
  children,
}: PropsWithChildren<ModalProps>) {
  function handleButtonClicked() {
    onClose();
  }
  if (isOpen) {
    return (
      <div className='fixed inset-0 bg-gray-500 bg-opacity-75 flex items-center justify-center transition-opacity duration-300'>
        <div className='bg-white p-8 max-w-md w-full mx-auto rounded shadow-lg transition-transform duration-300 transform'>
          <button
            className='absolute top-4 right-4 text-gray-700 hover:text-gray-800'
            onClick={handleButtonClicked}
          >
            X
          </button>
          {children}
        </div>
      </div>
    );
  }

  return null;
}
