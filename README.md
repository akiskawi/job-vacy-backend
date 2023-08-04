# Backend Api for Leave Request App

An application used to manage leave requests made by
employees of a company.

Specifications file: [specs.md](docs/specs.md)   
Design doc: [design-prototype.md](/docs/design-prototype.md)   
E/R Diagram: [ER_diagram.svg](/docs/ER_Diagram.svg)

Frontend Repository: [frontend repo](https://github.com/StavrosSpil/job-vacy-frontend)

After running the API, documentation for the endpoints can be found at `localhost:4000/endpoints`

## Functionalities

1. **Calendar**: View a comprehensive calendar displaying leave schedules and availability at a glance.

2. **Personal Dashboard**: Each user is greeted with a personalized dashboard upon login, displaying their leave balance, recent requests, and other relevant information.

3. **Admin Dashboard**: Administrators enjoy a powerful dashboard with access to all system features and data, enabling seamless management and oversight.

4. **Manager Dashboard**: Team managers have access to a specialized dashboard that empowers them to efficiently oversee and manage their team's leave-related activities.

5. **Email Notifications**: Automatic email notifications are sent when leave requests are submitted, ensuring transparent communication.

6. **Request Status Alerts**: Users receive email notifications upon approval or rejection of their leave requests, keeping them informed at all times.

7. **Role-based Access**:
   - Admin Roles: Extensive access to all system functionalities and data.
   - Manager Roles: Access limited to their respective teams for efficient team-level management.
   - User Roles: Access only to their personal leave data and functionalities.

8. **Team Creation**: Admins have the privilege to create teams, optimizing organization and team-specific management.

9. **Auto Leave Calculation**: Leave entitlements are automatically calculated based on relevant parameters, reducing manual effort.

10. **Diverse Leave Types**: Support for multiple leave types (e.g., vacation, sick leave, maternity/paternity leave) catering to various employee needs.

11. **Report Generation**: Extract comprehensive reports in CSV or Excel formats for insightful analysis and record-keeping.

12. **Request History**: Easily track and review past leave requests, promoting accountability and record management.

## Project Architecture Diagram

![Architecture Diagram](/docs/project-architecture.svg)

### Backend Architecture and Package Structure

This project follows a classic Spring MVC design and structure.

#### Packages
- **config**: Configuration files centralize settings and parameters, facilitating easy management and customization.
- **model**: The Model component contains entity classes representing data structures, business logic,
and relationships within the application. It forms the foundation of the MVC architecture. Models are further
organized in smaller packages which contain:
  - *entity classes*
  - *dao classes*: used for receiving or sending data
  - *exception classes*
  - *enums*
- **repositories**: Data access layer responsible for interacting with the database, including querying and persistence.
- **controllers**: Rest Controllers manage incoming HTTP requests, processing and routing them to appropriate actions in the application.
- **services**: Business logic encapsulated in services to handle various operations and tasks within the application.
- **exceptions**: Custom exception classes for handling specific error scenarios and providing meaningful error messages.
- **test**: Rest Controller used in development for testing purposes.
- **util**: Utility classes containing reusable functions and methods that enhance code modularity and maintainability.
  - **Initializer.java**: Creates sample data for use in development.

## How to Run

<!-- - Download and Install [docker](https://www.docker.com/products/docker-desktop/) -->
- Download this repository
- Download maven
- Run the following commands in the project directory
  - `./mvnw clean package -DskipTests` to first build the app
  - `mvn exec:java -Dexec.mainClass=com.manpower.backendProject.App` in order to run it.

<!-- - `docker-compose up` to start the app
- `docker-compose down` to stop
- In order to build a new image if you have an older version use `docker-compose up --build` -->

*Run with Docker under maintenance*

## Technologies used

- PostgreSQL
- JDK 17
- Java Spring Boot 3.0.5
- Maven
- Docker

## Contributors

<table>
  <tr>
    <td style="text-align:center;"><a href="https://github.com/akiskawi"><img src="https://github.com/akiskawi.png" width="60px;" alt="akiskawi"/><br /><sub><a href="https://github.com/akiskawi">akiskawi</a></sub></a></td>
    <td style="text-align:center;"><a href="https://github.com/dtsimaras"><img src="https://github.com/dtsimaras.png" width="60px;" alt="dtsimaras"/><br /><sub><a href="https://github.com/dtsimaras">dtsimaras</a></sub></a></td>
  </tr>
</table>
