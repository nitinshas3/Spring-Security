# Spring Security Notes

Security becomes very important once project becomes usable/public.

One popular security standard is:

# OWASP

OWASP releases:

# Top 10 Security Risks

developers should check for vulnerabilities.

Examples:

- SQL Injection
- Broken Authentication
- XSS
- CSRF

etc.

---

# Spring Security

When we add:

# Spring Security Dependency

Spring automatically secures the application.

By default it provides:

- Login page
- Username
- Generated password

Password is usually shown in terminal logs.

---

# Spring Boot and Servlet

Spring Boot is built on top of:

# Servlet Architecture

Tomcat acts as:

# Servlet Container

It handles:

```text
HTTP Request
       ↓
Servlet Processing
       ↓
HTTP Response
```

---

# DispatcherServlet

Spring MVC has:

# DispatcherServlet

which acts as:

# Front Controller

It receives requests and routes them to controllers.

---

# Filters

Before request reaches DispatcherServlet,
it passes through:

# Filters

Flow:

```text
Request
   ↓
Filter 1
   ↓
Filter 2
   ↓
Filter 3
   ↓
DispatcherServlet
   ↓
Controller
```

Filters can:

- Check authentication
- Validate requests
- Log requests
- Modify requests/responses

---

# Spring Security Filters

When Spring Security is added,
it automatically adds:

# Default Security Filters

Examples:

- Authentication Filter
- Authorization Filter
- CSRF Filter
- Session Management Filter

---

# Custom Filters

Later we can:

- Configure filters
- Disable filters
- Add new filters
- Create custom filters

depending on project requirements.

So yes:

# Spring Security works heavily using filter chains.

---

# HttpServletRequest

In controller methods, Spring can automatically provide:

# HttpServletRequest

if declared as parameter.

This gives access to:

- Headers
- Cookies
- Session
- Request body
- Parameters

Useful for manually inspecting raw request data.

---

# Username and Password

We can set custom credentials inside:

```properties
application.properties
```

Example:

```properties
spring.security.user.name=nitin
spring.security.user.password=1234
```

---

# Using Postman

For secured APIs in Postman:

- Go to Authorization tab
- Select Basic Auth
- Enter username/password

Spring Security then authenticates request.

---

# Session ID

After login/authentication:

# Session ID

gets generated.

Different users get:

# Different Session IDs

Spring uses sessions to track authenticated users.

Session information is usually stored using:

```text
JSESSIONID
```

cookie.
# CSRF Notes

# CSRF

CSRF =

# Cross Site Request Forgery

Example:

You are logged into a trusted website.

Then you visit some malicious website like:

```text
download-free-movie.com
```

That site secretly sends requests using your active session/cookies.

If server trusts that session, attacker can perform actions as you.

Example:

- Change password
- Transfer money
- Delete account

without your permission.

---

# Why Session Matters

After login, browser stores:

# Session ID

like:

```text
JSESSIONID
```

Browser automatically sends this cookie with requests.

Attacker tries to misuse this authenticated session.

---

# CSRF Protection

Spring Security adds:

# CSRF Tokens

to prevent fake requests.

Server checks:

```text
Is this request coming from trusted frontend?
```

---

# Where CSRF Matters

Mostly for:

- POST
- PUT
- DELETE

because they:

# Change server state

Examples:

- Updating profile
- Sending money
- Deleting data

---

# Why Not GET?

GET requests should only:

# Fetch Data

and not modify anything.

So CSRF protection usually focuses on state-changing requests, not GET requests.