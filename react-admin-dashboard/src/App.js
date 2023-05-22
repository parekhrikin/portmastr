import { ColorModeContext, useMode} from './theme';
import { CssBaseline, ThemeProvider } from "@mui/material";
import Topbar from "./scenes/global/Topbar";
import { Routes, Route } from "react-router-dom";
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

 
function App() {
  const [theme, colorMode] = useMode();
  return (
    <ColorModeContext.Provider value = {colorMode}>
      <ThemeProvider theme={theme}>
        <CssBaseline />
        <div className="app">
          <Sidebar />
          <main className="content">
            <Topbar />
            <Routes>
              <Route path="/" element={<Dashboard />} />
              <Route path="/rates" element={<Rates />} />
              <Route path="/customers" element={<Customers />} />
              <Route path="/confirmedRates" element={<ConfirmedRates />} />
              <Route path="/add" element={<AddRate />} />
              {/* <Route path="/form" element={<Form />} /> */}
              {/* <Route path="/bar" element={<Bar />} /> */}
              {/* <Route path="/pie" element={<Pie />} /> */}
              {/* <Route path="/line" element={<Line />} /> */}
              {/* <Route path="/faq" element={<FAQ />} /> */}
              {/* <Route path="/geography" element={<Geography />} /> */}
              {/* <Route path="/calendar" element={<Calendar />} /> */}
            </Routes>
          </main>
        </div>
      </ThemeProvider>
    </ColorModeContext.Provider>
  );
}

export default App;
