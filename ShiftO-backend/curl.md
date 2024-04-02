curl -X POST "http://localhost:8080/auth/login" \
     -H "Content-Type: application/json" \
     -d '{"email": "admin@shiftO.com", "password": "pass123"}'

curl -X POST "http://localhost:8080/admin/register" \
     -H "Content-Type: application/json" \
     -d '{"firstName": "HR", "lastName": "User", "email": "hr@shiftO.com", "role": "HR", "password": "password123"}'

curl -X POST "http://localhost:8080/admin/register" \
     -H "Content-Type: application/json" \
     -d '{"firstName": "John", "lastName": "Doe", "email": "johndoe@shiftO.com", "role": "Employee", "password": "123456"}'

curl "http://localhost:8080/admin/schedules"

curl -X POST "http://localhost:8080/auth/login" \
     -H "Content-Type: application/json" \
     -d '{"email": "hr@shiftO.com", "password": "password123"}'

curl "http://localhost:8080/hr/employee-info?employeeId=3"

curl -X POST "http://localhost:8080/hr/createSchedule" \
     -H "Content-Type: application/json" \
     -d '{"employeeId": "3", "startTime": "2023-10-10T09:00:00", "endTime": "2023-10-10T17:00:00", "status": "Pending"}'

curl -X POST "http://localhost:8080/hr/createSchedule" \
     -H "Content-Type: application/json" \
     -d '{"employeeId": "3", "startTime": "2023-10-11T09:00:00", "endTime": "2023-10-11T17:00:00", "status": "Pending"}'

curl -X POST "http://localhost:8080/hr/createSchedule" \
     -H "Content-Type: application/json" \
     -d '{"employeeId": "3", "startTime": "2023-10-12T09:00:00", "endTime": "2023-10-12T17:00:00", "status": "Pending"}'

curl -X POST "http://localhost:8080/hr/createSchedule" \
     -H "Content-Type: application/json" \
     -d '{"employeeId": "3", "startTime": "2023-10-13T09:00:00", "endTime": "2023-10-13T17:00:00", "status": "Pending"}'

curl "http://localhost:8080/hr/schedule/3"

curl -X POST "http://localhost:8080/auth/login" \
     -H "Content-Type: application/json" \
     -d '{"email": "johndoe@shiftO.com", "password": "123456"}'

curl "http://localhost:8080/employee/schedule?employeeId=3"

curl -X POST "http://localhost:8080/employee/request-change" \
     -H "Content-Type: application/json" \
     -d '{"id": "1", "employeeId": "3", "startTime": "2023-10-14T09:00:00", "endTime": "2023-10-14T17:00:00", "status": "Pending"}'

curl -X POST "http://localhost:8080/employee/request-change" \
     -H "Content-Type: application/json" \
     -d '{"id": "2", "employeeId": "3", "startTime": "2023-10-15T09:00:00", "endTime": "2023-10-15T17:00:00", "status": "Pending"}'

curl -X POST "http://localhost:8080/employee/request-change" \
     -H "Content-Type: application/json" \
     -d '{"id": "3", "employeeId": "3", "startTime": "2023-10-16T09:00:00", "endTime": "2023-10-16T17:00:00", "status": "Pending"}'

curl -X POST "http://localhost:8080/employee/request-change" \
     -H "Content-Type: application/json" \
     -d '{"id": "4", "employeeId": "3", "startTime": "2023-10-17T09:00:00", "endTime": "2023-10-17T17:00:00", "status": "Pending"}'

curl -X POST "http://localhost:8080/hr/approve-request?requestId=1"
curl -X POST "http://localhost:8080/hr/reject-request?requestId=2"

curl -X POST "http://localhost:8080/admin/approve-request?requestId=3"
curl -X POST "http://localhost:8080/admin/reject-request?requestId=4"

curl -X POST "http://localhost:8080/admin/approve?scheduleId=2"
curl -X POST "http://localhost:8080/admin/approve?scheduleId=4"