import '../styles/AddStory.css';
import { Sidebar, MenuItem, Menu } from 'react-pro-sidebar';
import { Link } from "react-router-dom";
import { useState } from 'react';


function Add() {
    const [header, setHeader] = useState('');
    const [content, setContent] = useState('');

    const addStory = async (e) => {
        e.preventDefault();

        const story = {
            header: header,
            content: content,
        };

        try {
            const response = await fetch("http://localhost:8080/stories/", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(story)
            });

            if (response.ok) {
                console.log("Story added successfully");
                setHeader('');
                setContent('');
            } else {
                console.error("Failed to add story");
            }
        } catch (error) {
            console.error("Error:", error);
        }
    };

    return (
        <div className="app">
            <Sidebar className="sidebar" backgroundColor='black'>
                <Menu>
                    <Link to="/"><MenuItem> Home </MenuItem></Link>
                    <Link to="/add"><MenuItem> Add Story </MenuItem></Link>
                    <Link to="/stories"><MenuItem> Stories </MenuItem></Link>
                </Menu>
            </Sidebar>
            <div className="content">
                <form className='form' onSubmit={addStory}>
                    <div className="form-group">
                        <label htmlFor="header">Header:</label>
                        <input
                            type="text"
                            id="header"
                            name="header"
                            value={header}
                            onChange={(e) => setHeader(e.target.value)}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="content">Content:</label>
                        <textarea
                            id="content"
                            name="content"
                            rows="10"
                            value={content}
                            onChange={(e) => setContent(e.target.value)}
                        ></textarea>
                    </div>
                    <div className="form-group">
                        <button type="submit">Submit</button>
                    </div>
                </form>
            </div>
        </div>
    );
}

export default Add;
