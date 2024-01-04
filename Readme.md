# Getting Started

## API Specifications

### Auth Endpoints

**Login:** localhost:8080/auth/login
* **Need** email and password **Return:** userId and jwt

**Register:** localhost:8080/auth/register
* **Need:** username, password, email, phone


### User Endpoints
**Activate:** localhost:8080/user/activate
* open
* need email only

**Disable:** localhost:8080/user/disable
* closed (secure by jwt with role based endpoints)
* user and admin access type
* need email only

**Delete:** localhost:8080/user/delete
* closed
* need email

### Task Endpoints

**Create:** localhost:8080/task/create
* closed
* need: userId as params and TaskDto as json

**Delete:** localhost:8080/task/delete
* closed
* need userId and taskId as params

**Fetch:** localhost:8080/task/get-all?userId=-
* closed 
* need userId as params

**Update:**
* closed
* need taskId and userId as params TaskDto as json

**Update Status:** localhost:8080/task/update/status
* closed
* need taskId and userId as params and Status string as text


### Admin Endpoints
**DashBoard Data:** localhost:8080/admin/dashboard
* closed (open for ADMIN role-type only)

**Activate/Deactivate Accounts:** localhost:8080/admin/account-status
* closed
* need email for account to activate
* need adminId as params


## Frontend Specifications

**/home:** for user

**/admin:** for admin


[Frontend Url](https://taskify-seven-omega.vercel.app/)




