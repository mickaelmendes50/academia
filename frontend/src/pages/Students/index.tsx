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

type Student = {
  id: number;
  name: string;
  cpf: string;
  email: string;
  tipo: string;
};

type StudentsAPIResponse = {
  data: Student[];
  errors: string[];
};

export default function Students() {
  const [isLoading, setIsLoading] = useState(true);
  const [students, setStudents] = useState<Student[]>([]);
  const [studentIdToDelete, setStudentIdToDelete] = useState<number | null>(
    null,
  );
  const [isDialogOpen, setIsDialogOpen] = useState(false);

  const navigate = useNavigate();

  function handleDeleteUser(id: number) {
    setStudentIdToDelete(id);
    setIsDialogOpen(true);
  }

  function handleConfirmDeleteUser() {
    axiosCrud
      .delete(`/students/${studentIdToDelete}`)
      .then(() => {
        setStudents(
          students.filter(student => student.id !== studentIdToDelete),
        );

        toast.success('Aluno deletado com sucesso!');
        setIsDialogOpen(false);
      })
      .catch(() => {
        toast.error('Não foi possível deletar o aluno no momento');
      });
  }

  useEffect(() => {
    axiosCrud
      .get<StudentsAPIResponse>('/students')
      .then(response => {
        setStudents(response.data.data);
      })
      .catch(error => {
        toast.error('Não foi possível carregar os alunos no momento');
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
          <Typography variant="h4">Seus alunos</Typography>
          <Typography variant="body1" marginTop="16px">
            Esses são os alunos cadastrados na sua academia.
          </Typography>
        </Box>

        <Button
          size="large"
          variant="contained"
          startIcon={<AddIcon />}
          onClick={() => {
            navigate('/students/create');
          }}
          style={{ background: '#25a18e' }}
        >
          Adicionar aluno
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
                  <TableCell sx={{ fontWeight: '700' }}>CPF</TableCell>
                  <TableCell sx={{ fontWeight: '700' }}>E-mail</TableCell>
                  <TableCell sx={{ fontWeight: '700' }}>Tipo</TableCell>
                  <TableCell sx={{ fontWeight: '700' }}>Ações</TableCell>
                </TableRow>
              </TableHead>

              <TableBody>
                {students.map(student => (
                  <TableRow key={student.id}>
                    <TableCell>{student.name}</TableCell>
                    <TableCell>{student.cpf}</TableCell>
                    <TableCell>{student.email}</TableCell>
                    <TableCell>{student.tipo}</TableCell>
                    <TableCell
                      sx={{ display: 'flex', gap: '4px', alignItems: 'center' }}
                    >
                      <IconButton
                        aria-label="edit"
                        onClick={() => {
                          navigate(`/students/edit/${student.id}`);
                        }}
                      >
                        <ModeEditIcon sx={{ width: '24px', height: '24px' }} />
                      </IconButton>
                      <IconButton
                        aria-label="delete"
                        onClick={() => {
                          handleDeleteUser(student.id);
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
        <DialogTitle id="alert-dialog-title">Deletar estudante</DialogTitle>
        <DialogContent>
          <DialogContentText id="alert-dialog-description">
            Tem certeza que deseja deletar esse estudante?
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button onClick={() => setIsDialogOpen(false)}>Não</Button>
          <Button onClick={handleConfirmDeleteUser}>Sim</Button>
        </DialogActions>
      </Dialog>
    </Layout>
  );
}
