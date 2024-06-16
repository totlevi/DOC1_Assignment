import React from 'react';
import { render, screen } from '@testing-library/react';
import App from '../components/App';

test('renders the header', () => {
  render(<App />);
  const headerElement = screen.getByText(/Welcome to VIA tabloid app/i);
  expect(headerElement).toBeInTheDocument();
});
