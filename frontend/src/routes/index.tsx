import { Route, Routes } from 'react-router-dom';

import Login from '../pages/Login';
import Cadastro from '../pages/Cadastro';
import CadastroStudent from '../pages/CadastroUsuario';
import Dashboard from '../pages/Dashboard';

export function AppRoutes() {
  return (
    <Routes>
      <Route path="/" element={<Login />} />
      <Route path="/cadastro" element={<Cadastro />} />
      <Route
        path="/dashboard"
        element={
          <Dashboard />
          // <PrivateRoutes user={user} redirectPath={'/'}>

          // </PrivateRoutes>
        }
      />
      <Route path="/cadastro/students" element={<CadastroStudent />} />
    </Routes>
  );
}
