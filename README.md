# Call Logging System (Spring Boot + React)

This repository contains a full-stack call logging system with:
- Spring Boot REST API backend
- React (Vite) frontend
- In-memory H2 database for local development

## Backend setup

```bash
cd backend
./mvnw spring-boot:run
```

If Maven Wrapper is not present in your environment, use:

```bash
mvn spring-boot:run
```

Backend runs on: `http://localhost:8080`

### API endpoints
- `GET /api/calls` - list all calls
- `POST /api/calls` - create a call
- `DELETE /api/calls/{id}` - delete a call

Example payload:

```json
{
  "callerName": "Jane Doe",
  "phoneNumber": "+1-202-555-0101",
  "direction": "INBOUND",
  "durationSeconds": 180,
  "status": "COMPLETED",
  "notes": "Discussed contract terms",
  "callTime": "2026-04-24T15:30:00"
}
```

## Frontend setup

```bash
cd frontend
npm install
npm run dev
```

Frontend runs on: `http://localhost:5173`
