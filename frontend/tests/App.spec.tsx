import { describe } from 'vitest';
import App from '../src/App';

import { render, screen } from '@testing-library/react';

describe('App', () => {
  it('should render App page', () => {
    render(<App />);

    const hello = screen.getByText('Ola, mundo');

    expect(hello).toBeInTheDocument();
  });
});
