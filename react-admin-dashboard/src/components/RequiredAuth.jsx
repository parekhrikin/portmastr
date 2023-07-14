import React, { useEffect } from 'react';
import { Outlet } from 'react-router-dom';
import Dashboard from '../scenes/dashboard/index'

import { ColorModeContext, useMode} from '../theme';
import { CssBaseline, ThemeProvider } from "@mui/material";
import Topbar from "../scenes/global/Topbar";
import Sidebar from "../scenes/global/Sidebar";

// const { Header, Footer, Sider, Content } = Layout;

function RequiredAuth() {
  const [theme, colorMode] = useMode();

  return (
    <ColorModeContext.Provider value = {colorMode}>
      <ThemeProvider theme={theme}>
        <CssBaseline />
        <div className="app">
          <Sidebar />
          <main className="content">
            <Topbar />
              {/* <Dashboard /> */}
              <Outlet />
          </main>
        </div>
      </ThemeProvider>
    </ColorModeContext.Provider>
  );

}

export default RequiredAuth;