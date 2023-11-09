import { useLocation } from 'react-router-dom';

function capitalize(str: string): string {
  return str.charAt(0).toUpperCase() + str.slice(1);
}

function extractRouteName(pathname: string): string {
  const pathSegments = pathname.substring(1).split('/');

  const homeIndex = pathSegments.indexOf('home');
  if (homeIndex !== -1 && homeIndex < pathSegments.length - 1) {
    const routeName = pathSegments[homeIndex + 1];
    return capitalize(routeName);
  }

  return '';
}

export const useHome = () => {
  const location = useLocation();
  const currentRouteName = extractRouteName(location.pathname);
  return { currentRouteName };
};
