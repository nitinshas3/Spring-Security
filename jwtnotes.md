this includes redefining authentication manager, if default then spring security uses session based using cookies , if we want jwt then we have to redefine authentication manager 

# Spring Security Session vs JWT Notes

# Default Spring Security Flow (Without JWT)

By default, Spring Security uses:

# Sessions

After successful login:

1. Authentication object is created.
2. Stored in server-side session.
3. Server sends:

# JSESSIONID

cookie to browser/client.

4. Browser automatically sends this cookie with every request.
5. Server restores user authentication using session.

This is:

# Stateful Authentication

because server stores user state/session.

---

# Spring Security Authentication Flow

```text
Request
   ↓
UsernamePasswordAuthenticationFilter
   ↓
AuthenticationManager
   ↓
AuthenticationProvider
   ↓
UserDetailsService
   ↓
PasswordEncoder
   ↓
Authenticated Authentication Object
   ↓
SecurityContextHolder
```

---

# JWT Workflow

With JWT:

# Sessions are disabled

using:

```text
SessionCreationPolicy.STATELESS
```

Now server does NOT store authentication/session.

---

# JWT Authentication Flow

1. User logs in with username/password.
2. Spring authenticates user normally.
3. Instead of creating session:

# JWT Token

is generated and returned.

4. Client stores JWT token.
5. Every request sends token in:

```text
Authorization Header
```

Example:

```text
Authorization: Bearer <token>
```

---

# JwtAuthenticationFilter

Custom:

# JwtAuthenticationFilter

checks every request.

It:

1. Reads token from header.
2. Validates token.
3. Extracts user details/claims.
4. Creates Authentication object.
5. Stores it in:

# SecurityContextHolder

for current request only.

After request finishes, authentication is gone.

No session stored.

---

# Session vs JWT

## Session Authentication

- Stateful
- Server stores user data
- Uses JSESSIONID cookie
- Easier logout/revocation
- More server memory usage

---

## JWT Authentication

- Stateless
- Client carries token
- Better scalability
- Good for REST APIs/microservices
- Harder token revocation

JWT usually contains:

- User ID
- Roles
- Expiry Time
- Claims

inside token itself.

---

# Important Point

## Session Authentication

```text
Server remembers user
```

---

## JWT Authentication

```text
Every request authenticates again using token
```

No server-side session memory used.

# 🔑 How the pieces fit  #
AuthenticationProviders

Each provider knows how to validate a certain type of credentials (DB, JWT, LDAP, etc.).

Example: DaoAuthenticationProvider checks username/password, JwtAuthenticationProvider checks tokens.

They return either a valid Authentication object or throw an exception.

AuthenticationManager

It’s the coordinator.

When a filter passes an Authentication object, the manager loops through all registered providers.

The first provider that supports that type of authentication will try to validate it.

If valid → success. If none succeed → failure.

AuthenticationConfiguration

This sits at startup/config time, not runtime.

It tells the AuthenticationManager which providers exist and wires them together.

So when the app runs, the manager already knows: “I have Provider A for DB, Provider B for JWT, Provider C for LDAP.”

⚡ Simple analogy
Providers = different security guards (one checks ID cards, one checks QR codes, one checks fingerprints).

Manager = the head guard who decides which guard should check you.

Configuration = the roster that says “these 3 guards are on duty today.”

# 👉 FIRST PHASE LOGIN PLUS FILTER GENERATION  #
Provider → does the actual check.

Manager → tries providers until one succeeds.

Configuration → wires providers into the manager at startup. 

Client sends username/password
↓
AuthenticationManager authenticates
↓
UserDetailsService loads user
↓
Password checked
↓
Authentication object returned
↓
JWT generated
↓
JWT sent to client

SO MAJORLY 2 STEPS TOKEN GENERATION AND VALIDATION FIRST REQUEST TOKEN IS GENERATED , LATER A NEW FILTER OF JWT IS CREATED MANUALLY , IT IS NOT IN SPRING , WE HAVE TO CREATE IT AND ADD IT AS FIRST FILTER BEFORE USERNAME PASSWORD FILTER 

# JWT FILTER TOKEN #
Extract token
↓
Validate token
↓
Extract username
↓
Load user
↓
Create Authentication object
↓
Put in SecurityContext
↓
Continue filter chain

# FINAL WORKFLOW #
LOGIN:

username/password
↓
AuthenticationManager
↓
authenticated
↓
generate JWT
↓
send token



NEXT REQUESTS:

JWT token                                                             
↓
JwtFilter                                                             
↓
validate token                                                        
↓
create Authentication object                                            
↓
SecurityContextHolder
↓
Spring authorizes request
↓
controller executes

# For JWT filters in Spring Security:
- Extend OncePerRequestFilter → ensures filter runs once per request.
- Main logic goes inside doFilterInternal() → extract token, validate, build Authentication.
- Filter sets Authentication into SecurityContextHolder if valid.
- Register filter in SecurityFilterChain so it runs before UsernamePasswordAuthenticationFilter.


