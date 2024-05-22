import React from 'react';
import ReactDOM from 'react-dom/client';
import './styles/index.css';
import App from './components/App';
import AddStory from './components/AddStory';
import Edit from './components/Stories';
import reportWebVitals from './reportWebVitals';
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

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
