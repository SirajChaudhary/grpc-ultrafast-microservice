# Employee Directory Service

Modern microservices often require fast and efficient service-to-service API communication. gRPC addresses this by using **HTTP/2** and **Protocol Buffers**, enabling high-performance RPC APIs with compact binary messages, faster serialization and deserialization, lower network latency, and reduced bandwidth usage compared to traditional REST APIs.

This project demonstrates how to build a gRPC-based microservice using **Spring Boot** and **Spring gRPC**. It follows a **Contract-First Development** approach using Protocol Buffers (`.proto`) and implements a layered architecture with Spring Data JPA and PostgreSQL.

# Project Overview

- Demonstrates enterprise-grade gRPC API development using Spring Boot.
- Uses Protocol Buffers (`.proto`) to define API contracts.
- Implements CRUD, search, validation, pagination, and sorting APIs.
- Uses the Unary RPC communication pattern.
- Follows a layered architecture with a clean separation of concerns.
- Automatically generates client and server code from the `.proto` contract.
- Uses PostgreSQL for data persistence.
- Provides centralized exception handling using Spring gRPC.
- Designed as a learning project for modern gRPC-based microservices.

### Features

- Contract-First Development
- Unary RPC
- Protocol Buffers Serialization
- Automatic Code Generation
- Layered Architecture
- CRUD APIs
- Search APIs
- Validation APIs
- Pagination
- Sorting
- Global Exception Handling
- Spring Data JPA
- PostgreSQL

### Technology Stack

| Technology | Version |
|------------|---------|
| Java | 25 |
| Spring Boot | 4.1 |
| Spring gRPC | Latest |
| Spring Data JPA | Latest |
| Protocol Buffers | 33+ |
| PostgreSQL | Latest |
| Maven | Latest |
| Lombok | Latest |
| Spring Boot Actuator | Latest |

### Project Architecture

```text
                              HTTP/2
+-------------+      Protocol Buffers      +----------------------+
| gRPC Client | -------------------------> | EmployeeGrpcService  |
+-------------+                            +----------+-----------+
                                                      |
                                              EmployeeService
                                                      |
                                             EmployeeRepository
                                                      |
                                                 PostgreSQL
```

### Request Flow

```text
Client
   │
   │ gRPC Request
   ▼
EmployeeGrpcService
   │
   ▼
EmployeeService
   │
   ▼
EmployeeRepository
   │
   ▼
PostgreSQL
```

### Project Structure

```text
employee-directory-service
│
├── database
│   └── employee-directory-db-and-sample-data.sql
│
├── src
│   ├── main
│   │
│   ├── java
│   │   └── com.sirajchaudhary.employeedirectory
│   │       ├── config
│   │       │   └── GrpcConfig.java
│   │       ├── entity
│   │       │   └── Employee.java
│   │       ├── exception
│   │       │   ├── GlobalGrpcExceptionHandler.java
│   │       │   ├── EmployeeAlreadyExistsException.java
│   │       │   └── EmployeeNotFoundException.java
│   │       ├── grpc
│   │       │   └── EmployeeGrpcService.java    <------ gRPC API Implementation (Implements all RPCs defined in employee.proto)
│   │       ├── mapper
│   │       │   └── EmployeeMapper.java
│   │       ├── repository
│   │       │   └── EmployeeRepository.java
│   │       ├── service
│   │       │   ├── EmployeeService.java
│   │       │   └── impl
│   │       │       └── EmployeeServiceImpl.java
│   │       └── EmployeeDirectoryApplication.java
│   │
│   ├── proto
│   │   └── employee.proto                      <------ gRPC API Contract
│   │
│   └── resources
│       ├── application.yml
│       └── banner.txt
│
├── pom.xml
└── README.md
```

You can quickly understand the project by exploring the [`employee.proto`](employee-directory-service/src/main/proto/employee.proto) contract and its [`EmployeeGrpcService.java`](employee-directory-service/src/main/java/com/sirajchaudhary/employeedirectory/grpc/EmployeeGrpcService.java) implementation.

### Project Skeleton

