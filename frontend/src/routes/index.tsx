import { Route, Routes } from 'react-router-dom';

import Login from '../pages/Login';
import Cadastro from '../pages/Cadastro';
import CadastroStudent from '../pages/CadastroUsuario';
import Dashboard from '../pages/Dashboard';
import PrivateRoutes from './PrivateRoutes';

import { useAuthContext } from '../contexts/auth';

export function AppRoutes() {
  const { user } = useAuthContext();

  return (
    <Routes>
      <Route path="/" element={<Login />} />
      <Route path="/cadastro" element={<Cadastro />} />
      <Route
        path="/dashboard"
        element={
          <PrivateRoutes user={user} redirectPath="/">
            <Dashboard />
          </PrivateRoutes>
        }
      />
      <Route path="/cadastro/students" element={<CadastroStudent />} />
    </Routes>
  );
}
