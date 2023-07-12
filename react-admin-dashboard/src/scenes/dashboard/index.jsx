import React, { useState, useEffect } from 'react';
import { useOktaAuth } from '@okta/okta-react';
import { toRelativeUrl } from '@okta/okta-auth-js';
import { Spin } from 'antd';
import { Layout } from 'antd';
import { Box } from "@mui/material";
import Header from "../../components/Header";

const baseURL = process.env.REACT_APP_API_URL + '/tasks';

const Dashboard = () => {

    const [tasks, setTasks] = useState(null);
    const { authState, oktaAuth } = useOktaAuth();
    const accessToken = oktaAuth.getAccessToken();

    useEffect(() => {
        if (authState && authState.isAuthenticated) {
            
            fetch(`${baseURL}`, {
                headers: {
                    Authorization: `Bearer ${accessToken}`,
                },
            })
                .then((response) => {
                    return response.json();
                })
                .then((data) => {
                    setTasks(data);
                })
                .catch((err) => {
                    console.error(err);
                });
        }
    }, [authState, oktaAuth]);

    if (!tasks) return null;

    return (
    <Box m="20px">
        <Box display="flex" justifyContent="space-between" alignItem="center">
            <Header title="DASHBORD" subtitle="Welcome to your dashboard" />
        </Box>
    </Box>
    );
};

export default Dashboard;