The initial project skeleton was generated using **Spring Initializr**. The gRPC service, Protocol Buffers contract, business logic, persistence layer, exception handling, and PostgreSQL integration were then implemented following a layered architecture.

---

# What is gRPC

**gRPC (Google Remote Procedure Call)** is an open-source, high-performance Remote Procedure Call (RPC) framework developed by Google. It enables applications to communicate with each other as if they were calling local methods, making it ideal for distributed systems and microservices.

gRPC uses **HTTP/2** as its transport protocol and **Protocol Buffers (protobuf)** as its default message format, providing fast, efficient, and strongly typed communication.

### History

- Google originally developed an internal RPC framework called **Stubby**.
- In **2015**, Google open-sourced its successor as **gRPC**.
- Today, gRPC is maintained by the **Cloud Native Computing Foundation (CNCF)** and is widely adopted in cloud-native applications.

### Why gRPC?

As distributed systems and microservices grew, traditional REST APIs introduced challenges such as larger payloads, slower serialization, and manual client development.

gRPC addresses these challenges by providing:

- High-performance communication.
- Strong API contracts using Protocol Buffers.
- Automatic client and server code generation.
- Built-in streaming support.
- Multi-language interoperability.

### Key Features

- High-performance RPC framework.
- Contract-First Development.
- HTTP/2 based communication.
- Protocol Buffers serialization.
- Automatic client and server code generation.
- Strongly typed APIs.
- Built-in streaming support.
- Backward-compatible contracts.
- Multi-language interoperability.

### Advantages

- Fast communication between services.
- Compact binary messages.
- Lower network bandwidth usage.
- Lower CPU and memory consumption.
- Automatic client SDK generation.
- Strongly typed API contracts.
- Built-in streaming support.
- Excellent for microservices and distributed systems.

### Limitations

- Not directly supported by web browsers.
- Requires knowledge of Protocol Buffers (`.proto`).
- Harder to debug than JSON-based REST APIs.
- Less suitable for public APIs consumed by third-party clients.

### Common Use Cases

gRPC is widely used for:

- Microservices communication.
- Cloud-native applications.
- Banking and financial systems.
- E-commerce platforms.
- Machine learning services.
- IoT applications.
- Real-time systems.
- High-performance internal APIs.

### When to Choose REST Instead

REST is generally a better choice for:

- Public APIs.
- Browser-based applications.
- Third-party integrations.
- Simple CRUD applications.
- APIs where human-readable JSON is preferred.

### HTTP/2

gRPC uses **HTTP/2** by default, which provides several improvements over HTTP/1.1.

Benefits include:

- Multiplexing multiple requests over a single connection.
- Header compression.
- Persistent connections.
- Lower latency.
- Better network utilization.
- Improved overall performance.

---

# Protocol Buffers (.proto)

**Protocol Buffers (protobuf)** are Google's language-neutral, platform-neutral, and extensible mechanism for serializing structured data. They are the default message format used by **gRPC**.

Instead of manually creating request and response classes, you define the API contract in a **`.proto`** file. During the build process, gRPC automatically generates the required client and server code.

### Why Protocol Buffers?

Protocol Buffers provide several advantages:

- Contract-First Development.
- Strongly typed request and response messages.
- Automatic client and server code generation.
- Compact binary serialization.
- Smaller payloads.
- Faster serialization and deserialization.
- Multi-language support.
- Backward and forward compatibility.
- Easy API versioning.

### What is a `.proto` File?

A `.proto` file defines the complete API contract between the client and server.

It typically contains:

- Package name
- Service definition
- RPC methods
- Request messages
- Response messages
- Data types

The same `.proto` contract can be shared across multiple applications and programming languages.

### Basic Structure

```proto
syntax = "proto3";

package employeedirectory.v1;

option java_package = "com.sirajchaudhary.employeedirectory.grpc";
option java_multiple_files = true;

service EmployeeService {

  rpc GetEmployee(GetEmployeeRequest)
      returns (EmployeeResponse);

}

message GetEmployeeRequest {
  int64 id = 1;
}

message EmployeeResponse {
  int64 id = 1;
  string employeeCode = 2;
  string firstName = 3;
}
```

### Main Components

