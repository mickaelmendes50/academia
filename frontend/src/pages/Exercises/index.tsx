import AddIcon from '@mui/icons-material/Add';
import DeleteIcon from '@mui/icons-material/Delete';
import ModeEditIcon from '@mui/icons-material/ModeEdit';
import {
  Box,
  Button,
  CircularProgress,
  Dialog,
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle,
  IconButton,
  Paper,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Typography,
} from '@mui/material';
import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';

import { toast } from 'react-toastify';
import { axiosCrud } from '../../services';

import { Layout } from '../../components/Layout';

type Exercise = {
  id: number;
  name: string;
  muscleGroup: string;
  equipment: string;
};

type ExercisesAPIResponse = {
  data: Exercise[];
  errors: string[];
};

export default function Exercises() {
  const [isLoading, setIsLoading] = useState(true);
  const [exercises, setExercises] = useState<Exercise[]>([]);
  const [exerciseIdToDelete, setExerciseIdToDelete] = useState<number | null>(
    null,
  );
  const [isDialogOpen, setIsDialogOpen] = useState(false);

  const navigate = useNavigate();

  function handleDeleteExercise(id: number) {
    setExerciseIdToDelete(id);
    setIsDialogOpen(true);
  }

  function handleConfirmDeleteExercise() {
    axiosCrud
      .delete(`/exercise/${exerciseIdToDelete}`)
      .then(() => {
        setExercises(
          exercises.filter(exercise => exercise.id !== exerciseIdToDelete),
        );

        toast.success('Exercício deletado com sucesso!');
        setIsDialogOpen(false);
      })
      .catch(() => {
        toast.error('Não foi possível deletar o exercício no momento');
      });
  }

  useEffect(() => {
    axiosCrud
      .get<ExercisesAPIResponse>('/exercise')
      .then(response => {
        setExercises(response.data.data);
      })
      .catch(error => {
        toast.error(error?.response?.data?.errors[0] ?? 'Erro desconhecido');
      })
      .finally(() => {
        setIsLoading(false);
      });
  }, []);

  return (
    <Layout>
      <Box
        display="flex"
        alignItems="flex-start"
        justifyContent="space-between"
      >
        <Box>
          <Typography variant="h4">Seus exercícios</Typography>
          <Typography variant="body1" marginTop="16px">
            Esses são os exercícios cadastrados na sua academia.
          </Typography>
        </Box>

        <Button
          size="large"
          variant="contained"
          startIcon={<AddIcon />}
          onClick={() => {
            navigate('/exercises/create');
          }}
          style={{ background: '#25a18e' }}
        >
          Adicionar exercício
        </Button>
      </Box>

      <Box marginTop="48px">
        {isLoading ? (
          <Box
            width="100%"
            display="flex"
            alignItems="center"
            justifyContent="center"
          >
            <CircularProgress />
          </Box>
        ) : (
          <TableContainer component={Paper}>
            <Table>
              <TableHead>
                <TableRow>
                  <TableCell sx={{ fontWeight: '700' }}>Nome</TableCell>
                  <TableCell sx={{ fontWeight: '700' }}>Equipamento</TableCell>
                  <TableCell sx={{ fontWeight: '700' }}>
                    Grupo Muscular
                  </TableCell>
                  <TableCell sx={{ fontWeight: '700' }}>Ações</TableCell>
                </TableRow>
              </TableHead>

              <TableBody>
                {exercises.map(exercise => (
                  <TableRow key={exercise.id}>
                    <TableCell>{exercise.name}</TableCell>
                    <TableCell>{exercise.equipment}</TableCell>
                    <TableCell>{exercise.muscleGroup}</TableCell>
                    <TableCell
                      sx={{ display: 'flex', gap: '4px', alignItems: 'center' }}
                    >
                      <IconButton
                        aria-label="edit"
                        onClick={() => {
                          navigate(`/exercises/edit/${exercise.id}`);
                        }}
                      >
                        <ModeEditIcon sx={{ width: '24px', height: '24px' }} />
                      </IconButton>
                      <IconButton
                        aria-label="delete"
                        onClick={() => {
                          handleDeleteExercise(exercise.id);
                        }}
                      >
                        <DeleteIcon sx={{ width: '24px', height: '24px' }} />
                      </IconButton>
                    </TableCell>
                  </TableRow>
                ))}
              </TableBody>
            </Table>
          </TableContainer>
        )}
      </Box>

      <Dialog
        open={isDialogOpen}
        onClose={() => setIsDialogOpen(false)}
        aria-labelledby="alert-dialog-title"
        aria-describedby="alert-dialog-description"
      >
        <DialogTitle id="alert-dialog-title">Deletar exercício</DialogTitle>
        <DialogContent>
          <DialogContentText id="alert-dialog-description">
            Tem certeza que deseja deletar esse exercício?
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button onClick={() => setIsDialogOpen(false)}>Não</Button>
          <Button onClick={handleConfirmDeleteExercise}>Sim</Button>
        </DialogActions>
      </Dialog>
    </Layout>
  );
}
