# Backend Api for Leave Request App

An application used to manage leave requests made by
employees of a company.

Specifications file: [specs.md](docs/specs.md)   
Design doc: [design-prototype.md](/docs/design-prototype.md)   
E/R Diagram: [ER_diagram.svg](/docs/ER_Diagram.svg)

Frontend Repository: [frontend repo](https://github.com/StavrosSpil/job-vacy-frontend)

After running the API, documentation for the endpoints can be found at `localhost:4000/endpoints`

### How to Install

- Download and Install [docker](https://www.docker.com/products/docker-desktop/)
- Download this repository
- Run the following commands to build docker images
  - `./mvnw clean package -DskipTests`
- `docker-compose up` to start the app
- `docker-compose down` to stop
- In order to build a new image if you have an older version use `docker-compose up --build`

### Technologies used

- PostgreSQL
- JDK 17
- Java Spring Boot 3.0.5
- Maven
- Docker