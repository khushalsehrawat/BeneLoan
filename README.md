# BeneLoan – Java Back-End Project  

## 📌 Overview  
This project was developed as part of the **technical assessment** for the **Java Back-End Developer role at BankBenchers**.  

It implements a **secure and robust backend system** for managing **Beneficiaries and Loans**, with support for:  
- 🔑 **User Authentication** using JWT tokens  
- 📂 **CSV Upload & Validation** for Beneficiaries and Loans  
- 🗄 **Data Persistence** with Spring Data JPA & MySQL  
- ✅ **Validation Rules** as defined in the assessment requirements  

The solution is kept **simple, professional, and extensible** for real-world applications.  

---

## ⚙️ Tech Stack  
- **Java 21**  
- **Spring Boot **  
- **Spring Security (JWT Authentication)**  
- **Spring Data JPA**  
- **MySQL**  
- **Apache Commons CSV** (CSV parsing & validation)  

---

## 🚀 Features  

### 🔐 User Authentication  
- `POST /auth/register` → Register a new user  
- `POST /auth/login` → Authenticate user & get **JWT token**  

### 👤 Beneficiary Management  
- `POST /upload/beneficiaries` → Upload **CSV of beneficiaries**  
- **Validations:**  
  - Reject duplicate `beneficiary_id`  
  - Reject missing `name`  
  - Validate `caste` → (GEN, SC, ST, OBC, OTHER)  

### 💰 Loan Management  
- `POST /upload/loans` → Upload **CSV of loans**  
- **Validations:**  
  - Verify `beneficiary_id` exists  
  - Validate **IFSC code format**  
  - Ensure `sanction_amount ≤ loan_amount`  

---

## 🛠️ Setup & Run  

### 1️⃣ Clone / Extract Project  
```bash
Setup & Run

Clone / Extract Project

Extract the zip and open it in IntelliJ IDEA / Eclipse / VS Code.

Database Setup

Install & run MySQL

```

Open in **IntelliJ IDEA / Eclipse / VS Code**.  

---

### 2️⃣ Database Setup  
Install & run **MySQL** and create a schema:  
```sql
CREATE DATABASE beneloan;
```

Update credentials in **`src/main/resources/application.properties`**:  
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/beneloan
spring.datasource.username=root
spring.datasource.password=1234
```

---

### 3️⃣ Run the Application  
```bash
mvn spring-boot:run
```
The backend will be available at 👉 **http://localhost:8080**  

---

## 📌 Usage  

### 🔑 Authentication  

**Register**  
```http
POST /auth/register
```
```json
{
  "username": "example",
  "password": "password123"
}
```

**Login**  
```http
POST /auth/login
```
```json
{
  "username": "example",
  "password": "password123"
}
```
✔️ Response → **JWT Token** (use in `Authorization: Bearer <token>` header)  

---

### 📂 CSV Upload  

**Beneficiaries**  
```http
POST /upload/beneficiaries
(multipart/form-data with file)
```
📄 Example CSV – `beneficiaries.csv`  
```csv
name,beneficiary_id,caste
Rahul,BEN101,GEN
Meera,BEN102,SC
Kabir,BEN103,ST
```

**Loans**  
```http
POST /upload/loans
(multipart/form-data with file)
```
📄 Example CSV – `loans.csv`  
```csv
application_id,beneficiary_id,bank_name,bank_type,account_no,ifsc_code,loan_amount,sanction_amount
APP001,BEN101,HDFC,Savings,12345HDFC001,HDFC0001234,50000,45000
APP002,BEN102,SBI,Current,12345SBI002,SBIN0005678,70000,60000
```

---

## 📝 Notes  
- ✅ The project **strictly follows the assignment requirements**  
- ✅ **Clear validation rules** ensure data integrity  
- ✅ Code is **simple, structured, and interview-ready**  

---

## 👨‍💻 Author  
**Khushal Sehrawat**  
📧 [khushalsehrawat28@gmail.com](mailto:khushalsehrawat28@gmail.com)  
📱 +91 9899788725  
🔗 [LinkedIn](https://www.linkedin.com/in/khushalsehrawat)  

🌐 Visit my web development studio — [TheVB24.com](https://www.thevb24.com)



## Admin console metadata

The root `index.html` is a static HTML/Bootstrap admin console for the Spring Boot API. It includes a description, Khushal Sehrawat as author, TheBenzzCreations as creator, Open Graph metadata and Twitter card metadata. The existing title, branding and application code are preserved.

The confirmed public URL, `https://www.thebenzzcreations.com/`, is used for the canonical link, `og:url` and the WebApplication JSON-LD URL. The structured data identifies the application's author and creator without adding lender, financial product or review claims. No dedicated social preview image is configured. Structured data does not guarantee search rich results.

One visible developer credit appears below the console in a footer. Its promotional link uses `rel="noopener noreferrer nofollow"`. The existing author information and TheVB24 studio link above are retained.

The console has no package manifest, configured frontend lint suite or frontend build command. Backend compilation targets Java 21 and backend-dependent features require the configured API and database.

## Development

Developed by [TheBenzzCreations](https://www.thebenzzcreations.com/).

TheBenzzCreations builds custom websites, mobile applications, business software, e-commerce platforms and AI automation solutions.

For development enquiries, visit our official website.
