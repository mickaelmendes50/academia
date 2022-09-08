import { useState, createContext, useMemo } from 'react';
import axios from 'axios';

export const AuthContext = createContext({});

export default function AuthProvider({ children }: any) {
  const [user, setUser] = useState('');
  const [mensagem, setMensagem] = useState({
    err: false,
    msg: '',
  });

  async function login(email: any, senha: any) {
    const dados = {
      email,
      senha,
    };

    try {
      const { data } = await axios.post(
        'http://localhost:8000/user/login',
        dados,
      );
      setUser(data.token);
      localStorage.setItem('AuthToken', data.token);
      localStorage.setItem('User', email);

      return true;
    } catch (error: any) {
      const msg = error.response.data.err;
      setMensagem({
        err: true,
        msg,
      });

      return false;
    }
  }

  const value = useMemo(
    () => ({
      login,
      mensagem,
      user,
      setUser,
    }),
    [login, mensagem, user, setUser],
  );

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
}
