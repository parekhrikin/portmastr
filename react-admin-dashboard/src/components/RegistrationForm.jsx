// import React, { useState } from 'react';
// import { Formik, Form, Field, ErrorMessage } from 'formik';
// import {
//   Box,
//   Button,
//   Container,
//   TextField,
//   Typography,
//   FormControl,
//   InputLabel,
//   Select,
//   MenuItem,
//   Grid,
// } from '@mui/material';
// import { styled } from '@mui/system';
// import RequiredAuth from "./RequiredAuth";
// import { useNavigate } from 'react-router-dom';
// import * as Yup from 'yup';
// import { Dialog, DialogTitle, DialogContent } from '@mui/material';

// const RegistrationContainer = styled(Container)({
//   display: 'flex',
//   justifyContent: 'center',
//   alignItems: 'center',
//   minHeight: '100vh',
// });

// const Title = styled(Typography)({
//   marginBottom: '2rem',
//   textAlign: 'center',
// });

// const SubmitButton = styled(Button)({
//   marginTop: '2rem',
// });

// const CancelButton = styled(Button)({
//   marginTop: '1rem',
// });

// const validationSchema = Yup.object().shape({
//   firstName: Yup.string().required('First Name is required'),
//   lastName: Yup.string().required('Last Name is required'),
//   email: Yup.string().email('Invalid email').required('Email is required'),
//   userType: Yup.string().required('Type of User is required'),
//   companyName: Yup.string().required('Company Name is required'),
//   websiteLink: Yup.string().url('Invalid URL').required('Website Link is required'),
//   teusHandled: Yup.number().required('Number of TEUs Handled is required'),
//   phoneNumber: Yup.string().required('Phone Number is required'),
// });

// const initialValues = {
//   firstName: '',
//   lastName: '',
//   email: '',
//   userType: '',
//   companyName: '',
//   websiteLink: '',
//   teusHandled: '',
//   phoneNumber: '',
// };

// const userTypes = [
//   { value: 'admin', label: 'Admin' },
//   { value: 'freightForwarder', label: 'Freight Forwarder' },
//   { value: 'importer', label: 'Importer' },
//   { value: 'exporter', label: 'Exporter' },
// ];

// function RegistrationForm() {

//   const [isDialogOpen, setIsDialogOpen] = useState(false);
//   const [dialogMessage, setDialogMessage] = useState('');

//   const navigate = useNavigate();

  // const handleSubmit = (values) => {
  //   // Handle registration logic here
  //   console.log(values);
  //   fetch('http://localhost:80/user/register', {
  //     method: 'POST',
  //     headers: {
  //       'Content-Type': 'application/json',
  //     },
  //     body: JSON.stringify(values),
  //   })
  //     .then((response) => {
  //       if (response.ok) {
  //         // Handle success
  //         setDialogMessage('User registered successfully!');
  //         setIsDialogOpen(true);
  //       } else if (response.status === 304) {
  //         // Handle Not Modified
  //         setDialogMessage('Not Modified');
  //         setIsDialogOpen(true);
  //       } else {
  //         // Handle error
  //         throw new Error('Failed to call endpoint');
  //       }
  //     })
  //     .catch((error) => {
  //       // Handle any errors that occur during the request
  //       setDialogMessage('Error: ' + error.message);
  //       setIsDialogOpen(true);
  //     });
  // };

//   const handleDialogClose = () => {
//     setIsDialogOpen(false);
//     setDialogMessage('');
//   };

//   const handleCancel = () => {
//     // Navigate back to the RequiredAuth component
//     navigate('/');
//   };

