import { createBrowserRouter } from 'react-router-dom';
import { Page } from '../pages/components/page/Page';
import { HomePage } from '../pages/home/HomePage';
import LoginPage from '../pages/Login';
import { Profile } from '../pages/Profile/Profile';
import { Proposta } from '../pages/Proposta/Proposta';

export const router = createBrowserRouter([
  {
    path: '/',
    element: <LoginPage />,
  },
  {
    path: '/user',
    element: <Page />,
    children: [
      {
        path: '/user/home',
        element: <HomePage />,
      },
      {
        path: '/user/profile',
        element: <Profile />,
      },
      {
        path: '/user/propostas',
        element: <Proposta />,
      },
    ],
  },
]);
