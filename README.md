
#  JWT Auth Backend Project



# Validation Improvements in Backend
## Summary of Changes:
Added ValidationUtils class in the com.security.utils package to validate email format and password strength.

Used strong regular expressions for accurate validation of input data.

Performed validation in service or controller layers before processing requests.

Returned appropriate HTTP responses with error messages on validation failure (e.g., 400 Bad Request).

Utilized @Valid annotation on request DTOs and performed additional manual validation in controllers when necessary.




# User Profile Web Application - Phase 3: Manage Articles

## Project Overview
This project is a modern web application for managing user profiles, developed in multiple phases. **Phase 3** focuses on implementing the **Manage Articles** feature, enabling authenticated users to create, view, update, and delete articles within a dedicated Articles tab in their profile. This phase includes updates to the frontend, backend, and database, with all CRUD operations successfully implemented and tested.

## Features of Phase 3
- **Articles Tab in User Profile**: A dedicated tab in the user profile, accessible only to authenticated users.
- **CRUD Operations for Articles**:
    - **Create**: Users can create new articles with a title and rich text content.
    - **Read**: Display a list of the user's articles with details (title, content, creation date, and update date).
    - **Update**: Edit the title and content of existing articles.
    - **Delete**: Delete articles with user confirmation.
- **Rich Text Editor**: Utilizes Quill for creating and editing article content with formatting options (e.g., bold, italic, lists, links).
- **Authentication**: The Articles tab is accessible only to users authenticated via JWT.
- **Persistent Storage**: Articles are stored in a MySQL database.
- **User Interface**: Responsive design using Bootstrap with localized messages for better user experience.
- **Successful Testing**: All features (frontend, backend, and database) have been thoroughly tested and function without errors.

## Technologies Used
### Frontend
- **Next.js**: React framework for server-side and client-side rendering (using App Router).
- **TypeScript**: For type safety and scalable development.
- **Quill**: Rich text editor for creating and editing article content.
- **Axios**: For making HTTP requests to backend APIs.
- **Bootstrap**: For styling the user interface.

### Backend
- **Spring Boot 3.5.3**: Java framework for building RESTful APIs.
- **Spring Data JPA**: For database management and CRUD operations.
- **Spring Security**: For JWT-based authentication.
- **Java 21**: Java version used for backend development.
- **MySQL Connector**: For connecting to the MySQL database.

### Database
- **MySQL**: Relational database for storing articles with the `articles` table.
- **Articles Table Schema**:
  ```sql
  CREATE TABLE articles (
      id BIGINT PRIMARY KEY AUTO_INCREMENT,
      title VARCHAR(255) NOT NULL,
      content TEXT NOT NULL,
      user_id BIGINT NOT NULL,
      created_at DATETIME NOT NULL,
      updated_at DATETIME NOT NULL
  );
  ```

## Implementation Details
### Frontend
- **Component: `ArticlesTab.tsx`**:
    - Located in the user profile page (`/profile`), accessible only to logged-in users.
    - Includes a form for creating/editing articles with title and content fields (using Quill).
    - Displays a list of user articles with edit and delete options.
    - Handles errors (e.g., unauthenticated access or API failures) with user-friendly messages.
    - Uses Axios for CRUD requests to backend APIs.
- **Component: `RichTextEditor.tsx`**:
    - A Quill-based rich text editor for article content.
    - Uses `next/dynamic` with `{ ssr: false }` to load Quill on the client side, avoiding SSR issues.
    - Supports text formatting (headers, bold, italic, lists, links, etc.).
    - Synchronizes editor content with parent component state for creating and editing articles.

### Backend
- **RESTful APIs**:
    - `POST /api/articles`: Create a new article.
    - `GET /api/articles`: Retrieve the user's articles.
    - `PUT /api/articles/{id}`: Update an existing article.
    - `DELETE /api/articles/{id}`: Delete an article.
- **Authentication**: All APIs are protected with JWT, ensuring only authenticated users can access them.
- **Transaction Management**: Uses `@Transactional` in `ArticleService` to ensure database changes are committed.
- **SQL Logging**: Enabled with `spring.jpa.show-sql=true` for debugging database queries.

### Database
- **Table: `articles`**: Designed to store articles with fields for `id`, `title`, `content`, `user_id`, `created_at`, and `updated_at`.
- **MySQL Connection**: Configured in `application.properties` for MySQL integration.
- **Validation**: Uses `spring.jpa.hibernate.ddl-auto=validate` to ensure table compatibility with the Entity.

