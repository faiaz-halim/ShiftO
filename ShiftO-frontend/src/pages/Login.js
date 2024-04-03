import React, { useState, useContext, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { login } from '../api/authService';
import { UserContext } from '../context/UserContext';

const Login = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();
  const { setUser } = useContext(UserContext);

  useEffect(() => {
    setUser(null);
    localStorage.removeItem('userToken');
  }, [setUser]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const userData = await login(email, password);
      setUser(userData);
      if (userData.role === 'Administrator') {
          navigate('/dashboard/admin');
      } else if (userData.role === 'HR') {
          navigate('/dashboard/hr');
      } else if (userData.role === 'Employee') {
          navigate('/dashboard/employee');
      } else {
          navigate('/');
      }
    } catch (err) {
      setError('Failed to login. Please check your credentials.');
    }
  };

  return (
    <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh' }}>
      <div>
        <h2 style={{ textAlign: 'center' }}>Login</h2>
        {error && <p style={{ color: 'red' }}>{error}</p>}
        <table>
            <tbody>
            <tr>
                <td>Email:</td>
                <td>
                <input
                    type="email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    required
                />
                </td>
            </tr>
            <tr>
                <td>Password:</td>
                <td>
                <input
                    type="password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    required
                />
                </td>
            </tr>
            <tr>
                <td colSpan="2" style={{ textAlign: 'center' }}>
                <button onClick={handleSubmit}>Login</button>
                </td>
            </tr>
            </tbody>
        </table>
      </div>
    </div>
  );
};

export default Login;