import { ThemeProvider } from '@mui/material';
import { ReactNode } from 'react';
import { BrowserRouter } from 'react-router-dom';

import AuthProvider from '../contexts/auth';

import { theme } from '../styles';

interface ProviderProps {
  children: ReactNode;
}

export function Provider({ children }: ProviderProps) {
  return (
    <ThemeProvider theme={theme}>
      <AuthProvider>
        <BrowserRouter>{children}</BrowserRouter>
      </AuthProvider>
    </ThemeProvider>
  );
}
