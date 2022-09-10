import { Route, Routes } from 'react-router-dom';

import Login from '../pages/Login';
import Cadastro from '../pages/Cadastro';
import Students from '../pages/Students';
import CreateStudent from '../pages/Students/Create';
import Exercises from '../pages/Exercises';
import CreateExercise from '../pages/Exercises/Create';
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
      <Route
        path="/students/create"
        element={
          <PrivateRoutes user={user} redirectPath="/">
            <CreateStudent />
          </PrivateRoutes>
        }
      />
      <Route
        path="/exercises"
        element={
          <PrivateRoutes user={user} redirectPath="/">
            <Exercises />
          </PrivateRoutes>
        }
      />
      <Route
        path="/exercises/create"
        element={
          <PrivateRoutes user={user} redirectPath="/">
            <CreateExercise />
          </PrivateRoutes>
        }
      />
    </Routes>
  );
}
