import React, { useState, useEffect } from 'react';
import { createSchedule, getEmployeeSchedule, approveScheduleChange, rejectScheduleChange } from '../api/hrService';

const HrDashboard = () => {
  const [employeeIdInput, setEmployeeIdInput] = useState('');
  const [employeeId, setEmployeeId] = useState(null);
  const [newSchedule, setNewSchedule] = useState({ startTime: '', endTime: '' });
  const [schedules, setSchedules] = useState([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    if (employeeId) {
      fetchEmployeeSchedules();
    }
  }, [employeeId]);

  const fetchEmployeeSchedules = async () => {
    setLoading(true);
    try {
      const data = await getEmployeeSchedule(employeeId);
      setSchedules(data);
    } catch (error) {
      console.error('Failed to fetch schedules:', error);
    } finally {
      setLoading(false);
    }
  };

  const handleSubmitNewSchedule = async (e) => {
    e.preventDefault();
    try {
      await createSchedule({ ...newSchedule, employeeId });
      setNewSchedule({ startTime: '', endTime: '' });
      fetchEmployeeSchedules();
    } catch (error) {
      console.error('Failed to create schedule:', error);
    }
  };

  const handleApproval = async (scheduleId, approve) => {
    try {
      if (approve) {
        await approveScheduleChange(scheduleId);
      } else {
        await rejectScheduleChange(scheduleId);
      }
      fetchEmployeeSchedules();
    } catch (error) {
      console.error('Failed to update schedule status:', error);
    }
  };

  return (
    <div style={{ display: 'flex', justifyContent: 'center', flexDirection: 'column', alignItems: 'center' }}>
      <h2>HR Dashboard</h2>
      {!employeeId && (
        <div>
          <input
            type="text"
            placeholder="Enter Employee ID"
            value={employeeIdInput}
            onChange={(e) => setEmployeeIdInput(e.target.value)}
          />
          <button onClick={() => setEmployeeId(employeeIdInput)}>Load Employee Schedules</button>
        </div>
      )}
      {employeeId && (
        <>
          <form onSubmit={handleSubmitNewSchedule} style={{ marginBottom: '20px' }}>
            <input
              type="datetime-local"
              value={newSchedule.startTime}
              onChange={(e) => setNewSchedule({ ...newSchedule, startTime: e.target.value })}
              required
            />
            <input
              type="datetime-local"
              value={newSchedule.endTime}
              onChange={(e) => setNewSchedule({ ...newSchedule, endTime: e.target.value })}
              required
            />
            <button type="submit">Set New Schedule</button>
          </form>
          {loading ? (
            <p>Loading schedules...</p>
          ) : (
            <table style={{ borderCollapse: 'collapse', margin: 'auto' }}>
              <thead>
                <tr>
                  <th style={{ border: '1px solid black', padding: '8px' }}>Date</th>
                  <th style={{ border: '1px solid black', padding: '8px' }}>Start Time</th>
                  <th style={{ border: '1px solid black', padding: '8px' }}>End Time</th>
                  <th style={{ border: '1px solid black', padding: '8px' }}>Status</th>
                  <th style={{ border: '1px solid black', padding: '8px' }}>Actions</th>
                </tr>
              </thead>
              <tbody>
                {schedules.map((schedule) => (
                  <tr key={schedule.id}>
                    <td style={{ border: '1px solid black', padding: '8px' }}>{new Date(schedule.startTime).toLocaleDateString()}</td>
                    <td style={{ border: '1px solid black', padding: '8px' }}>{new Date(schedule.startTime).toLocaleTimeString()}</td>
                    <td style={{ border: '1px solid black', padding: '8px' }}>{new Date(schedule.endTime).toLocaleTimeString()}</td>
                    <td style={{ border: '1px solid black', padding: '8px' }}>{schedule.status}</td>
                    <td style={{ border: '1px solid black', padding: '8px' }}>
                      <button onClick={() => handleApproval(schedule.id, true)}>Approve</button>
                      <button onClick={() => handleApproval(schedule.id, false)}>Reject</button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          )}
        </>
      )}
    </div>
  );
};

export default HrDashboard;
