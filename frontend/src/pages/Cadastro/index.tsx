import React, { useState } from 'react';
import { Link as RouterLink, useNavigate } from 'react-router-dom';
import {
  Box,
  Button,
  Container,
  Typography,
  Link,
  FormControl,
  InputLabel,
} from '@mui/material';
import FitnessCenterRoundedIcon from '@mui/icons-material/FitnessCenterRounded';
import axios from 'axios';

import { TextInput } from '../../components/Form';

export default function Cadastro() {
  const navigate = useNavigate();
  const [nome, setNome] = useState('');
  const [email, setEmail] = useState('');
  const [senha, setSenha] = useState('');
  const [token, setToken] = useState('');

  function reset() {
    setEmail('');
    setSenha('');
  }
  async function handleSubmit() {
    const dados = {
      nome,
      email,
      senha,
      token,
    };
    try {
      await axios.post('http://localhost:8000/users/create', dados);

      reset();
      navigate('/');
    } catch (error: any) {
      reset();
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
        Crie uma nova conta
      </Typography>
      <Typography>
        Ou{' '}
        <Link
          component={RouterLink}
          to="/"
          variant="body1"
          color="#fb8500"
          fontWeight="700"
          sx={{ textDecoration: 'none' }}
        >
          entre agora
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
          <InputLabel shrink htmlFor="name">
            Nome
          </InputLabel>
          <TextInput
            id="name"
            name="name"
            required
            onChange={event => setNome(event.target.value)}
          />
        </FormControl>

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
            onChange={event => setSenha(event.target.value)}
          />
        </FormControl>

        <FormControl variant="standard" fullWidth sx={{ marginBottom: '32px' }}>
          <InputLabel shrink htmlFor="token">
            Token
          </InputLabel>
          <TextInput
            id="token"
            name="token"
            required
            onChange={event => setToken(event.target.value)}
          />
        </FormControl>

        <Button
          type="submit"
          variant="contained"
          style={{ background: '#FB8500' }}
          fullWidth
        >
          Cadastrar
        </Button>
      </Container>
    </Box>
  );
}
