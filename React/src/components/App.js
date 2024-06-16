import React from 'react';
import '../styles/App.css';
import { Sidebar, Menu, MenuItem } from 'react-pro-sidebar';
import { Link } from "react-router-dom"

function App() {
  return (
    <div className="App">
      <Sidebar className="sidebar" backgroundColor='black'>
                <Menu>
                    <MenuItem>
                        <Link to="/">Home</Link>
                    </MenuItem>
                    <MenuItem>
                        <Link to="/add">Add Story</Link>
                    </MenuItem>
                    <MenuItem>
                        <Link to="/stories">Stories</Link>
                    </MenuItem>
                </Menu>
            </Sidebar>
      <header className="App-header">
        <h1>Welcome to VIA tabloid app</h1>
      </header>
    </div>
  );
}

export default App;
