# SkillMentor Platform
## Project Title
SkillMentor – Online Mentoring Platform
## Project Description
SkillMentor is a full-stack online mentoring platform that connects students with expert mentors for specialized subjects. The platform allows students to browse mentors, explore mentor profiles, book one-on-one sessions, upload payment proof, and track their learning progress through a personalized dashboard.
Administrators manage the platform through a dedicated admin dashboard where they can create mentors, assign subjects, monitor bookings, confirm payments, and mark sessions as completed.
The system demonstrates full-stack development by integrating a modern frontend with a secure backend and a relational database, along with authentication and role-based access control.

## Features
### Student Features
```
Students can browse mentors and view their expertise and subjects.
Students can view detailed mentor profiles that include their experience, profession, company, and reviews.
Students can book mentoring sessions by selecting a subject, date, and duration.
Students can upload payment proof after booking a session.
Students can track their session status through a personalized dashboard.
Students can join sessions using meeting links.
```
### Admin Features
```
The system includes an admin dashboard that is protected using role-based access control.
Admins can create mentors through an administrative form.
Admins can create subjects and assign them to mentors.
Admins can view and manage all session bookings.
Admins can confirm payments submitted by students.
Admins can mark sessions as completed once they are delivered.
Admins can add meeting links for scheduled sessions.
```
### Platform Features
```
Authentication and authorization are handled using Clerk.
The system supports public mentor discovery.
Students can complete an entire booking workflow from browsing mentors to attending sessions.
The platform uses PostgreSQL for structured data storage.
The system is deployed using cloud services for both frontend and backend.
```

