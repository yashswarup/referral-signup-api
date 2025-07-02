# 🧾 Referral Signup API – Spring Boot Project

This project is a **Java Spring Boot API** that implements a **referral-based user signup system**. Users can sign up with or without a referral code. Referral relationships are tracked, and users can later mark their profile as complete.

---

## 🚀 Live Deployment

🌐 **Base URL**:  
[https://referral-signup-api-production.up.railway.app](https://referral-signup-api-production.up.railway.app)

---

## 🧩 Features

✅ User Signup with or without Referral  
✅ Auto-generated Referral Code for each user  
✅ Referral Tracking API  
✅ Profile Completion API  
✅ SQL Database (H2 locally, Railway in prod)  
✅ Deployed on [Railway](https://railway.app)

---

## 🛠️ Tech Stack

- **Language**: Java (JDK 17)
- **Framework**: Spring Boot 3.x
- **Database**: H2 (local), PostgreSQL (Railway)
- **Build Tool**: Maven
- **Deployment**: Railway Cloud
- **API Testing**: Postman / CURL

---

## 📦 API Endpoints

### 🔹 1. Signup (with or without referral)

**POST** `/api/signup`

**Request Body (JSON):**

```json
{
  "name": "Yash",
  "email": "yash123@gmail.com",
  "password": "yash123"
}

