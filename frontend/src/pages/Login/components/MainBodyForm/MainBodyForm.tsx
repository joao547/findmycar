import { useState } from 'react';

import { Authentication } from '../Authentication/Authentication';
import { SignupMultiStep } from '../SignupMultiStep';

export function MainBodyForm() {
  const [formType, setFormType] = useState<'login' | 'signup'>('login');

  if (formType === 'login') {
    return <Authentication handleCreateAccount={setFormType} />;
  }

  return <SignupMultiStep handleAuthenticateUser={setFormType} />;
}