## Technology Stack
### Frontend
```
The frontend is built using React with TypeScript.
Vite is used as the development build tool.
Tailwind CSS is used for styling and responsive layouts.
The shadcn/ui component library is used for modern UI components.
React Router is used for navigation and routing.
Clerk is used for authentication and session management.
```
### Backend
```
The backend is developed using Spring Boot.
Spring Security is used for authentication and role-based authorization.
Spring Data JPA is used for database operations.
ModelMapper is used for converting entities into DTOs.
```
### Database
```
PostgreSQL is used as the relational database.
The database is hosted using Supabase.
```
### Deployment
```
The frontend is deployed using Vercel.
The backend is deployed using Render.
The database is hosted using Supabase.
```
## Frontend Structure
```
frontend/
└── src/
    ├── App.tsx
    ├── index.css
    ├── main.tsx
    ├── types.ts
    ├── vite-env.d..ts
    │
    ├── assets/
    │   ├── aws-certified-1.webp
    │   ├── aws-certified-2.webp
    │   ├── aws-certified-3.webp
    │   ├── logo.webp
    │   ├── mentor-1.webp
    │   ├── mentor-2.webp
    │   ├── mentor-3.webp
    │   ├── mentor-4.webp
    │   ├── mentor-5.webp
    │   ├── mentor-6.webp
    │   ├── microsoft-certified-1.webp
    │   ├── microsoft-certified-2.webp
    │   └── microsoft-certified-3.webp
    │
    ├── components/
    │   ├── Footer.tsx
    │   ├── Layout.tsx
    │   ├── MentorCard.tsx
    │   ├── Navigation.tsx
    │   ├── SchedulingModel.tsx
    │   ├── SignUpDialog.tsx
    │   ├── StatusPill.tsx
    │   │
    │   ├── admin/
    │   │   ├── AdminLayout.tsx
    │   │   ├── AdminOverviewPage.tsx
    │   │   ├── CreateMentorPage.tsx
    │   │   ├── CreateSubjectPage.tsx
    │   │   └── ManageBookingsPage.tsx
    │   │
    │   ├── hooks/
    │   │   └── use-toast.ts
    │   │
    │   └── ui/
    │       ├── alert.tsx
    │       ├── badge.tsx
    │       ├── button.tsx
    │       ├── calendar.tsx
    │       ├── card.tsx
    │       ├── checkbox.tsx
    │       ├── dialog.tsx
    │       ├── input.tsx
    │       ├── label.tsx
    │       ├── select.tsx
    │       ├── sheet.tsx
    │       ├── textarea.tsx
    │       ├── toast.tsx
    │       └── toaster.tsx
    │
    ├── lib/
    │   ├── api.ts
    │   └── utils.ts
    │
    └── pages/
        ├── DashboardPage.tsx
        ├── HomePage.tsx
        ├── LoginPage.tsx
        ├── MentorProfilePage.tsx
        └── PaymentPage.tsx
```
## Backend Structure
```
backend/
└── skill-mentor-backend-service-skillmentor-backend-final/
    ├── .gitattributes
    ├── .gitignore
    ├── Dockerfile
    ├── Dockerfile2
    ├── docker-compose.yaml
    ├── mvnw
    ├── mvnw.cmd
    ├── pom.xml
    │
    ├── .github/
    │   └── workflows/
    │       └── unit-test.yaml
    │
    ├── .mvn/
    │   └── wrapper/
    │       └── maven-wrapper.properties
    │
    └── src/
        ├── main/
        │   ├── java/
        │   │   └── com/
        │   │       └── stemlink/
        │   │           └── skillmentor/
        │   │               ├── SkillmentorApplication.java
        │   │               │
        │   │               ├── configs/
        │   │               │   ├── CorsConfig.java
        │   │               │   ├── ModelMapperConfig.java
        │   │               │   ├── OpenApiConfig.java
        │   │               │   ├── RedisConfig.java
        │   │               │   ├── SecurityConfig.java
        │   │               │   └── ValidatorConfiguration.java
        │   │               │
        │   │               ├── constants/
        │   │               │   └── UserRoles.java
        │   │               │
        │   │               ├── controllers/
        │   │               │   ├── AbstractController.java
        │   │               │   ├── AdminController.java
        │   │               │   ├── MentorController.java
        │   │               │   ├── SessionController.java
        │   │               │   ├── StudentController.java
        │   │               │   └── SubjectController.java
        │   │               │
        │   │               ├── dto/
        │   │               │   ├── ErrorResponse.java
        │   │               │   ├── MentorDTO.java
        │   │               │   ├── SessionDTO.java
        │   │               │   ├── StudentDTO.java
        │   │               │   ├── SubjectDTO.java
        │   │               │   └── response/
        │   │               │       ├── AdminSessionResponseDTO.java
        │   │               │       └── SessionResponseDTO.java
        │   │               │
        │   │               ├── entities/
        │   │               │   ├── Mentor.java
        │   │               │   ├── Session.java
        │   │               │   ├── Student.java
        │   │               │   └── Subject.java
        │   │               │
        │   │               ├── exceptions/
        │   │               │   └── SkillMentorException.java
        │   │               │
        │   │               ├── respositories/
        │   │               │   ├── MentorRepository.java
        │   │               │   ├── SessionRepository.java
        │   │               │   ├── StudentRepository.java
        │   │               │   └── SubjectRepository.java
        │   │               │
        │   │               ├── security/
        │   │               │   ├── AuthenticationFilter.java
        │   │               │   ├── ClerkValidator.java
        │   │               │   ├── SkillMentorAuthenticationEntryPoint.java
        │   │               │   ├── SkillMentorJwtValidator.java
        │   │               │   ├── TokenValidator.java
        │   │               │   └── UserPrincipal.java
        │   │               │
        │   │               ├── services/
        │   │               │   ├── AdminService.java
        │   │               │   ├── MentorService.java
        │   │               │   ├── SessionService.java
        │   │               │   ├── StudentService.java
        │   │               │   ├── SubjectService.java
        │   │               │   └── impl/
        │   │               │       ├── AdminServiceImpl.java
        │   │               │       ├── MentorServiceImpl.java
        │   │               │       ├── SessionServiceImpl.java
        │   │               │       ├── StudentServiceImpl.java
        │   │               │       └── SubjectServiceImpl.java
        │   │               │
        │   │               └── utils/
        │   │                   └── ValidationUtils.java
        │   │
        │   └── resources/
        │       ├── application.properties
        │       ├── application-dev.properties
        │       └── application-prod.properties
        │
        └── test/
            └── java/
                └── com/
```
## Getting Started (Local Development)
### Prerequisites
```
To run the project locally, the following software must be installed.
Node.js
npm
Java JDK
Maven
PostgreSQL
Git
```
## Environmental Variables
### Frontend Environment Variables
```
The frontend requires the Clerk publishable key and backend API base URL.
VITE_CLERK_PUBLISHABLE_KEY
VITE_API_BASE_URL
```
### Backend Environment Variables
```
The backend requires database credentials, authentication settings, and CORS configuration.
DATABASE_URL
DB_USERNAME
DB_PASSWORD
CLERK_JWKS_URL
CORS_ALLOWED_ORIGINS
PORT
```
## Authentication Setup
```
The system uses Clerk to handle user authentication and identity management.
A JWT template must be configured in Clerk for the backend to verify authentication tokens.
The template should include user information such as email, name, and role metadata.
Admin users are configured by adding role information to the Clerk public metadata.
For example, the metadata may include an admin role attribute that allows access to the administrative dashboard.
```
## API Endpoints
### Public Endpoints
```
These endpoints allow users to browse mentor information.
GET request to retrieve all mentors.
GET request to retrieve details of a specific mentor.
```
### Student Endpoints
```
Students can create and manage session bookings.
POST request to create a new mentoring session booking.
GET request to retrieve sessions belonging to the logged-in student.
POST request to submit a review for a completed session.
```
### Admin Endpoints
```
Admin endpoints allow administrators to manage the platform.
GET request to retrieve all bookings.
PATCH request to confirm a payment.
PATCH request to mark a session as completed.
PATCH request to add a meeting link to a session.
POST request to create a mentor.
POST request to create a subject.
```
## Deployment
### Frontend Deployment
```
The frontend application is deployed using Vercel.
You should include the deployed frontend link here.
```
### Backend Deployment
```
The backend API service is deployed using Render.
You should include the deployed backend link here.
```
### Swagger API Documentation
```
The backend provides API documentation through Swagger.
You should include the Swagger documentation link here.
```
## Submission Links
```
GitHub Repository Link – Frontend - https://github.com/senuthi0125/skillmentor-frontend/tree/working-fix-frontend
Final updates are on working-fix-frontend branch
GitHub Repository Link – Backend - https://github.com/senuthi0125/skill-mentor-backend-service/tree/skillmentor-backend-final
Final updates are on skillmentor-backend-final branch
Frontend Deployment Link - https://skillmentor-frontend-3bw8.vercel.app/
Backend Deployment Link - https://skill-mentor-backend-service-4.onrender.com
Swagger Documentation Link - https://skill-mentor-backend-service-4.onrender.com/swagger-ui/index.html
```
