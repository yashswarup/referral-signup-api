# Referral Signup API

This is a Spring Boot-based REST API that supports user signups, referral tracking, and profile completion. It uses an in-memory H2 database and provides endpoints to interact with user data.

## Features

- ✅ User Signup with unique referral code generation
- ✅ Referral tracking using referral codes
- ✅ Mark profile as completed
- ✅ View all users
- ✅ View successful referrals for a given user

## Technologies Used

- Java 17
- Spring Boot 3.1.5
- H2 In-Memory Database
- Maven
- Railway (Deployment)

## API Endpoints

### ➤ POST `/api/signup`
Signup a new user

**Request Body** (JSON):
```
{
  "name": "Yash",
  "email": "yash123@gmail.com",
  "password": "yash123",
  "referralCode": "OPTIONAL_REFERRAL_CODE"
}
```

### ➤ POST `/api/complete-profile/{id}`
Mark user profile as completed

**Example**: `/api/complete-profile/1`

### ➤ GET `/api/users`
Returns a list of all users.

### ➤ GET `/api/referrals/{id}`
Returns all users referred by the given user whose profiles are completed.

## Example curl Requests

```
# Signup a user without referral
curl.exe -X POST https://referral-signup-api-production.up.railway.app/api/signup -H "Content-Type: application/json" -d "{"name":"Yash","email":"yash123@gmail.com","password":"yash123"}"

# Signup a user with referral code
curl.exe -X POST https://referral-signup-api-production.up.railway.app/api/signup -H "Content-Type: application/json" -d "{"name":"Aman","email":"aman@gmail.com","password":"aman123", "referralCode":"1029D9CB"}"

# Complete profile
curl.exe -X POST https://referral-signup-api-production.up.railway.app/api/complete-profile/1

# Get all users
curl.exe https://referral-signup-api-production.up.railway.app/api/users

# Get referrals of user 1
curl.exe https://referral-signup-api-production.up.railway.app/api/referrals/1
```

## Deployment Notes

- ✅ Deployed on [Railway](https://railway.app)
- ✅ Public URL: `https://referral-signup-api-production.up.railway.app`
- ✅ Database: In-memory H2
- ✅ GitHub Repo: https://github.com/yashswarup

## Running Locally

1. Clone the repo
2. Run: `mvn spring-boot:run`
3. Access via `http://localhost:8080`

## Author

Yash Swarup
