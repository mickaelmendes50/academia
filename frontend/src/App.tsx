import { ToastContainer } from 'react-toastify';

import { AppRoutes } from './routes/index';
import { Provider } from './providers';

import { GlobalStyles } from './styles';

function App() {
  return (
    <Provider>
      <GlobalStyles />
      <AppRoutes />
      <ToastContainer />
    </Provider>
  );
}

export default App;