//   return (
//     <RegistrationContainer>
//       <Box sx={{ maxWidth: 600, p: 2 }}>
//         <Title variant="h5" component="h1">
//           Register for Portmaster
//         </Title>
//         <Formik
//           initialValues={initialValues}
//           validationSchema={validationSchema}
//           onSubmit={handleSubmit}
//         >
//           {({ errors, touched }) => (
//             <form onSubmit={handleSubmit}>
//               <Grid container spacing={2}>
//                 <Grid item xs={12} sm={6}>
//                   <Field
//                     as={TextField}
//                     label="First Name"
//                     name="firstName"
//                     error={touched.firstName && errors.firstName}
//                     helperText={touched.firstName && errors.firstName}
//                     fullWidth
//                     required
//                   />
//                 </Grid>
//                 <Grid item xs={12} sm={6}>
//                   <Field
//                     as={TextField}
//                     label="Last Name"
//                     name="lastName"
//                     error={touched.lastName && errors.lastName}
//                     helperText={touched.lastName && errors.lastName}
//                     fullWidth
//                     required
//                   />
//                 </Grid>
//                 <Grid item xs={12}>
//                   <Field
//                     as={TextField}
//                     label="Email Address"
//                     name="emailAddress"
//                     error={touched.emailAddress && errors.emailAddress}
//                     helperText={touched.emailAddress && errors.emailAddress}
//                     fullWidth
//                     required
//                   />
//                 </Grid>
//                 <Grid item xs={12}>
//                   <Field
//                     as={FormControl}
//                     fullWidth
//                     required
//                   >
//                     <InputLabel id="user-type-label">Type of User</InputLabel>
//                     <Field as={Select} labelId="user-type-label" name="userType">
//                       {userTypes.map((type) => (
//                         <MenuItem key={type.value} value={type.value}>
//                           {type.label}
//                         </MenuItem>
//                       ))}
//                     </Field>
//                     <ErrorMessage name="userType" component="div" className="error" />
//                   </Field>
//                 </Grid>
//                 <Grid item xs={12}>
//                   <Field
//                     as={TextField}
//                     label="Company Name"
//                     name="companyName"
//                     error={touched.companyName && errors.companyName}
//                     helperText={touched.companyName && errors.companyName}
//                     fullWidth
//                     required
//                   />
//                 </Grid>
//                 <Grid item xs={12}>
//                   <Field
//                     as={TextField}
//                     label="Website Link"
//                     name="websiteLink"
//                     error={touched.websiteLink && errors.websiteLink}
//                     helperText={touched.websiteLink && errors.websiteLink}
//                     fullWidth
//                     required
//                   />
//                 </Grid>
//                 <Grid item xs={12} sm={6}>
//                   <Field
//                     as={TextField}
//                     label="Number of TEUs Handled Monthly"
//                     name="approxTEUs"
//                     error={touched.approxTEUs && errors.approxTEUs}
//                     helperText={touched.approxTEUs && errors.approxTEUs}
//                     fullWidth
//                     required
//                   />
//                 </Grid>
//                 <Grid item xs={12} sm={6}>
//                   <Field
//                     as={TextField}
//                     label="Phone Number"
//                     name="phoneNumber"
//                     error={touched.phoneNumber && errors.phoneNumber}
//                     helperText={touched.phoneNumber && errors.phoneNumber}
//                     fullWidth
//                     required
//                   />
//                 </Grid>
//               </Grid>
//               <SubmitButton type="submit" variant="contained" color="primary" onClick={handleSubmit}>
//                 Register
//               </SubmitButton>

//               <CancelButton variant="contained" onClick={handleCancel}>
//                 Cancel
//               </CancelButton>

//               <Dialog open={isDialogOpen} onClose={handleDialogClose}>
//                 <DialogTitle>Registration Status</DialogTitle>
//                 <DialogContent>{dialogMessage}</DialogContent>
//                 <Button onClick={handleDialogClose} color="primary" autoFocus>
//                   OK
//                 </Button>
//               </Dialog>
//             </form>
//           )}
//         </Formik>
//       </Box>
//     </RegistrationContainer>
//   );
// }

// export default RegistrationForm;

import React, { useState } from 'react';

