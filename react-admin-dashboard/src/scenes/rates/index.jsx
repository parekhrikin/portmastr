import { Box, Typography, useTheme } from "@mui/material";
import { DataGrid, GridCheckboxSelectionModel, GridToolbar } from "@mui/x-data-grid";
import { tokens } from "../../theme";
import AdminPanelSettingsOutlinedIcon from "@mui/icons-material/AdminPanelSettingsOutlined";
import LockOpenOutlinedIcon from "@mui/icons-material/LockOpenOutlined";
import SecurityOutlinedIcon from "@mui/icons-material/SecurityOutlined";
import React, { useState, useEffect } from "react";
import Header from "../../components/Header";

const Rates = () => {
  const [rateList, setRateList] = useState({
    rates: []
  });
  const [selectionModel, setSelectionModel] = useState([]);
  const theme = useTheme();
  const colors = tokens(theme.palette.mode);

  const columns = [
    { field: "id", headerName: "ID", flex: 1 },
    { field: "pol", headerName: "POL", cellClassName: "pol-column--cell" },
    { field: "pod", headerName: "POD", cellClassName: "pod-column--cell" },
    { field: "route", headerName: "Route" },
    { field: "tt", headerName: "TT" },
    { field: "twenty", headerName: "20FT" },
    { field: "forty", headerName: "40FT" },
    { field: "fortyhq", headerName: "40HC" },
    { field: "addcharges", headerName: "Surcharges" },
    { field: "validity", headerName: "Validity" },
    {
      field: "actions",
      headerName: "Actions",
      sortable: false,
      renderCell: (params) => {
        const handleDelete = () => {
          const updatedRates = rateList.rates.filter(
            (rate) => rate.id !== params.row.id
          );
          setRateList({ rates: updatedRates });
          fetch(`http://localhost:8080/rate/${params.row.id}`, { method: 'DELETE' })
            .then(() => console.log('Delete successful'));
        };

        return (
          <button
            onClick={handleDelete}
            style={{
              background: "none",
              border: "none",
              color: colors.redAccent[500],
              cursor: "pointer",
            }}
          >
            Delete
          </button>
        );
      }
    }
  ];

  useEffect(() => {
    fetch("http://localhost:8080/rates")
      .then((res) => res.json())
      .then((res) => {
        console.log(res);
        setRateList({
          rates: res
        });
        console.log(rateList);
      })
      .catch((error) => {
        console.log("error...." + error);
      });
  }, []);

  return (
    <Box m="20px">
      <Header title="Rates" subtitle="View All The Rates" />
      <Box
        m="40px 0 0 0"
        height="75vh"
        sx={{
          "& .MuiDataGrid-root": {
            border: "none",
          },
          "& .MuiDataGrid-cell": {
            borderBottom: "none",
          },
          "& .pol-column--cell": {
            color: colors.greenAccent[300],
            fontWeight: 'bold',
          },
          "& .pod-column--cell": {
            color: colors.greenAccent[300],
            fontWeight: 'bold',
          },
          "& .MuiDataGrid-columnHeaders": {
            backgroundColor: colors.blueAccent[700],
            borderBottom: "none",
          },
          "& .MuiDataGrid-virtualScroller": {
            backgroundColor: colors.primary[400],
          },
          "& .MuiDataGrid-footerContainer": {
            borderTop: "none",
            backgroundColor: colors.blueAccent[700],
          },
          "& .MuiDataGrid-toolbarContainer .MuiButton-text": {
            color: `${colors.grey[100]} !important`
          }
        }}
      >
        <DataGrid
          rows={rateList.rates}
          columns={columns}
          components={{ Toolbar: GridToolbar }}
          checkboxSelection
          selectionModel={selectionModel}
          onSelectionModelChange={(newSelection) => {
            setSelectionModel(newSelection);
          }}
        />
      </Box>
    </Box>
  )

}

export default Rates;