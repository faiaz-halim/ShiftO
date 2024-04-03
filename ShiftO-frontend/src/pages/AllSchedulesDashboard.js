import React, { useEffect, useState } from 'react';
import { getAllApprovedSchedules } from '../api/scheduleService';
import { Link } from 'react-router-dom';


const AllSchedulesDashboard = () => {
  const [schedules, setSchedules] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    const fetchSchedules = async () => {
      try {
        let data = await getAllApprovedSchedules();
        data = data.filter(schedule => schedule.status === 'Approved');
        setSchedules(data);
      } catch (error) {
        console.error('Error fetching schedules:', error);
        setError('Failed to fetch schedules');
      } finally {
        setLoading(false);
      }
    };

    fetchSchedules();
  }, []);

  if (loading) return <div style={{ textAlign: 'center' }}>Loading schedules...</div>;
  if (error) return <div style={{ color: 'red', textAlign: 'center' }}>{error}</div>;

  return (
    <div style={{ textAlign: 'center', marginTop: '50px' }}>
        <div>
            <h1>Welcome to ShiftO</h1>
            <p>The ultimate solution for managing employee schedules efficiently.</p>
            <div>
            <Link to="/login" style={{ marginRight: '10px', textDecoration: 'none', color: 'blue' }}>Login</Link>
            <Link to="/register" style={{ marginLeft: '10px', textDecoration: 'none', color: 'blue' }}>Register</Link>
            </div>
        </div>
        <div style={{ display: 'flex', justifyContent: 'center', marginTop: '20px' }}>
        <table style={{ borderCollapse: 'collapse', width: '80%' }}>
            <thead>
            <tr>
                <th style={{ border: '1px solid black', padding: '8px', textAlign: 'center' }}>Employee Name</th>
                <th style={{ border: '1px solid black', padding: '8px', textAlign: 'center' }}>Start Time</th>
                <th style={{ border: '1px solid black', padding: '8px', textAlign: 'center' }}>End Time</th>
            </tr>
            </thead>
            <tbody>
            {schedules.map((schedule) => (
                <tr key={schedule.id}>
                <td style={{ border: '1px solid black', padding: '8px', textAlign: 'center' }}>{`${schedule.employee.firstName} ${schedule.employee.lastName}`}</td>
                <td style={{ border: '1px solid black', padding: '8px', textAlign: 'center' }}>{new Date(schedule.startTime).toLocaleString()}</td>
                <td style={{ border: '1px solid black', padding: '8px', textAlign: 'center' }}>{new Date(schedule.endTime).toLocaleString()}</td>
                </tr>
            ))}
            </tbody>
        </table>
        </div>
    </div>
  );
};

export default AllSchedulesDashboard;
