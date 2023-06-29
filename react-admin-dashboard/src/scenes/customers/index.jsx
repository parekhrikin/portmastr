import { Box } from "@mui/material";
import { DataGrid, GridToolbar } from "@mui/x-data-grid";
import { tokens } from "../../theme";

import React, { useState, useEffect } from "react";
import Header from "../../components/Header";
import { useTheme } from "@mui/material";

const Customers = () => {
  const [custList, setCustList] = useState({
    customers: []
  });
  const [selectionModel, setSelectionModel] = useState([]);
  const theme = useTheme();
  const colors = tokens(theme.palette.mode);

  const columns = [
    { field: "id", headerName: "ID", flex: 0.5 },
    { field: "name", headerName: "Name", cellClassName: "name-column--cell", flex: 1 },
    { field: "poc", headerName: "Point of Contact", flex: 1 },
    { field: "email", headerName: "Email", flex: 1 },
    { field: "phone", headerName: "Phone Number", flex: 1 },
    { field: "address", headerName: "Address", flex: 1 },
    { field: "city", headerName: "City", flex: 1 },
    { field: "zip", headerName: "Zip Code", flex: 1 },
    { field: "onboardDate", headerName: "Onboarded" },
    {
      field: "actions",
      headerName: "Actions",
      sortable: false,
      renderCell: (params) => {
        const handleDelete = () => {
          const updatedCusts = custList.customers.filter(
            (cust) => cust.id !== params.row.id
          );
          setCustList({ rates: updatedCusts });
          fetch(`http://localhost:80/customer/${params.row.id}`, { method: 'DELETE' })
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
    fetch("http://localhost:80/rates")
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
          },
          "& .pod-column--cell": {
            color: colors.greenAccent[300],
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
          rows={custList.customers}
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

export default Customers;