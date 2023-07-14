import React, { useState, useEffect } from 'react';
import { Box } from "@mui/material";
import Header from "../../components/Header";


const Dashboard = () => {



    return (
    <Box m="20px">
        <Box display="flex" justifyContent="space-between" alignItem="center">
            <Header title="DASHBORD" subtitle="Welcome to your dashboard" />
        </Box>
    </Box>
    );
};

export default Dashboard;