import axios from 'axios';

const API_BASE_URL = process.env.REACT_APP_API_BASE_URL;

export const login = async (email, password) => {
  try {
    const response = await axios.post(`${API_BASE_URL}/auth/login`, { email, password });
    localStorage.setItem('userToken', response.data.token);
    return response.data;
  } catch (error) {
    console.error('Login error:', error.response.data);
    throw error;
  }
};

export const logout = () => {
  localStorage.removeItem('userToken');
};

export const isUserLoggedIn = () => {
  const token = localStorage.getItem('userToken');
  return !!token;
};

export const axiosInstance = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Authorization': `Bearer ${localStorage.getItem('userToken')}`
  }
});
