import axios from 'axios';

const API_BASE_URL = process.env.REACT_APP_API_BASE_URL;

export const getEmployeeSchedule = async (employeeId) => {
  try {
    const response = await axios.get(`${API_BASE_URL}/employee/schedule?employeeId=${employeeId}`);
    return response.data;
  } catch (error) {
    console.error('Error fetching employee schedule:', error);
    throw error;
  }
};

export const requestScheduleChange = async (scheduleRequest) => {
  try {
    console.log(scheduleRequest);
    const response = await axios.post(`${API_BASE_URL}/employee/request-change`, scheduleRequest, {
        headers: {
          'Content-Type': 'application/json'
        }
      });
    return response.data;
  } catch (error) {
    console.error('Error requesting schedule change:', error);
    throw error;
  }
};
