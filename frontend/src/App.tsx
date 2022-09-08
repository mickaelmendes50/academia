import { AppRoutes } from './routes/index';
import { Provider } from './providers';

import { GlobalStyles } from './styles';

function App() {
  return (
    <Provider>
      <GlobalStyles />
      <AppRoutes />
    </Provider>
  );
}

export default App;
