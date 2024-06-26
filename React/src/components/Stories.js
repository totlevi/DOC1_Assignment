import '../styles/Stories.css';
import { Sidebar, MenuItem, Menu } from 'react-pro-sidebar';
import { Link } from "react-router-dom";
import { useEffect, useState, React } from 'react';
import { FaRegTrashAlt } from "react-icons/fa";
import Popup from 'reactjs-popup';
import 'reactjs-popup/dist/index.css';
import backendUrl from '../config.js';

function Edit() {
    const [stories, setStories] = useState([]);
    const [currentStory, setCurrentStory] = useState({ id: null, header: '', content: '' });

    useEffect(() => {
        console.log(backendUrl)
        console.log(`${backendUrl}/stories/`)
        fetch(`${backendUrl}/stories/`)
            .then(data => data.json())
            .then(data => {
                setStories(data);
                console.log(data);
            }).catch(err => console.log(err))
    }, []);

    const deleteStory = (id) => {
        fetch(`${backendUrl}/stories/${id}`, {
            method: 'DELETE'
        })
            .then(response => {
                if (response.ok) {
                    setStories(stories.filter(story => story.id !== id));
                } else {
                    console.error("Failed to delete story");
                }
            })
            .catch(error => console.error("Error deleting story:", error));
    };

    const editStory = () => {
        console.log("Editing story:", currentStory);
        fetch(`${backendUrl}/stories/${currentStory.id}?header=${encodeURIComponent(currentStory.header)}&content=${encodeURIComponent(currentStory.content)}`, {
            method: 'PUT',
        }).then(response => {
            if (response.ok) {
                console.log("Story edited successfully");
                window.location.reload();
            } else {
                console.error("Failed to edit story");
            }
        }).catch(e => console.error("Error:", e));
    };

    return (
        <div className='app'>
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
            <div className='content'>
                <h1>Stories</h1>
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Header</th>
                            <th>Content</th>
                            <th>Date</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {stories.map(story => (
                            <tr key={story.id}>
                                <td>
                                <Popup 
                                        trigger={<button>{story.id  }</button>} 
                                        modal
                                        closeOnDocumentClick
                                        onOpen={() => setCurrentStory(story)}
                                    >
                                        <div className='storyContainer'>
                                            <div className='storyHeader'>
                                                <input 
                                                    value={currentStory.header}  
                                                    onChange={(e) => setCurrentStory({ ...currentStory, header: e.target.value })}
                                                />
                                            </div>
                                            <div>
                                                <textarea 
                                                    value={currentStory.content} 
                                                    rows="10" 
                                                    onChange={(e) => setCurrentStory({ ...currentStory, content: e.target.value })}
                                                />
                                            </div>
                                            <p>{story.date}</p>
                                            <button onClick={editStory}>Edit Story</button>
                                        </div>
                                    </Popup>
                                </td>
                                <td>
                                    {story.header}
                                </td>
                                <td>{story.content}</td>
                                <td>{story.date}</td>
                                <td>
                                    <button onClick={() => deleteStory(story.id)}>
                                        <FaRegTrashAlt />
                                    </button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
}

export default Edit;