| Component | Purpose |
|-----------|---------|
| `syntax` | Specifies the Protocol Buffers version. |
| `package` | Groups related services and messages. |
| `option` | Configures language-specific code generation. |
| `service` | Defines the gRPC service. |
| `rpc` | Defines a remote procedure (API). |
| `message` | Defines request and response objects. |

### Common Data Types

| Proto Type | Java Type | Example |
|------------|-----------|---------|
| `string` | String | Employee Name |
| `bool` | boolean | Active |
| `int32` | int | Age |
| `int64` | long | Employee ID |
| `float` | float | Rating |
| `double` | double | Salary |
| `bytes` | byte[] | Image |
| `repeated` | List | Skills |
| `enum` | Enum | Status |

### Message Numbering

Every field inside a message has a unique field number.

```proto
message Employee {

  int64 id = 1;
  string employeeCode = 2;
  string firstName = 3;
  string lastName = 4;

}
```

Field numbers uniquely identify data during binary serialization.

Best Practices

- Never change existing field numbers.
- Never reuse deleted field numbers.
- Add new fields using new field numbers.
- Maintain backward compatibility whenever possible.

### Contract-First Development

Development typically follows this workflow:

```text
Create .proto Contract
          │
          ▼
Generate Java Classes
          │
          ▼
Implement gRPC Service
          │
          ▼
Build & Run Application
          │
          ▼
Client Invokes RPC Methods
```

### Generate Java Classes

Install the Protocol Buffers compiler.

```bash
brew install protobuf

protoc --version
```

Install Buf.

```bash
brew install buf

buf --version
```

Build the project.

```bash
mvn clean install
```

During the build, Java source code is generated automatically.

Generated files:

```text
target/generated-sources/protobuf
├── java
└── grpc-java
```

In IntelliJ IDEA:

- Right-click `target/generated-sources/protobuf`
- Select **Mark Directory As**
- Select **Generated Sources Root**

### Generated Classes

The Maven build generates classes similar to:

```text
EmployeeServiceGrpc.java
EmployeeMessage.java
CreateEmployeeRequest.java
GetEmployeeRequest.java
UpdateEmployeeRequest.java
EmployeeResponse.java
```

You only implement the generated service base class.

```java
@GrpcService
public class EmployeeGrpcService
        extends EmployeeServiceGrpc.EmployeeServiceImplBase {

}
```

The request and response classes are generated automatically from the `.proto` contract.

### Supported Languages

The same `.proto` contract can generate client and server code for multiple programming languages.

| Language | Common Framework |
|-----------|------------------|
| Java | Spring Boot |
| Kotlin | Spring Boot |
| Go | Go gRPC |
| Python | grpcio |
| C# | ASP.NET Core |
| C++ | gRPC C++ |
| Node.js | @grpc/grpc-js |
| JavaScript | gRPC-Web |
| PHP | gRPC PHP |
| Ruby | gRPC Ruby |
| Dart | Flutter |

Example:

```text
                 employee.proto
                       │
        ┌──────────────┼──────────────┐
        ▼              ▼              ▼
 Spring Boot       Python ML       Go Service
    (Java)           Service
        │              │              │
        └──────────────┼──────────────┘
                       │
              Shared API Contract
```

This allows applications written in different languages to communicate using the same API contract without additional integration code.

---

# gRPC Communication Patterns

gRPC supports four communication patterns. Each pattern defines how the client and server exchange messages and is suited for different use cases.

### 1. Unary RPC

The client sends **one request**, and the server returns **one response**.

```text
+--------+                    +--------+
| Client | ---- Request ----> | Server |
|        | <--- Response ---- |        |
+--------+                    +--------+
```

**Characteristics**

- One request and one response.
- Simplest and most commonly used communication pattern.
- Similar to a REST API request.
- Easy to implement and debug.

**Common Use Cases**

- Get Employee
- Create Employee
- Update Employee
- Delete Employee
- Login
- Place Order
- Get Customer Details

👉 This project implements the **Unary RPC** communication pattern, where each API receives a single request and returns a single response. Unary RPC is the simplest and most commonly used communication pattern, making it ideal for CRUD-based services like this Employee Directory Service.

### 2. Server Streaming RPC

