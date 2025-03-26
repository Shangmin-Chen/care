# Care

**Care** is a web application designed to help users break down ambitious goals into smaller, manageable sub-tasks. This repository contains the Spring Boot backend and the runnable version of the application, with the frontend build files integrated. The frontend is developed in a separate repository, `care-frontend`, and its build files are copied here after running `npm build`.

## Features
- **Goal Decomposition**: Transform big goals into actionable sub-tasks using a tree structure.
- **Journaling System**: Write and store notes for each task to support planning and reflection.
- **RESTful API**: Powered by Spring Boot for efficient and secure data handling.
- **Integrated Frontend**: Includes static build files from the React-based `care-frontend` repository.
- **Scalable Design**: Architected to grow with user needs and data demands.

## Tech Stack
- **Backend**: Spring Boot (Java) - RESTful API for task, tree, and journal management.
- **Frontend**: Static build files from React (JavaScript), sourced from `care-frontend`.
- **Database**: Currently using H2, will migrate to psql
- **Build Tools**: Maven

## Project Status
- **Current Focus**: Developing and refining the backend tree logic for goal decomposition and task management.
- **Next Feature**: Implementing a journal update API to allow users to modify existing journal entries.
- **Future Plans**: Enhancing the frontend experience in the care-frontend repository.


## Project Structure
```
care/
├── src/                  # Backend source code
│   ├── main/
│   │   ├── java/         # Java source files
│   │   └── resources/    # Configuration and static frontend build files
│   │       └── static/   # Copied build files from care-frontend
│   └── test/             # Test files
├── target/               # Compiled output
├── pom.xml               # Maven configuration
└── README.md             # Project documentation
```

## Prerequisites
- Java 17+ (for Spring Boot)
- Maven
- [Database software, if applicable, e.g., PostgreSQL]

## Installation
1. **Clone the Repository**  
   ```bash
   git clone https://github.com/Shangmin-Chen/care.git
   cd care
   ```

2. **Backend Setup**  
   - Configure `src/main/resources/application.properties` (e.g., database connection).
   - Build and run the application:  
     ```bash
     mvn clean package
     java -jar target/care-0.0.1-SNAPSHOT.jar
     ```
   - The app will be available at `http://localhost:8080`.

3. **Frontend Integration**  
   - The frontend build files are included in `src/main/resources/static`.
   - To update the frontend, navigate to the `care-frontend` repository, run `npm build`, and copy the build files:  
     ```bash
     cp -r ../care-frontend/build/* src/main/resources/static/
     ```
   - This assumes `care-frontend` is a sibling directory. Adjust the path if necessary (e.g., `/path/to/care-frontend/build/*`).

## Usage
- Access the app at `http://localhost:8080` in your browser.
- Use the API directly (e.g., via Postman) at `http://localhost:8080/` for testing.

## Contributing
Contributions are welcome! Please fork the repository, create a feature branch, and submit a pull request.

## License
[Specify your license, e.g., MIT License - see [LICENSE](LICENSE) for details]

## Contact
For questions or feedback, reach out to shangminch@gmail.com.