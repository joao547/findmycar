import { Aside } from './components/Aside/Aside';
import { MainBodyForm } from './components/MainBodyForm/MainBodyForm';

export default function LoginPage() {
  return (
    <div className='flex'>
      <Aside />
      <main className='flex-1 flex flex-col items-center'>
        <MainBodyForm />
      </main>
    </div>
  );
}
