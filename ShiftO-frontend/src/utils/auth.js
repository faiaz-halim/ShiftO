export const isAuthenticated = () => {
    const token = localStorage.getItem('userToken');
    return !!token;
  };

  export const setAuthToken = (token) => {
    localStorage.setItem('userToken', token);
  };

  export const getAuthToken = () => {
    return localStorage.getItem('userToken');
  };

  export const clearAuthToken = () => {
    localStorage.removeItem('userToken');
  };

  export const setupAuthHeaderForServiceCalls = () => {
    const token = getAuthToken();
    if (token) {
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
    }
  };

  export const removeAuthHeaderFromServiceCalls = () => {
    delete axios.defaults.headers.common['Authorization'];
  };
