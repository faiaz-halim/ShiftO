import axios from 'axios';

const API_BASE_URL = process.env.REACT_APP_API_BASE_URL;

export const createSchedule = async (schedule) => {
  try {
    const response = await axios.post(`${API_BASE_URL}/hr/createSchedule`, schedule);
    return response.data;
  } catch (error) {
    console.error('Error creating schedule:', error);
    throw error;
  }
};

export const getEmployeeSchedule = async (employeeId) => {
  try {
    const response = await axios.get(`${API_BASE_URL}/hr/schedule/${employeeId}`);
    return response.data;
  } catch (error) {
    console.error('Error fetching employee schedule:', error);
    throw error;
  }
};

export const approveScheduleChange = async (requestId) => {
  try {
    const response = await axios.post(`${API_BASE_URL}/hr/approve-request?requestId=${requestId}`);
    return response.data;
  } catch (error) {
    console.error('Error approving schedule change:', error);
    throw error;
  }
};

export const rejectScheduleChange = async (requestId) => {
  try {
    const response = await axios.post(`${API_BASE_URL}/hr/reject-request?requestId=${requestId}`);
    return response.data;
  } catch (error) {
    console.error('Error rejecting schedule change:', error);
    throw error;
  }
};
