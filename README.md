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

With referral:

{
  "name": "Aman",
  "email": "aman@gmail.com",
  "password": "aman123",
  "referralCode": "ABC12345"
}

CURL Example:

curl -X POST https://referral-signup-api-production.up.railway.app/api/signup \
  -H "Content-Type: application/json" \
  -d "{\"name\":\"Yash\",\"email\":\"yash123@gmail.com\",\"password\":\"yash123\"}"

🔹 2. Mark Profile as Completed
POST /api/complete-profile/{userId}

CURL Example:

curl -X POST https://referral-signup-api-production.up.railway.app/api/complete-profile/2

🔹 3. Get Referrals for a User
GET /api/referrals/{userId}

Returns users who used this user's referral code and completed their profile.

CURL Example:

curl https://referral-signup-api-production.up.railway.app/api/referrals/1

