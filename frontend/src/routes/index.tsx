import { Route, Routes } from 'react-router-dom';

import Login from '../pages/Login';
import Cadastro from '../pages/Cadastro';
import CadastroStudent from '../pages/CadastroUsuario';
import Students from '../pages/Students';
import PrivateRoutes from './PrivateRoutes';

import { useAuthContext } from '../contexts/auth';

export function AppRoutes() {
  const { user } = useAuthContext();

  return (
    <Routes>
      <Route path="/" element={<Login />} />
      <Route path="/cadastro" element={<Cadastro />} />
      <Route
        path="/students"
        element={
          <PrivateRoutes user={user} redirectPath="/">
            <Students />
          </PrivateRoutes>
        }
      />
      <Route path="/cadastro/students" element={<CadastroStudent />} />
    </Routes>
  );
}
