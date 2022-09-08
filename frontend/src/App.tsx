import { AppRoutes } from './routes/index';
import { Provider } from './providers';

function App() {
  return (
    <Provider>
      <AppRoutes />
    </Provider>
  );
}

export default App;
