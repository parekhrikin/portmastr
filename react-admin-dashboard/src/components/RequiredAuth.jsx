import React, { useState } from 'react';
import { Link, Outlet, Route, Routes, useNavigate } from 'react-router-dom';

import { ColorModeContext, useMode } from '../theme';
import { CssBaseline, ThemeProvider } from '@mui/material';
import Topbar from '../scenes/global/Topbar';
import Sidebar from '../scenes/global/Sidebar';
import RegistrationForm from './RegistrationForm';
import {
  Box,
  Button,
  Container,
  TextField,
  Typography,
} from '@mui/material';
import { styled } from '@mui/system';

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


// import React, { useState } from 'react';
// import { Link, Outlet, Route, Routes, useNavigate } from 'react-router-dom';

// import { ColorModeContext, useMode } from '../theme';
// import { CssBaseline, ThemeProvider } from '@mui/material';
// import Topbar from '../scenes/global/Topbar';
// import Sidebar from '../scenes/global/Sidebar';
// import RegistrationForm from './RegistrationForm';
// import Dashboard from "../scenes/dashboard";
// import Rates from "../scenes/rates";
// import ConfirmedRates from "../scenes/confirmedRates";
// import Customers from "../scenes/customers"
// import AddRate from "../scenes/addRate"
// import {
//   Box,
//   Button,
//   Container,
//   TextField,
//   Typography,
// } from '@mui/material';
// import { styled } from '@mui/system';


// const RootContainer = styled('div')({
//   display: 'flex',
//   justifyContent: 'center',
//   alignItems: 'center',
//   minHeight: '100vh',
// });

// const LoginContainer = styled(Container)({
//   display: 'flex',
//   flexDirection: 'column',
//   alignItems: 'center',
//   justifyContent: 'center',
//   maxWidth: 300,
//   padding: '2rem',
//   boxShadow: '0px 2px 4px rgba(0, 0, 0, 0.1)',
//   borderRadius: '8px',
// });

// const Title = styled(Typography)({
//   marginBottom: '2rem',
//   textAlign: 'center',
// });

// const Form = styled('form')({
//   display: 'flex',
//   flexDirection: 'column',
//   gap: '1rem',
//   width: '40%',
// });

// const SubmitButton = styled(Button)({
//   marginTop: '2rem',
// });

// function App() {
//   const [theme, colorMode] = useMode();
//   const [isLoggedIn, setIsLoggedIn] = useState(false);

//   const navigate = useNavigate();

//   const [email, setEmail] = useState('');
//   const [password, setPassword] = useState('');

//   const handleEmailChange = (e) => {
//     setEmail(e.target.value);
//   };

//   const handlePasswordChange = (e) => {
//     setPassword(e.target.value);
//   };

//   const handleSubmit = (e) => {
//     e.preventDefault();
//     // Perform login logic here
//     // Replace the following line with your actual login implementation
//     // For now, we'll simulate a successful login
//     if (email === 'rikinparekh3478@gmail.com' && password === '5768') {
//       // Redirect to dashboard or desired route on successful login
//       setIsLoggedIn(true);
//     } else {
//       // Handle login error, such as displaying an error message
//       alert('Invalid email or password');
//     }
//   };

//   return (
//     <ColorModeContext.Provider value={colorMode}>
//       <ThemeProvider theme={theme}>
//         <CssBaseline />
//         <RootContainer>
//         <Routes>
//           <Route path="/" element={ 
//               !isLoggedIn ? (
//                   <LoginContainer>
//                     <Title variant="h5" component="h1">
//                       Login to Portmaster
//                     </Title>
//                     <Form onSubmit={handleSubmit}>
//                       <TextField
//                         label="Email"
//                         variant="outlined"
//                         type="email"
//                         value={email}
//                         onChange={handleEmailChange}
//                         required
//                       />
//                       <TextField
//                         label="Password"
//                         variant="outlined"
//                         type="password"
//                         value={password}
//                         onChange={handlePasswordChange}
//                         required
//                       />
//                       Don't have an account?{' '}
//                       <Link to="/register">Register</Link>
//                       <SubmitButton
//                         type="submit"
//                         variant="contained"
//                         color="primary"
//                       >
//                         Login
//                       </SubmitButton>
//                     </Form>
//                   </LoginContainer>
//                 ) : (
//                   <div className="app">
//                     <Sidebar />
//                     <main className="content">
//                       <Topbar />
//                       <Outlet />
//                     </main>
//                   </div>
//                 )
//               }
//             />
//             <Route path="/register" element={<RegistrationForm />} />
//         </Routes>
//         </RootContainer>
//       </ThemeProvider>
//     </ColorModeContext.Provider>
//   );
// }

// export default App;


