import {
  Box,
  Button,
  Container,
  FormControl,
  InputLabel,
  Typography,
} from '@mui/material';
import ArrowBackIcon from '@mui/icons-material/ArrowBack';
import { FormEvent, useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { toast } from 'react-toastify';

import { Layout } from '../../../components/Layout';
import { TextInput } from '../../../components/Form';
import { axiosCrud } from '../../../services';

type Exercise = {
  id: number;
  name: string;
  muscleGroup: string;
  equipment: string;
};

type ExerciseAPIResponse = {
  data: Exercise;
  errors: string[];
};

export default function EditExercise() {
  const { id } = useParams();

  const navigate = useNavigate();

  const [name, setName] = useState('');
  const [equipment, setEquipment] = useState('');
  const [muscularGroup, setMuscularGroup] = useState('');
  const [isLoading, setIsLoading] = useState(false);

  useEffect(() => {
    axiosCrud
      .get<ExerciseAPIResponse>(`/exercise/${id}`)
      .then(response => {
        setName(response.data.data.name);
        setEquipment(response.data.data.equipment);
        setMuscularGroup(response.data.data.muscleGroup);
      })
      .catch(error => {
        toast.error('Não foi possível carregar o exercício no momento');

        navigate(-1);
      });
  }, [id]);

  async function handleSubmit(event: FormEvent) {
    event.preventDefault();

    setIsLoading(true);

    const payload = {
      name,
      muscleGroup: muscularGroup,
      equipment,
    };

    axiosCrud
      .put(`/exercise/${id}`, payload)
      .then(() => {
        toast.success('Exercício editado com sucesso!');
      })
      .catch(() => {
        toast.error('Erro ao editar exercício!');
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
          <Typography variant="h4">Editar exercício</Typography>
          <Typography variant="body1" marginTop="16px">
            Utilize os campos abaixo para editar o exercício selecionado.
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
            value={name}
            onChange={event => setName(event.target.value)}
            required
          />
        </FormControl>

        <FormControl variant="standard" fullWidth sx={{ marginBottom: '16px' }}>
          <InputLabel shrink htmlFor="equipment">
            Equipamento
          </InputLabel>
          <TextInput
            id="equipment"
            name="equipment"
            value={equipment}
            onChange={event => setEquipment(event.target.value)}
            required
          />
        </FormControl>

        <FormControl variant="standard" fullWidth sx={{ marginBottom: '16px' }}>
          <InputLabel shrink htmlFor="muscular-group">
            Grupo Muscular
          </InputLabel>
          <TextInput
            id="muscular-group"
            name="muscular-group"
            value={muscularGroup}
            onChange={event => setMuscularGroup(event.target.value)}
            required
          />
        </FormControl>

        <Button
          type="submit"
          variant="contained"
          style={{ background: '#FB8500' }}
          disabled={isLoading}
          fullWidth
        >
          {isLoading ? 'Carregando...' : 'Editar'}
        </Button>
      </Container>
    </Layout>
  );
}
