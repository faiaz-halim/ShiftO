import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Login from './pages/Login';
import EmployeeDashboard from './pages/EmployeeDashboard';
import HrDashboard from './pages/HrDashboard';
import AdminDashboard from './pages/AdminDashboard';
import AllSchedulesDashboard from './pages/AllSchedulesDashboard';
import Header from './components/Header';
import Footer from './components/Footer';
import { UserProvider } from './context/UserContext';

function App() {
  return (
    <UserProvider>
      <Router>
        <Header />
        <Routes>
          <Route path="/" element={<AllSchedulesDashboard />} />
          <Route path="/login" element={<Login />} />
          <Route path="/dashboard/employee" element={<EmployeeDashboard />} />
          <Route path="/dashboard/hr" element={<HrDashboard />} />
          <Route path="/dashboard/admin" element={<AdminDashboard />} />
        </Routes>
        <Footer />
      </Router>
    </UserProvider>
  );
}

export default App;
