import React, { useState, useEffect, useContext } from 'react';
import { getEmployeeSchedule, requestScheduleChange } from '../api/employeeService';
import { UserContext } from '../context/UserContext';

const EmployeeDashboard = () => {
  const [schedules, setSchedules] = useState([]);
  const [loading, setLoading] = useState(false);
  const { user } = useContext(UserContext);
  const [error, setError] = useState('');
  const [employeeId, setEmployeeId] = useState('');

  useEffect(() => {
    if (user && user.id) {
      fetchEmployeeSchedule(user.id);
      setEmployeeId(user.id);
    }
  }, [user]);

  const fetchEmployeeSchedule = async (employeeId) => {
    setLoading(true);
    try {
      const data = await getEmployeeSchedule(employeeId);
      setSchedules(data);
    } catch (err) {
      setError('Failed to fetch schedule');
    } finally {
      setLoading(false);
    }
  };

  const handleRequestScheduleChange = async (scheduleId, newStartTime, newEndTime) => {
    try {
        const scheduleChangeRequest = JSON.stringify({
            id: scheduleId,
            employeeId: employeeId,
            startTime: newStartTime,
            endTime: newEndTime,
            status: "Pending"
          });
          console.log(scheduleChangeRequest)
      await requestScheduleChange({ scheduleChangeRequest });
      fetchEmployeeSchedule(employeeId);
    } catch (err) {
      setError('Failed to request schedule change');
    }
  };

  return (
    <div>
      <h2 style={{ textAlign: 'center' }}>My Schedules</h2>
      {loading ? (
        <p>Loading schedules...</p>
      ) : error ? (
        <p style={{ color: 'red' }}>{error}</p>
      ) : (
        <table className="schedule-table">
          <thead>
            <tr>
              <th>Date</th>
              <th>Start Time</th>
              <th>End Time</th>
              <th>Status</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            {schedules.length > 0 ? (
              schedules.map((schedule) => (
                <tr key={schedule.id}>
                  <td>{new Date(schedule.startTime).toLocaleDateString()}</td>
                  <td>{new Date(schedule.startTime).toLocaleTimeString()}</td>
                  <td>{new Date(schedule.endTime).toLocaleTimeString()}</td>
                  <td>{schedule.status}</td>
                  <td>{schedule.status === 'Pending' && (
                        <button onClick={() => handleRequestScheduleChange(schedule.id, '2023-10-10T09:00:00', '2023-10-10T17:00:00')}>
                        Request Change
                        </button>
                    )}</td>
                </tr>
              ))
            ) : (
              <tr>
                <td colSpan="4">No schedules found.</td>
              </tr>
            )}
          </tbody>
        </table>
      )}
    </div>
  );
};

export default EmployeeDashboard;
