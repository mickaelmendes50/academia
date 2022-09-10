import {
  Box,
  Button,
  Container,
  FormControl,
  InputLabel,
  Typography,
} from '@mui/material';
import ArrowBackIcon from '@mui/icons-material/ArrowBack';
import { FormEvent, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { PatternFormat } from 'react-number-format';
import { toast } from 'react-toastify';

import { axiosCrud } from '../../../services';

import { Layout } from '../../../components/Layout';
import { TextInput } from '../../../components/Form';

export default function CreateStudent() {
  const navigate = useNavigate();

  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [cpf, setCpf] = useState('');
  const [planType, setPlanType] = useState('');
  const [isLoading, setIsLoading] = useState(false);

  async function handleSubmit(event: FormEvent) {
    event.preventDefault();

    setIsLoading(true);

    const payload = {
      name,
      email,
      cpf,
      tipo: planType,
    };

    axiosCrud
      .post('/students', payload)
      .then(() => {
        toast.success('Aluno cadastrado com sucesso!');
      })
      .catch(() => {
        toast.error('Erro ao cadastrar aluno!');
      })
      .finally(() => {
        setIsLoading(false);
      });
  }

  return (
    <Layout>
      <Box
        display="flex"
        alignItems="flex-start"
        justifyContent="space-between"
      >
        <Box>
          <Typography variant="h4">Cadastrar aluno</Typography>
          <Typography variant="body1" marginTop="16px">
            Utilize os campos abaixo para cadastrar um novo aluno.
          </Typography>
        </Box>

        <Button
          size="large"
          variant="contained"
          startIcon={<ArrowBackIcon />}
          onClick={() => {
            navigate('/students/');
          }}
          style={{ background: '#25a18e' }}
        >
          Voltar
        </Button>
      </Box>

      <Container
        onSubmit={handleSubmit}
        component="form"
        maxWidth="xs"
        sx={{
          marginTop: '48px',
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
            onChange={event => setName(event.target.value)}
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
          <InputLabel shrink htmlFor="cpf">
            CPF
          </InputLabel>
          <PatternFormat
            format="###.###.###-##"
            customInput={TextInput}
            id="cpf"
            name="cpf"
            required
            onValueChange={v => setCpf(v.formattedValue)}
          />
        </FormControl>

        <FormControl variant="standard" fullWidth sx={{ marginBottom: '32px' }}>
          <InputLabel shrink htmlFor="plan-type">
            Tipo Plano
          </InputLabel>
          <TextInput
            id="plan-type"
            name="tipo-plano"
            required
            onChange={event => setPlanType(event.target.value)}
          />
        </FormControl>

        <Button
          type="submit"
          variant="contained"
          style={{ background: '#FB8500' }}
          disabled={isLoading}
          fullWidth
        >
          {isLoading ? 'Carregando...' : 'Cadastrar'}
        </Button>
      </Container>
    </Layout>
  );
}