## Repositories
- **Backend Repository**: [fullstack-auth-phase3-backend](https://github.com/miladrostami-devjava/fullstack-auth-phase3-backend)
- **Frontend Repository**: [fullstack-auth-phase3-frontend-nextjs](https://github.com/miladrostami-devjava/fullstack-auth-phase3-frontend-nextjs-Article-Tab-Improvements-project)

## Prerequisites
- **Node.js**: Version 18 or higher for the frontend.
- **Java**: Version 21 for the backend.
- **MySQL**: Version 8 for the database.
- **Maven**: For managing backend dependencies.
- **NPM**: For managing frontend dependencies.

## Installation and Setup
1. **Clone the Repositories**:
   ```bash
   git clone https://github.com/miladrostami-devjava/fullstack-auth-phase3-backend
   git clone https://github.com/miladrostami-devjava/fullstack-auth-phase3-frontend-nextjs-Article-Tab-Improvements-project
   ```

2. **Install Frontend**:
   ```bash
   cd fullstack-auth-phase3-frontend-nextjs-Article-Tab-Improvements-project
   npm install
   npm run dev
   ```
   The application runs at `http://localhost:3000`.

3. **Install Backend**:
   ```bash
   cd fullstack-auth-phase3-backend
   mvn install
   mvn spring-boot:run
   ```
   APIs are available at `http://localhost:9090`.

4. **Database Setup**:
    - Create a MySQL database (e.g., `myapp`):
      ```sql
      CREATE DATABASE myapp;
      ```
    - Update `application.properties` in the backend:
      ```properties
      spring.datasource.url=jdbc:mysql://localhost:3306/myapp?useSSL=false&serverTimezone=UTC
      spring.datasource.username=your_username
      spring.datasource.password=your_password
      spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
      spring.jpa.hibernate.ddl-auto=validate
      spring.jpa.show-sql=true
      ```

5. **Create Articles Table**:
   ```sql
   CREATE TABLE articles (
       id BIGINT PRIMARY KEY AUTO_INCREMENT,
       title VARCHAR(255) NOT NULL,
       content TEXT NOT NULL,
       user_id BIGINT NOT NULL,
       created_at DATETIME NOT NULL,
       updated_at DATETIME NOT NULL
   );
   ```

6. **Testing**:
    - Navigate to `http://localhost:3000/profile` and log in.
    - Access the Articles tab and test CRUD operations.

## Testing
- **Create Article**: Verified that articles with title and rich text content are saved in the database.
- **Read Articles**: User articles are displayed correctly in the Articles tab.
- **Update Article**: Changes to title and content are successfully saved.
- **Delete Article**: Articles are deleted with user confirmation.
- **Authentication**: Access to the Articles tab is restricted to authenticated users.
- **Database**: SQL queries logged and verified to ensure proper data storage.

## Resolved Issues
- **Quill Errors**:
    - Issue: Errors like `Class constructor Quill cannot be invoked without 'new'` and `quillRef.current.on is not a function`.
    - Solution: Used `next/dynamic` with `{ ssr: false }` to load Quill on the client side.
- **Database Storage**:
    - Issue: Articles not saving in MySQL.
    - Solution: Corrected `application.properties`, rebuilt the `articles` table, and added `@Transactional`.


## Contributing
To contribute, please create a Pull Request or report issues in the respective repositories:
- Backend: [fullstack-auth-phase3-backend](https://github.com/miladrostami-devjava/fullstack-auth-phase3-backend)
- Frontend: [fullstack-auth-phase3-frontend-nextjs](https://github.com/miladrostami-devjava/fullstack-auth-phase3-frontend-nextjs-Article-Tab-Improvements-project)




## Project Overview

This project is a full-stack **JWT (JSON Web Token) based Authentication and Authorization system**. The backend is developed using **Java 21**, **Spring Boot 3.x.x**, and **Spring Security 6.1.x**, while the frontend uses **Next.js 15.3** with **TypeScript**.

The goal of this project is to provide a modern, secure, and scalable platform for user management, token-based authentication, and access control suitable for web and mobile applications.

---

## Key Features

- **Java 21:** Leveraging the latest Java features for improved performance and maintainability.
- **Spring Boot 3.x.x:** Rapid and standardized development of microservices and Java applications.
- **Spring Security 6.1.x:** Robust implementation of authentication and authorization with top security standards.
- **JWT (JSON Web Token):** Stateless, secure token issuance and validation for authentication.
- **Next.js 15.3 + TypeScript:** Modern, fast, and type-safe frontend development for a user-friendly UI and seamless API interaction.
- **Modular Architecture:** Clear separation of concerns for better readability, testability, and scalability.
- **Complete RESTful API support:** Standard API design for easy client and service integration.
- **Role-Based Access Control (RBAC):** Flexible user role and permission management to precisely control access.
- **Comprehensive Documentation and Developer Guidance:** Easy onboarding and development continuation.

---

## Project Structure

```

jwt-auth-backend/
├── backend/                 # Backend built with Spring Boot and Java 21
│   ├── src/main/java/       # Main Java source code
│   ├── src/main/resources/  # Configuration and resources
│   ├── pom.xml              # Maven build configuration
├── frontend/                # Frontend built with Next.js and TypeScript
│   ├── pages/               # Web pages
│   ├── components/          # UI components
│   ├── public/              # Static assets
│   ├── package.json         # Node.js project configuration
├── README.md                # Initial project documentation
├── HELP.md                  # This help file

````

---

## Getting Started

### Backend

1. Make sure **Java 21** and **Maven** are installed and configured on your system.
2. Navigate to the `backend` directory and run the following commands to build and start the application:

```bash
mvn clean install
mvn spring-boot:run
````

3. The backend server will be available by default at `http://localhost:9090`.
4. Security settings and JWT configurations can be adjusted in `application.properties` or `application.yml`.

### Frontend

1. Navigate to the `frontend` directory.
2. Install dependencies:

```bash
npm install
```

3. Run the development server:

```bash
npm run dev
```

4. The frontend app will be accessible at `http://localhost:3000`.

5. The Frontend Repository app will be accessible at  [Frontend](https://github.com/miladrostami-devjava/FullStack-Project2-JWT-Auth-Part-Frontend-Tag2)

---

## Important References and Documentation

* **Official Apache Maven Documentation:** [https://maven.apache.org/guides/index.html](https://maven.apache.org/guides/index.html)
* **Spring Boot Maven Plugin Reference:** [https://docs.spring.io/spring-boot/3.5.3/maven-plugin](https://docs.spring.io/spring-boot/3.5.3/maven-plugin)
* **Build OCI Image with Spring Boot:** [https://docs.spring.io/spring-boot/3.5.3/maven-plugin/build-image.html](https://docs.spring.io/spring-boot/3.5.3/maven-plugin/build-image.html)
* **Spring Web Documentation:** [https://docs.spring.io/spring-boot/3.5.3/reference/web/servlet.html](https://docs.spring.io/spring-boot/3.5.3/reference/web/servlet.html)
* **Building a RESTful Web Service Guide:** [https://spring.io/guides/gs/rest-service/](https://spring.io/guides/gs/rest-service/)
* **Serving Web Content with Spring MVC:** [https://spring.io/guides/gs/serving-web-content/](https://spring.io/guides/gs/serving-web-content/)
* **Building REST Services with Spring:** [https://spring.io/guides/tutorials/rest/](https://spring.io/guides/tutorials/rest/)

---

## Maven Parent Overrides Note

Due to Maven’s design, some elements such as `<license>` and `<developers>` are inherited from the parent POM to your project’s POM.
In this project, these elements are overridden as empty to prevent unwanted inheritance.
If you switch to a different parent POM and want to inherit those elements, you will need to remove these empty overrides manually.

---

## About the Developer

This project is developed by **Milad Rostami**, a software engineer specialized in Java programming, Spring Frameworks, and modern web development with React and Next.js.
Milad has deep expertise in security, scalable application architecture, and delivering production-grade software solutions.

Connect with Milad and explore his other projects and professional background on LinkedIn:

[Milad Rostami - LinkedIn](https://www.linkedin.com/in/milad-rostami-07798484/)

---

## Support and Contributions

* Please read the documentation before reporting any issues or requesting features.
* Contributions, improvements, and bug fixes are highly welcomed.
* To contribute, please fork the repository and submit a Pull Request.
* For questions or requests, use the Issues section on the GitHub repository.

---

## Summary

This project provides a comprehensive, secure, and modern JWT-based authentication solution for full-stack applications.
It leverages the latest Java and Spring technologies alongside a powerful, type-safe Next.js frontend.
With modular design, advanced security features, and thorough documentation, it is well-suited for real-world production environments.

---

If you want, I can help generate this as a ready-to-use `HELP.md` file or assist with adding further details or custom sections. Just let me know!

```

If you want me to prepare the actual file for you, I can do that too!
```


