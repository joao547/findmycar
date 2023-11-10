import { createBrowserRouter } from 'react-router-dom';
import { Home } from '../pages/home/Home';
import LoginPage from '../pages/Login';
import { Profile } from '../pages/Profile/Profile';

export const router = createBrowserRouter([
  {
    path: '/',
    element: <LoginPage />,
  },
  {
    path: '/home',
    element: <Home />,
    children: [
      {
        path: 'dashboard',
        element: (
          <h1>
            Esse texto pertence a tela de <strong>dashboard/home</strong>
          </h1>
        ),
      },
      {
        path: 'profile',
        element: <Profile />
      },
    ],
  },
]);
