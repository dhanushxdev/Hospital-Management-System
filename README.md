# Hospital Management System (HMS)

A complete backend REST API project built using Java, Spring Boot, Hibernate/JPA, MySQL, and Swagger/OpenAPI for managing hospitals, branches, patients, encounters, medicine orders, and medicine items.

---

# 🚀 Tech Stack

- Java 8+
- Spring Boot
- Spring Data JPA (Hibernate)
- MySQL
- Maven
- Lombok
- Swagger / OpenAPI
- Bean Validation

---

# 📌 Features

## Hospital Module

- Save Hospital
- Update Hospital
- Delete Hospital
- Get Hospital By ID
- Get Hospital By Email
- Pagination & Sorting Support

## Branch Module

- Add Branch to Hospital
- Update Branch
- Delete Branch
- Get Branch By ID
- Get Branches By Hospital ID

## Address Module

- Save Address
- Update Address
- Delete Address
- Get Address By ID
- Get All Addresses

## Person Module

- Save Person
- Update Person
- Delete Person
- Get Person By ID
- Get All Persons with Pagination & Sorting

## Encounter Module

- Create Encounter
- Update Encounter
- Delete Encounter
- Get Encounter By ID

## MedOrder Module

- Create Medicine Order
- Update Medicine Order
- Delete Medicine Order
- Get Medicine Order By ID

## MedItems Module

- Add Medicine Items
- Update Medicine Items
- Delete Medicine Items
- Get Medicine Items By ID

---

# 🛠 Project Architecture

The project follows layered architecture:

```text
Controller Layer
       ↓
Service Layer
       ↓
DAO Layer
       ↓
Repository Layer
       ↓
Database
```

---

# 📂 Package Structure

```text
com.ty.HospitalManagementSystem
│
├── controller
├── service
├── dao
├── repo
├── Entity
├── exception
├── util
```

---

# 🗄 Database Relationships

## Hospital ↔ Branch

- One Hospital can have many Branches
- `@ManyToOne`

## Branch ↔ Address

- One Branch has one Address
- `@OneToOne`

## Person ↔ Address

- One Person has one Address
- `@OneToOne`

## Person ↔ Encounter

- One Person can have many Encounters
- `@ManyToOne`

## Branch ↔ Encounter

- One Branch can have many Encounters
- `@ManyToOne`

## Encounter ↔ MedOrder

- One Encounter can have many MedOrders
- `@ManyToOne`

## MedOrder ↔ MedItems

- One MedOrder can contain multiple Medicine Items
- `@ManyToOne`

---

# 🔥 API Endpoints

## Hospital APIs

| Method | Endpoint | Description |
|---|---|---|
| POST | `/hospital/save` | Save hospital |
| PUT | `/hospital/update?id=1` | Update hospital |
| DELETE | `/hospital/delete?id=1` | Delete hospital |
| GET | `/hospital/getById?id=1` | Get hospital by ID |
| GET | `/hospital/getByEmail?email=test@gmail.com` | Get hospital by email |
| GET | `/hospital/getAllHospital?page=0&size=5&direction=asc` | Get all hospitals |

---

## Address APIs

| Method | Endpoint |
|---|---|
| POST | `/api/addresses` |
| PUT | `/api/addresses/{id}` |
| DELETE | `/api/addresses/{id}` |
| GET | `/api/addresses/{id}` |
| GET | `/api/addresses/getAllPersons` |

---

## Branch APIs

| Method | Endpoint |
|---|---|
| POST | `/api/branches?hid=1&aid=1` |
| PUT | `/api/branches/{id}` |
| DELETE | `/api/branches/{id}` |
| GET | `/api/branches/{id}` |
| GET | `/api/branches/hospital/{hid}` |

---

## Person APIs

| Method | Endpoint |
|---|---|
| POST | `/api/persons` |
| PUT | `/api/persons/{id}` |
| DELETE | `/api/persons/{id}` |
| GET | `/api/persons/{id}` |
| GET | `/api/persons?page=0&size=5&direction=asc` |

---

## Encounter APIs

| Method | Endpoint |
|---|---|
| POST | `/api/encounters?pid=1&bid=1` |
| PUT | `/api/encounters/{id}?bid=1` |
| DELETE | `/api/encounters/{id}` |
| GET | `/api/encounters/{id}` |

---

## MedOrder APIs

| Method | Endpoint |
|---|---|
| POST | `/api/medorders?eid=1` |
| PUT | `/api/medorders/{id}` |
| DELETE | `/api/medorders/{id}` |
| GET | `/api/medorders/{id}` |

---

## MedItems APIs

| Method | Endpoint |
|---|---|
| POST | `/api/meditems?mid=1` |
| PUT | `/api/meditems/{id}` |
| DELETE | `/api/meditems/{id}` |
| GET | `/api/meditems/{id}` |

---

# 📘 Swagger Documentation

Swagger UI is integrated for API testing and documentation.

## Access Swagger UI

```text
http://localhost:8080/swagger-ui/index.html
```

---

# ⚙️ Validation Used

The project uses Bean Validation annotations such as:

```java
@NotBlank
@NotNull
@Email
@Min
@Max
```

## Example

```java
@NotBlank(message = "hospital name is required")
private String name;
```

---

# ❌ Exception Handling

Global Exception Handling is implemented using:

```java
@RestControllerAdvice
```

## Handled Exceptions

- IdNotFoundException
- NoSuchElementException
- MethodArgumentNotValidException
- ConstraintViolationException

---

# 📄 Sample JSON Requests

## Save Hospital

```json
{
  "name": "Apollo Hospital",
  "email": "apollo@gmail.com"
}
```

---

## Save Address

```json
{
  "state": "Karnataka",
  "city": "Bangalore",
  "pincode": 560037
}
```

---

## Save Person

```json
{
  "name": "Rohit",
  "email": "rohit@gmail.com",
  "phone": 9876543210,
  "address": {
    "id": 1
  }
}
```

---

## Save Branch

```json
{
  "name": "Whitefield Branch",
  "phone": 9876543210,
  "manager": "David"
}
```

---

## Save Encounter

```json
{
  "reason": "Fever",
  "cost": 2500
}
```

---

## Save MedOrder

```json
{
  "date": "2026-05-12",
  "doctor": "Dr. Smith"
}
```

---

## Save MedItems

```json
{
  "name": "Paracetamol",
  "cost": 120
}
```

---

# 🧪 How To Run The Project

## 1️⃣ Clone Repository

```bash
git clone <your-github-repo-url>
```

---

## 2️⃣ Open Project

Open project in:

- IntelliJ IDEA
- Eclipse
- Spring Tool Suite (STS)

---

## 3️⃣ Configure Database

Update `application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/hospital_db
spring.datasource.username=root
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## 4️⃣ Run Application

Run:

```text
HospitalManagementSystemApplication.java
```

---

# 📌 Future Improvements

- Spring Security with JWT
- Role Based Authentication
- DTO Layer
- Logging
- Docker Deployment
- Cloud Deployment
- Appointment Module
- Billing Module

---

# 👨‍💻 Author

Developed by Dhanush Kumar
