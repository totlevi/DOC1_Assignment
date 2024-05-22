import '../styles/Stories.css';
import { Sidebar, MenuItem, Menu } from 'react-pro-sidebar';
import { Link } from "react-router-dom";
import { useEffect, useState } from 'react';
import { FaRegTrashAlt } from "react-icons/fa";
import Popup from 'reactjs-popup';
import 'reactjs-popup/dist/index.css';

function Edit() {
    const [stories, setStories] = useState([]);
    const [currentStory, setCurrentStory] = useState({ id: null, header: '', content: '' });

    useEffect(() => {
        fetch("http://localhost:8080/stories/")
            .then(data => data.json())
            .then(data => {
                setStories(data);
                console.log(data);
            }).catch(err => console.log(err))
    }, []);

    const deleteStory = (id) => {
        fetch(`http://localhost:8080/stories/${id}`, {
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
        fetch(`http://localhost:8080/stories/${currentStory.id}?header=${encodeURIComponent(currentStory.header)}&content=${encodeURIComponent(currentStory.content)}`, {
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
                    <Link to="/"><MenuItem to="/"> Home </MenuItem></Link>
                    <Link to="/add"><MenuItem > Add Story </MenuItem></Link>
                    <Link to="/stories"><MenuItem > Stories </MenuItem></Link>
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
