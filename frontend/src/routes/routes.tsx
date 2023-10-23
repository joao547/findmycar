import { createBrowserRouter } from 'react-router-dom';
import { Home } from '../pages/home/Home';
import LoginPage from '../pages/Login';

export const router = createBrowserRouter([
  {
    path: '/',
    element: <LoginPage />,
  },
  {
    path: '/home',
    element: <Home />,
  },
]);
