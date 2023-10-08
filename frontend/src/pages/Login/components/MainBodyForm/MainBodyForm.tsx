import { useState } from 'react';

import { Authentication } from '../Authentication/Authentication';
import { Signup } from '../Signup/Signup';

export function MainBodyForm() {
  const [formType, setFormType] = useState<'login' | 'signup'>('login');

  if (formType === 'login') {
    return <Authentication handleCreateAccount={setFormType} />;
  }

  return <Signup />;
}