The client sends **one request**, and the server returns **multiple responses**.

```text
+--------+                    +--------+
| Client | ---- Request ----> | Server |
|        | <--- Response 1 -- |        |
|        | <--- Response 2 -- |        |
|        | <--- Response 3 -- |        |
+--------+                    +--------+
```

**Characteristics**

- One client request.
- Multiple server responses.
- The server continuously streams data until the operation completes.

**Common Use Cases**

- Live Stock Prices
- Live Cricket Scores
- Order Tracking
- Notifications
- News Feed
- GPS Navigation
- Chat History

### 3. Client Streaming RPC

The client sends **multiple requests**, and the server returns **one response**.

```text
+--------+                    +--------+
| Client | ---- Request 1 --> | Server |
|        | ---- Request 2 --> |        |
|        | ---- Request 3 --> |        |
|        | <--- Response ---- |        |
+--------+                    +--------+
```

**Characteristics**

- Multiple client requests.
- One server response.
- The server processes all incoming messages before returning the result.

**Common Use Cases**

- File Upload
- Image Upload
- Video Upload
- Sensor Data Collection
- Batch Processing
- Mobile Device Logs

### 4. Bidirectional Streaming RPC

Both client and server exchange messages independently and simultaneously.

```text
+--------+                    +--------+
| Client | <---> <---> <--->  | Server |
+--------+                    +--------+
```

**Characteristics**

- Multiple client requests.
- Multiple server responses.
- Both sides communicate simultaneously.
- Ideal for real-time applications.

**Common Use Cases**

- Live Chat
- Video Calling
- Multiplayer Games
- Live Collaboration
- Financial Trading
- IoT Communication
- Live GPS Tracking

### Communication Pattern Comparison

| Pattern | Client Requests | Server Responses | Best Use Cases |
|----------|-----------------|------------------|----------------|
| Unary RPC | One | One | CRUD APIs |
| Server Streaming RPC | One | Many | Notifications, Live Updates |
| Client Streaming RPC | Many | One | File Upload, Batch Processing |
| Bidirectional Streaming RPC | Many | Many | Chat, Video Calls, Gaming |

---

# gRPC vs REST APIs

Both **gRPC** and **REST** are widely used for building distributed systems and microservices. REST is ideal for public APIs and browser-based applications, while gRPC is optimized for high-performance service-to-service communication.

### Feature Comparison

| Feature | REST API | gRPC |
|----------|----------|------|
| Communication Style | Resource-based | Remote Procedure Call (RPC) |
| Transport Protocol | HTTP/1.1 (commonly) | HTTP/2 |
| Data Format | JSON / XML | Protocol Buffers (Binary) |
| API Contract | Optional (OpenAPI/Swagger) | Required (`.proto`) |
| Payload Size | Larger | Smaller |
| Serialization | JSON/XML | Protocol Buffers |
| Performance | Good | Excellent |
| Network Latency | Higher | Lower |
| CPU Usage | Higher | Lower |
| Streaming | Limited | Built-in |
| Code Generation | Manual | Automatic |
| Browser Support | Excellent | Limited (gRPC-Web) |
| Multi-language Support | Yes | Yes |
| Best Use Case | Public APIs | Internal Microservices |

### Communication

#### REST

REST exposes multiple endpoints for different operations.

```text
GET    /employees
POST   /employees
PUT    /employees/{id}
DELETE /employees/{id}
```

Communication flow:

```text
Java Object
      │
JSON Serialization
      │
HTTP/1.1
      │
Network
      │
JSON Deserialization
      │
Java Object
```

#### gRPC

gRPC typically exposes a **single endpoint**, while the operation is determined by the **service** and **RPC method** defined in the `.proto` contract.

```text
localhost:9090
        │
EmployeeService
├── CreateEmployee()
├── GetEmployee()
├── GetAllEmployees()
├── UpdateEmployee()
├── DeleteEmployee()
├── SearchEmployees()
├── FindEmployeesByDepartment()
├── ExistsByEmployeeCode()
├── ExistsByEmail()
└── CountEmployees()
```

Communication flow:

