# Smart Campus Operations Hub – Backend

A **Spring Boot + MongoDB REST API** for managing **incident tickets, attachments, technician updates, and comments** in a university environment.

---

## Tech Stack

- **Backend:** Spring Boot (Layered Architecture)
- **Database:** MongoDB (NoSQL)
- **Security:** JWT Authentication
- **API Docs:** OpenAPI (Swagger)
- **Build Tool:** Maven

---

## Features Implemented (Member 3)

### Incident Ticket Management

- Create incident tickets
- Update ticket details
- Assign technician (ADMIN)
- Ticket workflow:

  `OPEN → IN_PROGRESS → RESOLVED → CLOSED`

- Admin can REJECT tickets

---

### Attachments

- Upload up to **3 images per ticket**
- File validation (type + size)
- Stored locally

---

### Comments

- Users & staff can comment
- Edit/Delete only by owner

---

### Technician Updates

- Technician updates status
- Resolution notes
- Full audit log (timeline)

---

### Authentication & Roles

- JWT Login system
- Roles:
  - USER
  - ADMIN
  - TECHNICIAN

---

## How to Run the Project

### 1️ Clone Repository

```bash
git clone https://github.com/your-username/smart-campus-backend.git
cd smart-campus-backend
```

### 2️⃣ Run MongoDB

Make sure MongoDB is running locally:

```text
mongodb://localhost:27017
```

---

### 3️⃣ Configure `application.yml`

```yaml
spring:
  data:
    mongodb:
      uri: ${MONGODB_URI:mongodb://localhost:27017/smart_campus_operations_hub}

file:
  upload-dir: ./uploads
```

---

### 4️⃣ Run Application

```bash
mvn spring-boot:run
```

The backend now starts on port `8081` by default.

---

### 5️⃣ Swagger UI

```text
http://localhost:8081/swagger-ui.html
```

---

## POSTMAN API TESTING GUIDE

---

## 1. Register User

**POST** `/api/v1/auth/register`

```json
{
  "fullName": "John Doe",
  "universityEmailAddress": "john@uni.com",
  "password": "123456",
  "role": "USER"
}
```

---

## 2. Login

**POST** `/api/v1/auth/login`

```json
{
  "universityEmailAddress": "john@uni.com",
  "password": "123456"
}
```

### Response

```json
{
  "data": {
    "token": "JWT_TOKEN_HERE",
    "role": "USER"
  }
}
```

👉 Copy token → use in headers:

```text
Authorization: Bearer YOUR_TOKEN
```

---

## TICKET APIs

---

## ➕ 3. Create Ticket

**POST** `/api/v1/tickets`

```json
{
  "incidentCategory": "HARDWARE_ISSUE",
  "ticketTitle": "Projector not working",
  "description": "Projector in lab 1 is broken",
  "priorityLevel": "HIGH",
  "preferredContactName": "John",
  "preferredContactEmailAddress": "john@uni.com",
  "preferredContactPhoneNumber": "0771234567",
  "resourceIdentifier": "RES-01",
  "resourceName": "Projector",
  "resourceType": "EQUIPMENT",
  "locationIdentifier": "LOC-01",
  "locationName": "Lab 1"
}
```

---

## 4. Get All Tickets

**GET** `/api/v1/tickets?page=0&size=10`

---

## 5. Get Ticket By ID

**GET** `/api/v1/tickets/{ticketId}`

---

## 6. Update Ticket

**PUT** `/api/v1/tickets/{ticketId}`

```json
{
  "ticketTitle": "Updated title",
  "description": "Updated description"
}
```

---

## 7. Assign Technician (ADMIN)

**PUT** `/api/v1/tickets/{ticketId}/assign`

```json
{
  "technicianUserId": "tech123",
  "technicianName": "Tech User"
}
```

---

## 8. Update Ticket Status

**PUT** `/api/v1/tickets/{ticketId}/status`

```json
{
  "status": "IN_PROGRESS"
}
```

---

## 9. Reject Ticket (ADMIN)

**PUT** `/api/v1/tickets/{ticketId}/reject`

```json
{
  "reason": "Invalid request"
}
```

---

## 10. Add Resolution Notes

**PUT** `/api/v1/tickets/{ticketId}/resolution`

```json
{
  "resolutionNotes": "Issue fixed successfully"
}
```

---

## COMMENT APIs

---

## 11. Add Comment

**POST** `/api/v1/tickets/comments/{ticketId}`

```json
{
  "commentText": "Please fix ASAP"
}
```

---

## 12. Update Comment

**PUT** `/api/v1/tickets/comments/{commentId}`

```json
{
  "commentText": "Updated comment"
}
```

---

## 13. Delete Comment

**DELETE** `/api/v1/tickets/comments/{commentId}`

---

## 14. Get Comments

**GET** `/api/v1/tickets/comments/{ticketId}`

---

## ATTACHMENT APIs

---

## ⬆15. Upload Attachment

**POST** `/api/v1/tickets/attachments/{ticketId}`

- Body: **form-data**
  - file: (image)

Max 3 files per ticket

---

## 16. Get Attachments

**GET** `/api/v1/tickets/attachments/{ticketId}`

---

## 17. Delete Attachment

**DELETE** `/api/v1/tickets/attachments/{attachmentId}`

---

## TECHNICIAN UPDATE LOG

---

## 18. Get Updates

**GET** `/api/v1/tickets/updates/{ticketId}`

---

## USER APIs

---

## 19. Get All Users (ADMIN)

**GET** `/api/v1/users?page=0&size=10`

---

## 20. Get User Profile

**GET** `/api/v1/users/{userId}`

---

---

## Authorization Summary

| Role       | Access                    |
| ---------- | ------------------------- |
| USER       | Create ticket, comment    |
| TECHNICIAN | Update status, resolution |
| ADMIN      | Assign, reject, manage    |

---

## Notes

- Always include JWT token in headers:

```text
Authorization: Bearer <token>
```

- File uploads must be:
  - JPEG / PNG / PDF
  - Max size: 5MB

---

## Assignment Coverage

✔ REST APIs (GET, POST, PUT, DELETE)
✔ MongoDB persistence
✔ File handling
✔ Validation & error handling
✔ Role-based security
✔ Full workflow implementation

---

## Author

- Student Name: YOUR NAME
- Module: PAF 2026
- Member Role: **Member 3 – Incident Ticketing System**

---
