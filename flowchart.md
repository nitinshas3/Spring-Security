# 1. Session Authentication - Login Flow

```text
POST /login
(username + password)
      │
      ▼
UsernamePasswordAuthenticationFilter
(Spring Security Filter)
      │
      ▼
Creates Authentication Object
(Authentication = UsernamePasswordAuthenticationToken)
      │
      ▼
AuthenticationManager
(Spring calls automatically)
      │
      ▼
DaoAuthenticationProvider
(Spring default provider)
      │
      ▼
UserDetailsService
(Your implementation)
      │
      ▼
Database Lookup
(Repository/JPA)
      │
      ▼
PasswordEncoder.matches()
(Spring does comparison)
      │
      ▼
Authenticated Authentication Object
(principal=userDetails,
 authorities=roles)
      │
      ▼
Stored in SecurityContext
(Spring)
      │
      ▼
Stored in HttpSession
(Spring)
      │
      ▼
JSESSIONID sent to browser
(Spring)
```

---

# 2. Session Authentication - Subsequent Request Flow

```text
GET /profile
      │
      ▼
Browser sends JSESSIONID
(Automatic cookie)
      │
      ▼
SessionManagementFilter
(Spring)
      │
      ▼
Find Session using JSESSIONID
(Spring)
      │
      ▼
Get Authentication Object
from Session
(Spring)
      │
      ▼
Put Authentication into
SecurityContext
(Spring)
      │
      ▼
Authorization Checks
(hasRole(), authenticated())
      │
      ▼
Controller Executes
```

---

# 3. JWT Authentication - Login Flow

```text
POST /login
(username + password)
      │
      ▼
AuthController
(Your controller)
      │
      ▼
Create Authentication Object
(Manual)
      │
      ▼
AuthenticationManager.authenticate()
(Spring)
      │
      ▼
DaoAuthenticationProvider
(Spring)
      │
      ▼
UserDetailsService
(Your implementation)
      │
      ▼
Database Lookup
(Repository/JPA)
      │
      ▼
PasswordEncoder.matches()
(Spring)
      │
      ▼
Authenticated Authentication Object
(Spring returns)
      │
      ▼
Generate JWT
(JwtService)
      │
      ▼
Return JWT
(AuthController)
```

---

# 4. JWT Authentication - Subsequent Request Flow

```text
GET /profile
Authorization:
Bearer eyJhbG...
      │
      ▼
JwtFilter
(Your custom filter)
      │
      ▼
request.getHeader("Authorization")
(Manual)
      │
      ▼
Extract JWT
(Manual)
      │
      ▼
Extract Username
(JwtService)
      │
      ▼
UserDetailsService
(load user again)
      │
      ▼
Validate JWT
(JwtService)
      │
      ▼
Create Authentication Object
(Manual)
      │
      ▼
SecurityContextHolder
.getContext()
.setAuthentication(auth)
(Manual)
      │
      ▼
Authorization Checks
(Spring)
      │
      ▼
Controller Executes
```