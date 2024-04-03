import React, { useContext } from 'react';
import { Link } from 'react-router-dom';
import UserGreeting from './UserGreeting';
import { UserContext } from '../context/UserContext';

const Header = () => {
  const { user } = useContext(UserContext);

  const getDashboardLink = () => {
    switch (user?.role) {
      case 'Administrator':
        return '/dashboard/admin';
      case 'HR':
        return '/dashboard/hr';
      case 'Employee':
        return '/dashboard/employee';
      default:
        return '/login';
    }
  };

  return (
    <header style={{ backgroundColor: '#333', color: '#fff', padding: '10px 20px', display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
      <h1>ShiftO</h1>
      <nav>
        <ul style={{ listStyle: 'none', display: 'flex', gap: '20px', margin: 0, padding: 0 }}>
          <li><Link to="/" style={{ color: '#fff', textDecoration: 'none' }}>Home</Link></li>
          <li><Link to="/login" style={{ color: '#fff', textDecoration: 'none' }}>Login</Link></li>
          <li><Link to={getDashboardLink()} style={{ color: '#fff', textDecoration: 'none' }}>Dashboard</Link></li>
        </ul>
      </nav>
      <UserGreeting />
    </header>
  );
};

export default Header;