```text
Java Object
      │
Protocol Buffers
(Binary Serialization)
      │
HTTP/2
      │
Network
      │
Protocol Buffers
(Binary Deserialization)
      │
Java Object
```

### Why gRPC Is Faster Than REST

Compared to REST, gRPC provides better performance because it uses:

- HTTP/2 instead of HTTP/1.1.
- Compact binary Protocol Buffers instead of JSON.
- Faster serialization and deserialization.
- Smaller request and response payloads.
- Lower CPU and memory usage.
- Persistent connections.
- Request multiplexing.
- Reduced network latency.
- Higher throughput.

### HTTP/1.1 vs HTTP/2

| HTTP/1.1 | HTTP/2 |
|-----------|---------|
| One request per connection (commonly) | Multiple requests over a single connection |
| Larger headers | Header compression |
| Higher latency | Lower latency |
| More network overhead | Better network utilization |
| Slower for high-volume communication | Optimized for high-performance communication |

### Status Code Comparison

| REST HTTP Status | gRPC Status | Description |
|------------------|-------------|-------------|
| 200 OK | OK (0) | Request completed successfully |
| 201 Created | OK (0) | Resource created successfully |
| 400 Bad Request | INVALID_ARGUMENT (3) | Invalid request |
| 401 Unauthorized | UNAUTHENTICATED (16) | Authentication required |
| 403 Forbidden | PERMISSION_DENIED (7) | Permission denied |
| 404 Not Found | NOT_FOUND (5) | Resource not found |
| 409 Conflict | ALREADY_EXISTS (6) | Resource already exists |
| 429 Too Many Requests | RESOURCE_EXHAUSTED (8) | Resource exhausted or rate limited |
| 500 Internal Server Error | INTERNAL (13) | Internal server error |
| 503 Service Unavailable | UNAVAILABLE (14) | Service temporarily unavailable |

### When to Choose REST

Choose REST when:

- Building public APIs.
- Browser compatibility is important.
- Third-party applications consume the API.
- Human-readable JSON is preferred.
- Simplicity is more important than performance.

### When to Choose gRPC

Choose gRPC when:

- Building microservices.
- Services communicate internally.
- High performance and low latency are required.
- Streaming communication is needed.
- Automatic client code generation is beneficial.

### Best Practice

Many enterprise applications use both technologies together.

```text
                 External Clients
                        │
                 REST APIs (HTTP/1.1)
                        │
                  API Gateway
                        │
      ------------------------------------
      │                │                 │
      ▼                ▼                 ▼
 User Service    Order Service    Payment Service
      │                │                 │
      └────────────────┼─────────────────┘
                       │
                 gRPC (HTTP/2)
                       │
      Internal Service-to-Service Communication
```

A common architecture is:

- REST APIs for web, mobile, and third-party clients.
- gRPC APIs for internal service-to-service communication.
- Shared business logic beneath both API layers.

---

# Running Project

**Prerequisites**

Make sure the following software is installed before running the project.

| Software | Version |
|----------|---------|
| Java | 25 |
| Maven | 3.9+ |
| PostgreSQL | Latest |
| Protocol Buffers | 33.0+ |
| Buf | Latest |
| IntelliJ IDEA | Recommended |
| Postman | Latest |

**Step 1: Clone the Repository**

```bash
git clone https://github.com/SirajChaudhary/grpc-ultrafast-microservice.git

cd grpc-ultrafast-microservice
cd employee-directory-service
```

**Step 2: Create Database**

Create a PostgreSQL database.

```sql
CREATE DATABASE employeedirectorydb;
```

Execute the SQL script.

```text
database/
└── employee-directory-db-and-sample-data.sql
```

This script will create:

- Employees table
- Database indexes
- Sample employee records

**Step 3: Install Protocol Buffers**

Install the Protocol Buffers compiler.

```bash
brew install protobuf
```

Verify the installation.

```bash
protoc --version
```


**Step 4: Install Buf**

Buf is a modern tool for managing Protocol Buffers.

Install Buf.

```bash
brew install buf
```

Verify the installation.

```bash
buf --version
```

**Step 5: Build the Project**

Build the project using Maven.

```bash
mvn clean install
```

👉 During the build process:

