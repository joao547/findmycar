/* eslint-disable max-len */
import React, { useState } from 'react';

const ToggleSwitch = () => {
  const [isChecked, setIsChecked] = useState(false);

  const handleToggle = () => {
    console.log(isChecked);
    setIsChecked(!isChecked);
  };

  return (
    <label className='flex items-center cursor-pointer gap-4'>
      <div className='text-gray-700 text-sm'>Consultor Mecanico</div>
      <div
        className={`relative ${
          isChecked ? 'bg-orange-500' : 'bg-gray-300'
        } rounded-full shadow-inner w-14 h-7 flex items-center p-1 transition-background`}
      >
        <input
          type='checkbox'
          className='hidden'
          checked={isChecked}
          onChange={handleToggle}
        />
        <div
          className={`toggle-dot w-6 h-6 bg-white rounded-full shadow-md transform transition-transform duration-300 ${
            isChecked ? 'translate-x-full bg-orange-500' : ''
          }`}
        ></div>
      </div>
    </label>
  );
};

export default ToggleSwitch;
