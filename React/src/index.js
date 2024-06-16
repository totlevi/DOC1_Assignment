import React from 'react';
import ReactDOM from 'react-dom/client';
import './styles/index.css';
import App from './components/App';
import AddStory from './components/AddStory';
import Edit from './components/Stories';
import { RouterProvider, createHashRouter } from "react-router-dom"

const router = createHashRouter([
  {
    path:"/",
    element:<App/>
  },
  {
    path:"/add",
    element:<AddStory/>
  },
  {
    path:"/stories",
    element:<Edit/>
  },
])

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(<RouterProvider router={router} />);

