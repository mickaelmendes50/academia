import { useState, createContext, useMemo, useContext } from 'react';
import axios from 'axios';

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
      password,
    };

    const { data } = await axios.post(
      'http://localhost:8000/user/login',
      payload,
    );

    setUser(email);
    localStorage.setItem('@academia:auth_token', data.token);

    if (rememberMe) {
      localStorage.setItem('@academia:user', email);
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
