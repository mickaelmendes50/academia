import AddIcon from '@mui/icons-material/Add';
import DeleteIcon from '@mui/icons-material/Delete';
import ModeEditIcon from '@mui/icons-material/ModeEdit';
import {
  Box,
  Button,
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
import { useNavigate } from 'react-router-dom';

import { Layout } from '../../components/Layout';

export default function Students() {
  const navigate = useNavigate();

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

      <TableContainer component={Paper} sx={{ marginTop: '48px' }}>
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
            <TableRow>
              <TableCell>Vitor</TableCell>
              <TableCell>07544766122</TableCell>
              <TableCell>vitor@mail.com</TableCell>
              <TableCell>Mensal</TableCell>
              <TableCell
                sx={{ display: 'flex', gap: '4px', alignItems: 'center' }}
              >
                <IconButton aria-label="delete">
                  <ModeEditIcon sx={{ width: '24px', height: '24px' }} />
                </IconButton>
                <IconButton aria-label="delete">
                  <DeleteIcon sx={{ width: '24px', height: '24px' }} />
                </IconButton>
              </TableCell>
            </TableRow>
            <TableRow>
              <TableCell>Vitor</TableCell>
              <TableCell>07544766122</TableCell>
              <TableCell>vitor@mail.com</TableCell>
              <TableCell>Mensal</TableCell>
              <TableCell
                sx={{ display: 'flex', gap: '4px', alignItems: 'center' }}
              >
                <IconButton aria-label="delete">
                  <ModeEditIcon sx={{ width: '24px', height: '24px' }} />
                </IconButton>
                <IconButton aria-label="delete">
                  <DeleteIcon sx={{ width: '24px', height: '24px' }} />
                </IconButton>
              </TableCell>
            </TableRow>
          </TableBody>
        </Table>
      </TableContainer>
    </Layout>
  );
}