- `.proto` files are compiled.
- Java request classes are generated.
- Java response classes are generated.
- gRPC service classes are generated.
- Project is compiled successfully.

Generated source files are available under:

```text
target/generated-sources/protobuf
├── java
└── grpc-java
```

**Step 6: Configure IntelliJ IDEA**

👉 After the build completes:

- Right-click `target/generated-sources/protobuf`
- Select **Mark Directory As**
- Select **Generated Sources Root**

This enables IntelliJ IDEA to recognize the generated Java classes.

**Step 7: Run the Application**

Start the Spring Boot application.

```bash
mvn spring-boot:run
```

The application starts on:

```text
HTTP Server : 8080 (Spring Boot Actuator)

gRPC Server : 9090
```

**Step 8: Verify the Application**

If the application starts successfully, you should see logs similar to:

```text
Started EmployeeDirectoryApplication

Tomcat started on port 8080

gRPC Server started on port 9090
```

**Step 9: Test gRPC APIs Using Postman**

Open **Postman**.

1. Create a new **gRPC Request**.
2. Enter the server address.

```text
localhost:9090
```

3. Click **Connect**.
4. Import the Protocol Buffers contract.

```text
src/main/proto/employee.proto
```

5. Select the service.

```text
EmployeeService
```

6. Select an RPC method.
7. Click **Use Sample Message**.
8. Update the request values if required.
9. Click **Invoke**.

---

# Running gRPC APIs

After starting the application, import the `employee.proto` file into your preferred gRPC client (such as Postman) and invoke the following RPC methods.

### CRUD APIs

- **CreateEmployee:** Creates a new employee record.
  <img width="3074" height="1524" alt="image" src="https://github.com/user-attachments/assets/c09d398f-f811-4dbd-9067-1b1cde3269c1" />
- **GetEmployee:** Retrieves an employee by ID.
  <img width="3068" height="1258" alt="image" src="https://github.com/user-attachments/assets/2bd4737c-5faa-405b-9d16-449d0f2a3680" />
- **GetAllEmployees:** Returns all employees with pagination and sorting support.
  <img width="3068" height="1836" alt="image" src="https://github.com/user-attachments/assets/e52c4fbe-ec0a-4de2-adc5-0fad190c069d" />
- **UpdateEmployee:** Updates an existing employee.
  <img width="3072" height="1586" alt="image" src="https://github.com/user-attachments/assets/41582543-a4c3-4dee-890c-10ab013e8b30" />
- **DeleteEmployee:** Deletes an employee by ID.
  <img width="3070" height="884" alt="image" src="https://github.com/user-attachments/assets/4300dac0-ea8a-40c5-8088-1b21258c1811" />

### Search APIs

**SearchEmployees**

- Searches employees using:
    - Employee Code
    - First Name
    - Last Name
- Supports:
    - Partial keyword search
    - Case-insensitive search
    - Pagination
    - Sorting
- Supported sorting fields:
    - id
    - employeeCode
    - firstName
    - lastName
    - joiningDate
  <img width="3074" height="1556" alt="image" src="https://github.com/user-attachments/assets/18e7aeaf-6adf-4b4f-8415-0e86f89f0a3c" />

**FindEmployeesByDepartment**

- Returns employees belonging to a specific department.
- Supports:
    - Department filtering
    - Pagination
    - Sorting
  <img width="3070" height="1858" alt="image" src="https://github.com/user-attachments/assets/8ab6fa90-8465-40be-be7f-167bd8d67c0e" />

### Validation APIs

- **ExistsByEmployeeCode:** Checks whether an employee code already exists.
  <img width="3072" height="812" alt="image" src="https://github.com/user-attachments/assets/7b6bb303-963a-4e1c-9b1f-11b86547dba4" />

- **ExistsByEmail:** Checks whether an email address already exists.
  <img width="3072" height="838" alt="image" src="https://github.com/user-attachments/assets/a9932f55-8221-44af-b2e9-21d5e6367b73" />

### Utility APIs

- **CountEmployees:** Returns the total number of employees in the database.
  <img width="3070" height="842" alt="image" src="https://github.com/user-attachments/assets/46aaf30e-c81f-4308-a90c-5427dd7d0640" />

