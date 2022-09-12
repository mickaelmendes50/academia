import { useState, createContext, useMemo, useContext } from 'react';

import { axiosAuth } from '../services';

type LoginArgs = {
  email: string;
  password: string;
  rememberMe: boolean;
};

interface AuthContextProps {
  user: string;
  login: (args: LoginArgs) => void;
  logout: () => void;
}

export const AuthContext = createContext({} as AuthContextProps);

export const useAuthContext = () => useContext(AuthContext);

export default function AuthProvider({ children }: any) {
  const [user, setUser] = useState(
    () => localStorage.getItem('@academia:user') || '',
  );

  async function login({ email, password, rememberMe }: LoginArgs) {
    const payload = {
      email,
      senha: password,
    };

    const { data } = await axiosAuth.post('/user/login', payload);

    setUser(data.nome);
    localStorage.setItem('@academia:auth_token', data.token);

    if (rememberMe) {
      localStorage.setItem('@academia:user', data.nome);
    }
  }

  function logout() {
    setUser('');
    localStorage.removeItem('@academia:auth_token');
    localStorage.removeItem('@academia:user');
  }

  const value = useMemo(
    () => ({
      user,
      login,
      logout,
    }),
    [user, login, logout],
  );

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
}
