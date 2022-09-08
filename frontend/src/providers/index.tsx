import { ReactNode } from 'react';
import { BrowserRouter } from 'react-router-dom';

import AuthProvider from '../contexts/auth';

interface ProviderProps {
  children: ReactNode;
}

export function Provider({ children }: ProviderProps) {
  return (
    <AuthProvider>
      <BrowserRouter>{children}</BrowserRouter>
    </AuthProvider>
  );
}
