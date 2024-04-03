import React, { useContext } from 'react';
import { UserContext } from '../context/UserContext';

const UserGreeting = () => {
  const { user } = useContext(UserContext);

  if (!user) {
    return null;
  }

  return <p>Welcome, {user.firstName}!</p>;
};

export default UserGreeting;
