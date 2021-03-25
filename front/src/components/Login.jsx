import React, { useState } from 'react';
import Button from '@material-ui/core/Button';
import CssBaseline from '@material-ui/core/CssBaseline';
import FormControl from '@material-ui/core/FormControl';
import Input from '@material-ui/core/Input';
import InputLabel from '@material-ui/core/InputLabel';
import Paper from '@material-ui/core/Paper';
import Typography from '@material-ui/core/Typography';
import './Login.css';
import AssignmentTwoToneIcon from '@material-ui/icons/AssignmentTwoTone';
import axios from "axios";



export const Login = (props) => {

    const [usernameState, setUsernameState] = useState("");
    const [passwordState, setPasswordState] = useState("");

    const handleUsernameChange = (e) => {
        setUsernameState(e.target.value);
    }

    const handlePasswordChange = (e) => {
        setPasswordState(e.target.value);
    }

    let user = {
        email: usernameState,
        password: passwordState,
        firstname: "Juan",
        lastname: "Galeano",
        username: "Juan Frasica"
    }

    const handleLogin = (e) => {
        e.preventDefault();
        axios.post("http://localhost:8080/api/users/login", user)
            .then(Response => {
                let token = Response.data.accessToken;
                localStorage.setItem("token", token);
                props.successfully();
            }).catch(error => {
                props.failed();
            });
    }

    return (
        <div>
            <CssBaseline />
            <main className="layout">
                <Paper className="paper">

                    <Typography variant="h2">Task Planner</Typography>
                    <AssignmentTwoToneIcon style={{ fontSize: 150 }} />
                    <form className="form" onSubmit={handleLogin}>
                        <FormControl margin="normal" required fullWidth>
                            <InputLabel htmlFor="email">Username</InputLabel>
                            <Input
                                onChange={handleUsernameChange}
                                id="username"
                                name="username"
                                autoComplete="username"
                                autoFocus
                            />
                        </FormControl>
                        <FormControl margin="normal" required fullWidth>
                            <InputLabel htmlFor="password">Password</InputLabel>
                            <Input
                                name="password"
                                type="password"
                                id="password"
                                autoComplete="current-password"
                                onChange={handlePasswordChange}
                            />
                        </FormControl>
                        <br />
                        <br />
                        <br />
                        <Button
                            type="submit"
                            fullWidth
                            variant="contained"
                            color="primary"
                            className="submit"
                        >
                            LOG IN
                        </Button>
                        <br />
                        <br />
                        <Button fullWidth variant="contained" disabled>Create Account
                        </Button>
                    </form>
                </Paper>
            </main>
        </div>
    );
}
