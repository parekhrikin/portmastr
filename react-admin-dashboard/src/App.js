import { ColorModeContext, useMode} from './theme';
import { CssBaseline, ThemeProvider } from "@mui/material";
import Topbar from "./scenes/global/Topbar";
import { Route, Routes } from 'react-router-dom';
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


 
function App() {
  // const [theme, colorMode] = useMode();


  return (
    
            <Routes>
              <Route path="/" element={<RequiredAuth />}>
              
                      <Route path="/" element={<Dashboard />} />
                      <Route path="/rates" element={<Rates />} />
                      <Route path="/customers" element={<Customers />} />
                      <Route path="/confirmedRates" element={<ConfirmedRates />} />
                      <Route path="/add" element={<AddRate />} />
                    
              </Route>
            </Routes>
            
  );
}

export default App;
