ScrumApp Management System

 Project Context

A software development agency managing multiple web and mobile application projects has experienced significant growth in recent months. Due to the expansion of its team and the increasing number of ongoing projects and tasks, they have identified the need to implement a task management system that allows:

- Tracking the progress of tasks for each project.
- Assigning clear responsibilities among developers, designers, and testers.
- Supervising the progress of projects by managers and administrators.
- Ensuring security and access control, so each employee can view and manage only the information relevant to their work.

 Competency Framework

- Project management with agile methodologies.
- Backend development of a web application (level 3).
- Database management (level 2).
- Developing and executing automated tests (level 3).

 Project Description

The project is a task management system based on a REST API, developed with Java 17 and Spring Boot. The application allows user authentication through JWT (JSON Web Tokens) and roles with different access levels using Spring Security. It uses a MySQL database to store information and employs Docker for containerization, integrating GitHub Actions for CI/CD.

 Project Objectives

1. Reinforce the concepts of API creation.
2. Apply database relationships.
3. Develop login functionality with Spring Security and JWT.
4. Gain initial experience with GitHub Actions.

 Team

- Ana María - (Developer) [GitHub Profile](https://github.com/anamartin99)
- Abraham Martín - (Developer) [GitHub Profile](https://github.com/abmmm19888)
- Eva Martínez - (Product Owner)  [GitHub Profile](https://github.com/EvaMartinez94)
- Norbert "Corvus" Harrow (Scrum Master) - [GitHub Profile](https://github.com/NCorvusH)



 Functional Requirements

 User Roles

- Administrator: Can manage users and tasks.
- Manager: Can create, assign, and manage tasks, but cannot manage users.
- User: Can only view and update the status of their assigned tasks.

 Authentication and Authorization

- Implement authentication with JWT for secure login.
- Role-based authorization with Spring Security, granting different permissions based on the user's assigned role.

 Task CRUD

- Create: Managers can create new tasks and assign them to a user.
- Read: Users can view their tasks, while managers can view tasks assigned to any user.
- Update: Users can update the status of their tasks.
- Delete: Only managers can delete tasks.

 User Management

- Administrators can manage the creation, deletion, and updating of system users, as well as assign roles.
- Filter by User: Managers can view the list of tasks by user and their status.

 Entity Relationships (Database)

- User: Table containing user information and their relationship with tasks.
- Task: Table storing tasks assigned to users.
- Project: Relationship with the User entity (A user can belong to multiple projects, and a project can have multiple users). Relationship with Task (A project can have multiple tasks).

 Entity-Relationship Diagram (ER)

- User:
  - id (PK)
  - username
  - password
  - email
  - role (ADMIN, MANAGER, USER)

- Project:
  - id (PK)
  - name

- Task:
  - id (PK)
  - name
  - description
  - status (PENDING, IN_PROGRESS, COMPLETED)
  - user_id (FK) (Relationship with User)
  - project_id (FK) (Relationship with Project)

  API Endpoints

  Authentication

- Login:
  - `POST /api/auth/login`: User authentication (Returns JWT)
  
- User Registration:
  - `POST /api/auth/register`: Register a new user (Accessible only by ADMIN)

  Users

- List All Users:
  - `GET /api/users`: Only ADMIN
  
- Get User by ID:
  - `GET /api/users/{id}`: ADMIN or the user themselves
  
- Create New User:
  - `POST /api/users`: Only ADMIN
  
- Update User:
  - `PUT /api/users/{id}`: Only ADMIN
  
- Delete User:
  - `DELETE /api/users/{id}`: Only ADMIN

  Projects

- List All Projects:
  - `GET /api/projects`
  
- Create New Project:
  - `POST /api/projects`: Only MANAGER and ADMIN
  
- Update Project:
  - `PUT /api/projects/{id}`: Only MANAGER and ADMIN
  
- Delete Project:
  - `DELETE /api/projects/{id}`: Only MANAGER and ADMIN

  Tasks

- List All Tasks:
  - `GET /api/tasks`: Only MANAGER and ADMIN
  
- Get Specific Task:
  - `GET /api/tasks/{id}`: Users can only get their own tasks
  
- Create New Task:
  - `POST /api/tasks`: Only MANAGER
  
- Update Task:
  - `PUT /api/tasks/{id}`: Users can only update their own tasks
  
- Delete Task:
  - `DELETE /api/tasks/{id}`: Only MANAGER

 Technologies Used

- Java 17: Programming language used for backend development.
- Spring Boot: Framework for building Java applications.
- Spring Security and JWT: For authentication and authorization.
- MySQL: Database used for data storage.
- Docker: For containerization and deployment.
- GitHub Actions: For CI/CD and deployment automation.
- JUnit and Mockito: For automated testing.

  Technical Requirements

1. Knowledge of Java programming.
2. Knowledge of Object-Oriented Programming (OOP).
3. Spring Boot.
4. Spring Security and JWT.
5. Testing.
6. Docker.
7. GitHub Actions.
8. Lombok and `@Autowired` annotation cannot be used.

  How to Run the Project

  Configuration

1. Clone the Repository:

   ```bash
   git clone https://github.com/anamartin99/ScrumApp-1.git





