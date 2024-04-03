import axios from 'axios';

const API_BASE_URL = process.env.REACT_APP_API_BASE_URL;

export const registerEmployee = async (employeeData) => {
  try {
    const response = await axios.post(`${API_BASE_URL}/admin/register`, employeeData);
    return response.data;
  } catch (error) {
    console.error('Error registering employee:', error.response.data);
    throw error;
  }
};

export const assignSchedule = async (scheduleDTO) => {
  try {
    const response = await axios.post(`${API_BASE_URL}/admin/schedule`, scheduleDTO);
    return response.data;
  } catch (error) {
    console.error('Error assigning schedule:', error.response.data);
    throw error;
  }
};

export const viewAllSchedules = async () => {
  try {
    const response = await axios.get(`${API_BASE_URL}/admin/schedules`);
    return response.data;
  } catch (error) {
    console.error('Error viewing all schedules:', error.response.data);
    throw error;
  }
};

export const approveSchedule = async (scheduleId) => {
  try {
    const response = await axios.post(`${API_BASE_URL}/admin/approve`, null, { params: { scheduleId } });
    return response.data;
  } catch (error) {
    console.error('Error approving schedule:', error.response.data);
    throw error;
  }
};

export const approveScheduleChange = async (requestId) => {
  try {
    const response = await axios.post(`${API_BASE_URL}/admin/approve-request`, null, { params: { requestId } });
    return response.data;
  } catch (error) {
    console.error('Error approving schedule change request:', error.response.data);
    throw error;
  }
};

export const rejectScheduleChange = async (requestId) => {
  try {
    const response = await axios.post(`${API_BASE_URL}/admin/reject-request`, null, { params: { requestId } });
    return response.data;
  } catch (error) {
    console.error('Error rejecting schedule change request:', error.response.data);
    throw error;
  }
};