---

# Project Notes

### Can We Add REST APIs to This gRPC Microservice?

Yes. A Spring Boot application can expose both **REST APIs** and **gRPC APIs** within the same project.

To add REST support, simply create the following packages:

```text
src/main/java
│
├── controller          <-- REST Controllers (RESTful APIs)
├── request             <-- REST Request DTOs
├── response            <-- REST Response DTOs
│
├── grpc                <-- gRPC Services (gRPC APIs)
├── service             <-- Shared Business Logic
├── repository
├── entity
└── mapper
```

Both REST and gRPC reuse the same:

- Service layer
- Repository layer
- Entity classes
- Mapper
- Business logic
- Database

Only the API transport layer changes.

Architecture:

```text
                 REST Clients
                      │
              REST Controller
                      │
                      │
gRPC Clients ─────────► EmployeeGrpcService
                      │
               EmployeeService
                      │
             EmployeeRepository
                      │
                 PostgreSQL
```

### Common Annotations Used in This Project

**Spring gRPC**

| Annotation | Purpose |
|------------|---------|
| `@GrpcService` | Exposes a gRPC service implementation. |
| `@GrpcAdvice` | Handles exceptions globally for gRPC services. |
| `@GrpcExceptionHandler` | Maps Java exceptions to gRPC status codes. |

**Spring Framework**

| Annotation | Purpose |
|------------|---------|
| `@Service` | Marks a business service class. |
| `@Repository` | Marks the repository layer. |
| `@Transactional` | Executes methods within a database transaction. |

**JPA**

| Annotation | Purpose |
|------------|---------|
| `@Entity` | Represents a database table. |
| `@Table` | Maps an entity to a database table. |
| `@Id` | Defines the primary key. |
| `@GeneratedValue` | Generates primary key values automatically. |

**Bean Validation**

| Annotation | Purpose |
|------------|---------|
| `@NotBlank` | Validates that a field is not blank. |
| `@NotNull` | Validates that a field is not null. |
| `@Email` | Validates email format. |
| `@Size` | Validates string length. |

---

# Project Highlights

- Built using **Java 25**, **Spring Boot 4.1**, and **Spring gRPC**.
- Demonstrates enterprise-grade **gRPC microservice** development.
- Follows **Contract-First Development** using Protocol Buffers (`.proto`).
- Uses **Unary RPC** communication pattern.
- Implements complete **CRUD**, **Search**, **Validation**, and **Utility** APIs.
- Supports **pagination**, **sorting**, and **department-based filtering**.
- Uses **HTTP/2** and **Protocol Buffers** for high-performance communication.
- Automatically generates client and server code from the `.proto` contract.
- Implements a clean **layered architecture** with Spring Data JPA and PostgreSQL.
- Provides centralized exception handling using Spring gRPC.
- Demonstrates multi-language interoperability using a shared `.proto` contract.
- Can be extended to support **Server Streaming**, **Client Streaming**, and **Bidirectional Streaming** RPCs.
- REST APIs can be added without changing the existing business logic.
- Suitable for learning modern gRPC-based microservice development and service-to-service communication.

---

# Key Takeaways

- gRPC is an excellent choice for **high-performance service-to-service communication**.
- Protocol Buffers provide **compact binary serialization**, resulting in smaller payloads and faster processing than JSON.
- HTTP/2 improves communication through **multiplexing, header compression, and persistent connections**.
- A single `.proto` file defines the complete API contract and enables automatic client and server code generation.
- The same `.proto` contract can be shared across multiple programming languages, enabling seamless cross-platform communication.
- Contract-First Development helps build **strongly typed**, **consistent**, and **version-compatible** APIs.
- Unary RPC is the simplest and most commonly used communication pattern for CRUD-based microservices.
- Spring Boot allows **REST APIs** and **gRPC APIs** to coexist in the same application while sharing the same business logic and database.
- gRPC is well suited for **microservices**, **distributed systems**, **cloud-native applications**, and **real-time services** where performance and low latency are critical.

---

# License

Free software, [Siraj Chaudhary](https://www.linkedin.com/in/sirajchaudhary/)
