
import '../styles/App.css';
import { Sidebar, Menu, MenuItem } from 'react-pro-sidebar';
import { Link } from "react-router-dom"

function App() {
  return (
    <div className="App">
      <Sidebar className="sidebar" backgroundColor='black'>
        <Menu>
          <Link to="/"><MenuItem> Home </MenuItem></Link>
          <Link to="/add"><MenuItem> Add Story </MenuItem></Link>
          <Link to="/stories"><MenuItem> Stories </MenuItem></Link>
        </Menu>
      </Sidebar>
      <header className="App-header">
        <h1>Welcome to VIA tabloid app</h1>
      </header>
    </div>
  );
}

export default App;
