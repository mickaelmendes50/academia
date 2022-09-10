import { InputBase, alpha, styled } from '@mui/material';

export const TextInput = styled(InputBase)(({ theme }) => ({
  'label + &': {
    marginTop: theme.spacing(3.5),
  },
  '& .MuiInputBase-input': {
    borderRadius: 4,
    position: 'relative',
    backgroundColor: theme.palette.mode === 'light' ? '#fcfcfb' : '#2b2b2b',
    border: '1px solid #d1d5d8',
    fontSize: 16,
    width: '100%',
    padding: '10px 12px',
    transition: theme.transitions.create([
      'border-color',
      'background-color',
      'box-shadow',
    ]),
    '&:focus': {
      boxShadow: `${alpha('#fb8500', 0.25)} 0 0 0 0.2rem`,
      borderColor: '#fb8500',
    },
  },
}));
