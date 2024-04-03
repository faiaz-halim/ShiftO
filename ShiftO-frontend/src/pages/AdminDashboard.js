import React, { useState, useEffect, useContext } from 'react';
import {
  registerEmployee,
  assignSchedule,
  viewAllSchedules,
  approveSchedule,
  approveScheduleChange,
  rejectScheduleChange,
} from '../api/adminService';
import { UserContext } from '../context/UserContext';

const AdminDashboard = () => {
  const [schedules, setSchedules] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');
  const { user } = useContext(UserContext);

  useEffect(() => {
    loadAllSchedules();
  }, []);

  const loadAllSchedules = async () => {
    setLoading(true);
    try {
      const data = await viewAllSchedules();
      setSchedules(data);
    } catch (error) {
      setError('Failed to load schedules');
    } finally {
        setLoading(false);
    }
  };

  const handleApproveSchedule = async (scheduleId) => {
    try {
      await approveSchedule(scheduleId);
      loadAllSchedules();
    } catch (error) {
      setError('Failed to approve schedule');
    }
  };

  const handleApproveScheduleChange = async (requestId) => {
    try {
      await approveScheduleChange(requestId);
      loadAllSchedules();
    } catch (error) {
      setError('Failed to approve schedule change');
    }
  };

  const handleRejectScheduleChange = async (requestId) => {
    try {
      await rejectScheduleChange(requestId);
      loadAllSchedules();
    } catch (error) {
      setError('Failed to reject schedule change');
    }
  };

  return (
    <div>
      <h2 style={{ textAlign: 'center' }}>Admin Dashboard</h2>
      {loading ? (
        <p>Loading schedules...</p>
      ) : error ? (
        <p style={{ color: 'red' }}>{error}</p>
      ) : (
        <table className="schedule-table">
          <thead>
            <tr>
              <th>Date</th>
              <th>Employee ID</th>
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
                  <td>{schedule.employee.id}</td>
                  <td>{new Date(schedule.startTime).toLocaleTimeString()}</td>
                  <td>{new Date(schedule.endTime).toLocaleTimeString()}</td>
                  <td>{schedule.status}</td>
                  <td>{schedule.status === 'Pending' && (
                    <>
                        <button onClick={() => handleApproveSchedule(schedule.id)}>Approve Schedule</button>
                        <button onClick={() => handleApproveScheduleChange(schedule.requestId)}>Approve Change</button>
                        <button onClick={() => handleRejectScheduleChange(schedule.requestId)}>Reject Change</button>
                    </>
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

export default AdminDashboard;
