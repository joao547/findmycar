export function ImagemBase64({ base64String }: { base64String: string }) {
  if (!base64String) {
    return <div>String Base64 não fornecida.</div>;
  }

  const imageUrl = `data:image/jpeg;base64,${base64String}`;

  return (
    <div className='w-16 h-16 inline-block rounded-full overflow-hidden'>
    <img
      src={imageUrl}
      alt='avatar_usuario'
      className='w-full h-full object-cover rounded-full'
    />
  </div>
  );
}


