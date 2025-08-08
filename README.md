
#  JWT Auth Backend Project



# Validation Improvements in Backend
## Summary of Changes:
Added ValidationUtils class in the com.security.utils package to validate email format and password strength.

Used strong regular expressions for accurate validation of input data.

Performed validation in service or controller layers before processing requests.

Returned appropriate HTTP responses with error messages on validation failure (e.g., 400 Bad Request).

Utilized @Valid annotation on request DTOs and performed additional manual validation in controllers when necessary.

# Sample Code:
java
Copy
Edit
package com.security.utils;

import java.util.regex.Pattern;

public class ValidationUtils {

    private static final String EMAIL_REGEX = "^[\\w.-]+@[\\w.-]+\\.\\w{2,}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public static boolean isValidEmail(String email) {
        if (email == null) return false;
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean isValidPassword(String password) {
        if (password == null) return false;
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).{8,}$";
        return password.matches(regex);
    }
}
## Important Notes:
Backend validation is essential and must not be skipped even if frontend validation exists.

Validation should be done in controller or service layers before any business logic execution.

Proper HTTP status codes and error messages should be sent back to the client for better error handling.

Spring Boot’s @Valid, @Email, and @NotBlank annotations can be used, but complex rules often require manual validation logic.




# Frontend Project
[Frontend Project Repository](https://github.com/miladrostami-devjava/-fullstack-auth-phase2-frontend-nextjs-Validation-Improvements-project?tab=readme-ov-file)


# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.5.3/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.5.3/maven-plugin/build-image.html)
* [Spring Web](https://docs.spring.io/spring-boot/3.5.3/reference/web/servlet.html)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.





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


