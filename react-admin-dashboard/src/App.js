import { ColorModeContext, useMode} from './theme';
import { CssBaseline, ThemeProvider } from "@mui/material";
import Topbar from "./scenes/global/Topbar";
import { Route, Routes } from 'react-router-dom';
import { Security, SecureRoute, LoginCallback } from '@okta/okta-react';
import Sidebar from "./scenes/global/Sidebar";
import Dashboard from "./scenes/dashboard";
import Rates from "./scenes/rates";
// import Invoices from "./scenes/invoices";
import ConfirmedRates from "./scenes/confirmedRates";
import Customers from "./scenes/customers"
import AddRate from "./scenes/addRate"
// import Bar from "./scenes/bar";
// import Form from "./scenes/form";
// import Line from "./scenes/line";
// import Pie from "./scenes/pie";
// import FAQ from "./scenes/faq";
// import Geography from "./scenes/geography";
// import Calendar from "./scenes/calendar"

import RequiredAuth from './components/RequiredAuth';
import { useNavigate } from 'react-router-dom';
import { OktaAuth, toRelativeUrl } from '@okta/okta-auth-js';
// import { Security } from '@okta/okta-react';
import config from './okta/config';
// import { LoginCallback, } from '@okta/okta-react';

const oktaAuth = new OktaAuth(config.oidc);

 
function App() {
  // const [theme, colorMode] = useMode();

  const navigate = useNavigate();
  const restoreOriginalUri = (_oktaAuth, originalUri) => {
    navigate(toRelativeUrl(originalUri || '/', window.location.origin));
  };

  return (
    <Security oktaAuth={oktaAuth} restoreOriginalUri={restoreOriginalUri}>
            <Routes>
              <Route path="/" element={<RequiredAuth />}>
                <Route path="" element={<Dashboard />} />
                <Route path="/rates" element={<Rates />} />
                <Route path="/customers" element={<Customers />} />
                <Route path="/confirmedRates" element={<ConfirmedRates />} />
                {/* <Route path="/add" element={<AddRate />} /> */}
                <Route path="/logout" />
              </Route>
              <Route path="login/callback" element={<LoginCallback />} />
            </Routes>
    </Security>
    // <Router>
    //   <Security oktaAuth={oktaAuth} restoreOriginalUri={restoreOriginalUri}>
    //     <Route path="/login/callback" component={LoginCallback} />
    //       <RequiredAuth>
    //         <SecureRoute exact path="/" component={Dashboard} />
    //         <SecureRoute path="/rates" component={Rates} />
    //         <SecureRoute path="/confirmedRates" component={ConfirmedRates} />
    //         <SecureRoute path="/customers" component={Customers} />
    //         <SecureRoute path="/add" component={AddRate} />
    //       </RequiredAuth>
    //   </Security>
    // </Router>
  );
}

export default App;
