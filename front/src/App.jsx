import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import './App.css';
import { Login } from './components/Login';
import { Tasks } from './components/Tasks';
import { UserProfile } from './components/UserProfile';
import Swal from 'sweetalert2';
import axios from "axios";

const App = () => {

    let userData = {
        username: "Sebastian",
        password: "12345",
        email: "juan.frasica@mail.com"
    };


    function setLocalStorage() {
        localStorage.setItem('username', userData.username);
        localStorage.setItem('password', userData.password);
        localStorage.setItem('email', userData.email);
    }


    const [itemsState, setItemsState] = useState([]);

    useEffect(() => {
        axios.get("http://localhost:8080/api/tasks")
            .then(response => {
                var APIResponse = response.data;
                let finalTasks = [...itemsState]
                if (APIResponse.length !== itemsState.length) {
                    finalTasks = APIResponse
                }
                setItemsState(finalTasks)
            }).catch(error => {
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: error.message,
            })
        });
    }, []);

    /*
    const items = [{
        "description": "Do IETI Lab 3",
        "responsible": {
            "name": userData.username,
            "email": userData.email
        },
        "status": "In Progress",
        "dueDate": 156464645645
    }, {
        "description": "Do IETI Lab 4",
        "responsible": {
            "name": userData.username,
            "email": userData.email
        },
        "status": "Ready",
        "dueDate": 156475645646
    }, {
        "description": "Do Project´s stuff",
        "responsible": {
            "name": userData.username,
            "email": userData.email
        },
        "status": "Completed",
        "dueDate": 158464685646
    }
    ];
*/
    setLocalStorage();

    let initialLoggedInState = localStorage.getItem("isLoggedIn");
    if (initialLoggedInState === "false") {
        initialLoggedInState = false;
    } else if (initialLoggedInState === "true") {
        initialLoggedInState = true;
    }



    const [isLoggedInState, setIsLoggedInState] = useState(initialLoggedInState);

    const handleSuccessfullyLogin = (e) => {
        setIsLoggedInState(true);
        localStorage.setItem("isLoggedIn", true);
        window.location.href = "/home";
    }

    const handleFailedLogin = (e) => {
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Incorrect user or password'
        })
        setIsLoggedInState(false);
        localStorage.setItem("isLoggedIn", false);
    }

    const handleLogout = () => {
        setIsLoggedInState(false);
        localStorage.setItem("isLoggedIn", false);
        window.location.href = "/";
    }

    const handleAddNewTask = (newItem) => {
        const newItems = [...itemsState, newItem];
        console.log(newItems)
        setItemsState(newItems);
    }

    const handleUpdateProfile = (newUserName, newPassword) => {
        const newUserData = {
            username: newUserName,
            password: newPassword,
            email: userData.email
        };
        userData = newUserData;
        console.log(userData);
        setLocalStorage();
    };



    const LoginView = () => (<Login successfully={handleSuccessfullyLogin} failed={handleFailedLogin} />);
    const MainView = () => (<Tasks items={itemsState} logout={handleLogout} addTask={handleAddNewTask} userData={userData} />);
    const UserView = () => (<UserProfile userData={userData} updateUserData={handleUpdateProfile}
    />);

    return (
        <Router>
            <div className="App">
                <Route exact path="/" component={isLoggedInState ? MainView : LoginView} />
                <Route path="/home" component={isLoggedInState ? MainView : LoginView} />
                <Route path="/registration" component={isLoggedInState ? UserView : LoginView} />
            </div>
        </Router>
    );
}

export default App;
