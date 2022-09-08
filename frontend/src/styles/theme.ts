import { createTheme } from '@mui/material';

export const theme = createTheme({
  components: {
    MuiButton: {
      styleOverrides: {
        root: {
          fontWeight: '600',
        },
      },
    },
    MuiCheckbox: {
      styleOverrides: {
        root: {
          color: '#d1d5d8',
        },
      },
    },
    MuiInputLabel: {
      styleOverrides: {
        root: {
          fontSize: '20px',
          '&.Mui-focused': {
            color: '#fb8500',
          },
        },
      },
    },
  },
  typography: {
    h1: {
      color: '#333333',
      fontWeight: '700',
    },
    h2: {
      color: '#333333',
      fontWeight: '700',
    },
    h3: {
      color: '#333333',
      fontWeight: '700',
    },
    h4: {
      color: '#333333',
      fontWeight: '700',
    },
    h5: {
      color: '#333333',
      fontWeight: '700',
    },
    h6: {
      color: '#333333',
      fontWeight: '700',
    },
    body1: {
      color: '#2D3748',
    },
    button: {
      textTransform: 'none',
    },
    fontFamily: ['Rubik', 'sans-serif'].join(','),
  },
});
