import { Avatar, Box, Button, Link, Typography } from '@mui/material';
import FitnessCenterIcon from '@mui/icons-material/FitnessCenter';
import ImportContactsIcon from '@mui/icons-material/ImportContacts';
import LogoutIcon from '@mui/icons-material/Logout';
import { ReactNode } from 'react';
import { Link as RouterLink, useLocation } from 'react-router-dom';
import { toast } from 'react-toastify';

import { useAuthContext } from '../../contexts/auth';

interface LayoutProps {
  children: ReactNode;
}

export function Layout({ children }: LayoutProps) {
  const { pathname } = useLocation();

  const { user, logout } = useAuthContext();

  function handleLogout() {
    logout();

    toast.success('Até mais!');
  }

  return (
    <Box
      component="main"
      width="100%"
      minHeight="100vh"
      display="grid"
      gridTemplateColumns="250px 1fr"
    >
      <Box
        component="aside"
        padding="24px"
        width="100%"
        bgcolor="#ffffff"
        borderRadius="0px 20px 20px 0px"
        boxShadow="box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25)"
        display="flex"
        flexDirection="column"
      >
        <Box display="flex" gap="16px" alignItems="center">
          <Avatar sx={{ width: '56px', height: '56px', background: '#fb8500' }}>
            {user}
          </Avatar>

          <Box>
            <Typography component="h1" variant="h6" fontSize="16px">
              {user}
            </Typography>
            <Typography fontSize="12px">Administrador</Typography>
          </Box>
        </Box>
        <Box component="nav" marginTop="32px" display="grid" gap="12px">
          <Link
            component={RouterLink}
            to="/students"
            variant="body1"
            padding="12px"
            borderRadius="8px"
            bgcolor={pathname.includes('students') ? '#fb8500' : '#ffffff'}
            color={pathname.includes('students') ? '#ffffff' : '#fb8500'}
            fontWeight="700"
            display="flex"
            gap="12px"
            alignItems="center"
            sx={{ textDecoration: 'none' }}
          >
            <ImportContactsIcon />
            <Typography
              variant="body1"
              color={pathname.includes('students') ? '#ffffff' : '#fb8500'}
              fontWeight="700"
            >
              Alunos
            </Typography>
          </Link>
          <Link
            component={RouterLink}
            to="/exercises"
            variant="body1"
            padding="12px"
            borderRadius="8px"
            bgcolor={pathname.includes('exercises') ? '#fb8500' : '#ffffff'}
            color={pathname.includes('exercises') ? '#ffffff' : '#fb8500'}
            fontWeight="700"
            display="flex"
            gap="12px"
            alignItems="center"
            sx={{ textDecoration: 'none' }}
          >
            <FitnessCenterIcon />
            <Typography
              variant="body1"
              color={pathname.includes('exercises') ? '#ffffff' : '#fb8500'}
              fontWeight="700"
            >
              Exercícios
            </Typography>
          </Link>
        </Box>

        <Button
          size="large"
          variant="text"
          startIcon={<LogoutIcon />}
          onClick={handleLogout}
          sx={{
            marginTop: 'auto',
            color: '#fb8500',
            justifyContent: 'flex-start',
          }}
        >
          Sair
        </Button>
      </Box>
      <Box component="section" padding="64px">
        {children}
      </Box>
    </Box>
  );
}
