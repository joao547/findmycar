import { ChangeEvent, useState } from 'react';
import { TextInput } from '../../../../../../components';
import avatarSrc from '../../../../../../assets/avatarImage.png';

type AvatarInputProps = {
  handleUploadUserAvatar: (
    key: string,
    value: string | number | object,
  ) => void;
};

/* eslint-disable max-len */
export function AvatarInput({ handleUploadUserAvatar }: AvatarInputProps) {
  const [avatarImg, setAvatarImg] = useState<string>(avatarSrc);

  function handleFileChange(event: ChangeEvent<HTMLInputElement>) {
    const file = event.target.files?.[0];

    if (file) {
      const url = URL.createObjectURL(file);
      setAvatarImg(url);
      handleUploadUserAvatar('avatar', file);
    }
  }
  return (
    <TextInput.layout>
      <div className='flex items-center space-x-6'>
        <div className='shrink-0'>
          <img
            className='h-16 w-16 object-cover rounded-full'
            src={avatarImg}
            alt='profile'
          />
        </div>
        <label className='block'>
          <span className='sr-only'>Choose profile photo</span>
          <input
            type='file'
            onChange={handleFileChange}
            className='
                    block w-full text-sm text-slate-500
                    file:mr-4 file:py-2 file:px-4
                    file:rounded-full file:border-0
                    file:text-sm file:font-semibold
                    file:bg-violet-50 file:text-violet-700
                    hover:file:bg-violet-100
      '
          />
        </label>
      </div>
    </TextInput.layout>
  );
}
