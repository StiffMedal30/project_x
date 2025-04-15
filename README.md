# Project X - Voice Idea Refinement App

**Project X** is a proof-of-concept application that allows users to record voice notes of business ideas, transcribe them using Google Speech-to-Text, and refine the text using an AI model (e.g., LLaMA). The refined ideas are stored for future retrieval via a web or mobile interface.

---

## 🚀 Features

- 🎧 Voice note upload via web and mobile apps
- 🔊 Audio transcription using Google Cloud Speech-to-Text
- 🧠 Idea refinement using an AI model (e.g., LLaMA)
- 📦 Persistent idea storage with PostgreSQL and Liquibase migrations
- 🔐 Secure access via JWT-based authentication
- 📱 Web interface (React) & mobile interface (React Native)

---
## 👤 Role-Based Access Control

| Permission               | OWNER ✅ | COLLABORATOR ✅ |
|--------------------------|:--------:|:----------------:|
| View Ideas               | ✅       | ✅               |
| Create / Edit Ideas      | ✅       | ✅               |
| Delete Ideas             | ✅       | ❌               |
| Invite Collaborators     | ✅       | ❌               |

---

## 🧱 Tech Stack

### Backend
- Java 17
- Spring Boot
- Spring Data JPA
- Spring Security (JWT-based auth)
- PostgreSQL
- Liquibase (for DB migrations)
- Google Cloud Speech-to-Text
- External AI microservice (LLaMA)

### Frontend
- Web: React + Material UI (CRA)
- Mobile: React Native (Native build)

### Dev Tools
- Gradle
- Docker (for local DB)
- IntelliJ IDEA / VSCode
- Postman (for API testing)

---

## 📂 Project Structure

```
project-x/
├── backend/
│   ├── src/main/java/...
│   ├── src/main/resources/
│   │   └── db/changelog/db.changelog-master.yaml
│   └── build.gradle
├── frontend/
│   ├── web/ (CRA + Material UI)
│   └── mobile/ (React Native)
```

---

## 🛠️ Getting Started

1. Set up Environment Variables for Production (not needed for dev)
   In production, sensitive data such as database credentials and the JWT secret key should never be hardcoded. Instead, use environment variables to store them securely.

  Linux/macOS
  You can set environment variables temporarily in your terminal or set them permanently in a config file (e.g., .bashrc, .zshrc, or a systemd service configuration).

  Temporarily in the terminal:

```bash
export DB_USERNAME=your_db_username
export DB_PASSWORD=your_db_password
export JWT_SECRET=your_jwt_secret_key
```
Permanently in .bashrc or .zshrc:

Add the following lines to the .bashrc (or .zshrc) file in your home directory:

```bash
export DB_USERNAME=your_db_username
export DB_PASSWORD=your_db_password
export JWT_SECRET=your_jwt_secret_key
```
Then, run ```source ~/.bashrc``` (or ```source ~/.zshrc```) to apply the changes.

Windows
You can set environment variables using PowerShell or through the system settings.

Using PowerShell:

```powershell
$env:DB_USERNAME="your_db_username"
$env:DB_PASSWORD="your_db_password"
$env:JWT_SECRET="your_jwt_secret_key"
```
Alternatively, set them in the system settings:

Right-click This PC > Properties.

Click Advanced system settings > Environment Variables.

Add new system variables with the appropriate names (DB_USERNAME, DB_PASSWORD, JWT_SECRET) and their corresponding values.

### 2. Clone the repository

```bash
git clone https://github.com/your-username/project-x.git
cd project-x
```

### 3. Start PostgreSQL (via Docker if used)

```bash
docker run --name projectx-postgres \
  -e POSTGRES_USER=your_user \
  -e POSTGRES_PASSWORD=your_password \
  -e POSTGRES_DB=voiceidea \
  -p 5432:5432 \
  -d postgres
```

### 4. Backend Setup

Update `application.yml` with your PostgreSQL credentials and GCP config:

```bash
cd backend
./gradlew bootRun
```

Liquibase will automatically apply database migrations.

### 5. Frontend Setup

#### Web (React)

```bash
cd frontend/web
npm install
npm start
```

#### Mobile (React Native)

```bash
cd frontend/mobile
npm install
npx react-native run-android # or run-ios
```

---

## 🔐 Authentication

- JWT-based authentication using Spring Security
- API endpoints under `/api/` are secured
- Auth Endpoints:
    - `POST /api/auth/register`
    - `POST /api/auth/login`

---

## 🔄 API Overview

- `POST /api/ideas/upload` - Upload voice note
- `GET /api/ideas` - Get all appUser ideas
- `GET /api/idea/{id}` - Get specific idea

---

## 📌 Environment Variables (example)

```env
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/voiceidea
SPRING_DATASOURCE_USERNAME=your_user
SPRING_DATASOURCE_PASSWORD=your_password
JWT_SECRET=your_super_secret_key
GCP_CREDENTIALS_PATH=/path/to/your/gcp/credentials.json
```

---

## 📈 Future Enhancements

- Real-time voice streaming and transcription
- Export refined ideas to PDF or Markdown
- AI feedback/improvement loop
- Multi-appUser collaboration
- Richer UI dashboards

---

## 🧖 Acronyms Used

| Acronym | Meaning                                   |
|---------|-------------------------------------------|
| JWT     | JSON Web Token                            |
| GCP     | Google Cloud Platform                     |
| JPA     | Java Persistence API                      |
| REST    | Representational State Transfer (API)     |
| DB      | Database                                  |
| API     | Application Programming Interface         |
| CRA     | Create React App                          |
| UI      | User Interface                            |

---

## 🧑‍💻 Author

**Project Lead**: @Christiaan Dotze  
_This project is in active development as a personal initiative alongside a full-time job._

---

## 📄 License

MIT License – free to use and modify.
