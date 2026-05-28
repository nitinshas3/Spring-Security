# Spring Security Learning Project

A hands-on repository exploring **Spring Security fundamentals** in Java.  
This project demonstrates how authentication, authorization, and user identity management work in Spring Boot, with a focus on **filter chains, authentication providers, and custom user details**.

---

## 📖 What You'll Learn

- **SecurityFilterChain & HttpSecurity**
    - How `HttpSecurity.build()` assembles the filter chain.
    - Configuring filters for login, CSRF, and role-based access.

- **Authentication Workflow**
    - Role of `AuthenticationManager` and `AuthenticationProvider`.
    - How providers delegate to `UserDetailsService`.
    - Password validation with `BCryptPasswordEncoder`.

- **UserDetailsService & Database Integration**
    - Implementing `UserDetailsService` to fetch users from DB.
    - Returning `UserDetails` with username, password, roles, and account state.
    - Managing flags like `enabled`, `locked`, and `expired`.

- **Role-Based Access Control**
    - Using `GrantedAuthority` to assign roles (`USER`, `ADMIN`).
    - Restricting endpoints with `@PreAuthorize` and `authorizeHttpRequests`.

---

## 🛠️ Tech Stack

- **Java** (Spring Boot)
- **Spring Security**
- **BCryptPasswordEncoder** for password hashing
- **Maven** for build management

---

## 🚀 Getting Started

### 1. Clone the repo

```bash
git clone https://github.com/nitinshas3/Spring-Security.git
```

### 2. Build with Maven

```bash
mvn clean install
```

### 3. Run the application

```bash
mvn spring-boot:run
```

---

## 📑 Structure

- `SecurityConfig.java` → Configures SecurityFilterChain and HttpSecurity.
- `UserDetailsServiceImpl.java` → Loads users from DB.
- `UserEntity.java` → Represents user table with fields like username, password, role, locked, enabled.
- `notes.md` → Conceptual notes explaining the workflow step-by-step.

---

## 🎯 Why This Repo?

This repo is a starter kit for backend developers learning Spring Security.

It connects theory (filters, providers, principals) with practical code (DB integration, password hashing, role checks).

---

## 👤 Author

Nitin S Shastri