function MyForm() {
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [emailAddress, setEmailAddress] = useState('');
  const [userType, setUserType] = useState('');
  const [companyName, setCompanyName] = useState('');
  const [websiteLink, setWebsiteLink] = useState('');
  const [approxTEUs, setApproxTEUs] = useState('');
  const [phoneNumber, setPhoneNumber] = useState('');

  const [isDialogOpen, setIsDialogOpen] = useState(false);
  const [dialogMessage, setDialogMessage] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    // Perform form submission logic here
    // You can make an API request to store the data in the database

    const firstName = e.target.elements.firstName.value;
    const lastName = e.target.elements.lastName.value;
    const emailAddress = e.target.elements.emailAddress.value;
    const userType = e.target.elements.userType.value;
    const companyName = e.target.elements.companyName.value;
    const websiteLink = e.target.elements.websiteLink.value;
    const approxTEUs = e.target.elements.approxTEUs.value;
    const phoneNumber = e.target.elements.phoneNumber.value;

    // Create the data object
    const values = {
      firstName,
      lastName,
      emailAddress,
      userType,
      companyName,
      websiteLink,
      approxTEUs,
      phoneNumber,
    };
    console.log(values);
    fetch('http://localhost:80/user/register', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(values),
    })
      .then((response) => {
        if (response.ok) {
          // Handle success
          setDialogMessage('User registered successfully!');
          setIsDialogOpen(true);
        } else if (response.status === 304) {
          // Handle Not Modified
          setDialogMessage('Not Modified');
          setIsDialogOpen(true);
        } else {
          // Handle error
          throw new Error('Failed to call endpoint');
        }
      })
      .catch((error) => {
        // Handle any errors that occur during the request
        setDialogMessage('Error: ' + error.message);
        setIsDialogOpen(true);
      });
    // Reset the form fields
    setFirstName('');
    setLastName('');
    setEmailAddress('');
    setUserType('');
    setCompanyName('');
    setWebsiteLink('');
    setApproxTEUs('');
    setPhoneNumber('');
  };

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label htmlFor="firstName">First Name</label>
        <input
          type="text"
          id="firstName"
          value={firstName}
          onChange={(e) => setFirstName(e.target.value)}
          required
        />
      </div>

      <div>
        <label htmlFor="lastName">Last Name</label>
        <input
          type="text"
          id="lastName"
          value={lastName}
          onChange={(e) => setLastName(e.target.value)}
          required
        />
      </div>

      <div>
        <label htmlFor="emailAddress">Email Address</label>
        <input
          type="email"
          id="emailAddress"
          value={emailAddress}
          onChange={(e) => setEmailAddress(e.target.value)}
          required
        />
      </div>

      <div>
        <label htmlFor="userType">User Type</label>
        <select
          id="userType"
          value={userType}
          onChange={(e) => setUserType(e.target.value)}
          required
        >
          <option value="">Select User Type</option>
          <option value="admin">Admin</option>
          <option value="freightForwarder">Freight Forwarder</option>
          <option value="importer">Importer</option>
          <option value="exporter">Exporter</option>
        </select>
      </div>

      <div>
        <label htmlFor="companyName">Company Name</label>
        <input
          type="text"
          id="companyName"
          value={companyName}
          onChange={(e) => setCompanyName(e.target.value)}
          required
        />
      </div>

      <div>
        <label htmlFor="websiteLink">Website Link</label>
        <input
          type="url"
          id="websiteLink"
          value={websiteLink}
          onChange={(e) => setWebsiteLink(e.target.value)}
          required
        />
      </div>

      <div>
        <label htmlFor="approxTEUs">Approximate TEUs Handled Monthly</label>
        <input
          type="number"
          id="approxTEUs"
          value={approxTEUs}
          onChange={(e) => setApproxTEUs(e.target.value)}
          required
        />
      </div>

      <div>
        <label htmlFor="phoneNumber">Phone Number</label>
        <input
          type="tel"
          id="phoneNumber"
          value={phoneNumber}
          onChange={(e) => setPhoneNumber(e.target.value)}
          required
        />
      </div>

      <button type="submit">Submit</button>
    </form>
  );
}

export default MyForm;