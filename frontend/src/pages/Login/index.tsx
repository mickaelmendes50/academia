import { useState, useContext, FormEvent } from 'react';
import { Link as RouterLink, useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';
import {
  Box,
  Button,
  Container,
  Typography,
  Link,
  FormControl,
  InputLabel,
  FormControlLabel,
  Checkbox,
} from '@mui/material';
import FitnessCenterRoundedIcon from '@mui/icons-material/FitnessCenterRounded';

import { AuthContext } from '../../contexts/auth';
import { TextInput } from '../../components/Form';

export default function Login() {
  const navigate = useNavigate();

  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [rememberMe, setRememberMe] = useState(false);
  const [loading, setLoading] = useState(false);

  const { login }: any = useContext(AuthContext);

  async function handleSubmit(event: FormEvent) {
    event.preventDefault();

    try {
      setLoading(true);

      await login({ email, password, rememberMe });
      toast.success('Login realizado com sucesso!');

      navigate('/students');
    } catch (error) {
      toast.error('Falha ao realizar login!');
    } finally {
      setLoading(false);
    }
  }

  return (
    <Box
      component="main"
      padding="80px 32px"
      width="100%"
      minHeight="100vh"
      display="flex"
      flexDirection="column"
      alignItems="center"
    >
      <FitnessCenterRoundedIcon sx={{ color: '#fb8500', fontSize: '64px' }} />
      <Typography
        component="h1"
        variant="h5"
        marginTop="16px"
        marginBottom="8px"
      >
        Entre em sua conta
      </Typography>
      <Typography>
        Ou{' '}
        <Link
          component={RouterLink}
          to="/cadastro"
          variant="body1"
          color="#fb8500"
          fontWeight="700"
          sx={{ textDecoration: 'none' }}
        >
          cadastre-se
        </Link>
      </Typography>

      <Container
        component="form"
        onSubmit={handleSubmit}
        maxWidth="xs"
        sx={{
          marginTop: '32px',
          borderRadius: '16px',
          background: '#ffffff',
          boxShadow: '0px 4px 6px -1px rgba(0, 0, 0, 0.1)',
        }}
        style={{ padding: '36px' }}
      >
        <FormControl variant="standard" fullWidth sx={{ marginBottom: '16px' }}>
          <InputLabel shrink htmlFor="email">
            E-mail
          </InputLabel>
          <TextInput
            id="email"
            name="email"
            autoComplete="email"
            required
            onChange={event => setEmail(event.target.value)}
          />
        </FormControl>

        <FormControl variant="standard" fullWidth sx={{ marginBottom: '16px' }}>
          <InputLabel shrink htmlFor="password">
            Senha
          </InputLabel>
          <TextInput
            id="password"
            name="password"
            type="password"
            required
            onChange={event => setPassword(event.target.value)}
          />
        </FormControl>

        <FormControlLabel
          control={
            <Checkbox
              checked={rememberMe}
              onChange={event => setRememberMe(event.target.checked)}
            />
          }
          label="Lembre-se de mim"
          sx={{ marginBottom: '32px' }}
        />

        <Button
          type="submit"
          variant="contained"
          style={{ background: '#FB8500' }}
          disabled={loading}
          fullWidth
        >
          {loading ? 'Carregando...' : 'Entrar'}
        </Button>
      </Container>
    </Box>
  );
}
