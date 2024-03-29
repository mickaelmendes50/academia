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
import { toast } from 'react-toastify';

import { Layout } from '../../../components/Layout';
import { TextInput } from '../../../components/Form';
import { axiosCrud } from '../../../services';

export default function CreateExercise() {
  const navigate = useNavigate();

  const [name, setName] = useState('');
  const [equipment, setEquipment] = useState('');
  const [muscularGroup, setMuscularGroup] = useState('');
  const [isLoading, setIsLoading] = useState(false);

  async function handleSubmit(event: FormEvent) {
    event.preventDefault();

    setIsLoading(true);

    const payload = {
      name,
      muscleGroup: muscularGroup,
      equipment,
    };

    axiosCrud
      .post('/exercise', payload)
      .then(() => {
        toast.success('Exercício cadastrado com sucesso!');
      })
      .catch(() => {
        toast.error('Erro ao cadastrar exercício!');
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
          <Typography variant="h4">Cadastrar exercício</Typography>
          <Typography variant="body1" marginTop="16px">
            Utilize os campos abaixo para cadastrar um novo exercício.
          </Typography>
        </Box>

        <Button
          size="large"
          variant="contained"
          startIcon={<ArrowBackIcon />}
          onClick={() => {
            navigate('/exercises/');
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
          <InputLabel shrink htmlFor="equipment">
            Equipamento
          </InputLabel>
          <TextInput
            id="equipment"
            name="equipment"
            required
            onChange={event => setEquipment(event.target.value)}
          />
        </FormControl>

        <FormControl variant="standard" fullWidth sx={{ marginBottom: '16px' }}>
          <InputLabel shrink htmlFor="muscular-group">
            Grupo Muscular
          </InputLabel>
          <TextInput
            id="muscular-group"
            name="muscular-group"
            required
            onChange={event => setMuscularGroup(event.target.value)}
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
