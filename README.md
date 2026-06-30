# Dì Tư SmartFood Backend

## 📋 Mô tả

API backend for restaurant management system.

## 🚀 Quick Start

### Prerequisites

Before running this project, ensure you have installed:

- Git
- Docker (Docker Desktop on Windows/macOS or Docker Engine on Linux)
- An IDE (IntelliJ IDEA recommended)

You also need:
- AWS S3 Bucket
- AWS SES (for email sending)
- Environment variables configured in `.env` or `application.yml`
### Installation

```bash
git clone https://github.com/tnqui/di-tu-smart-food-backend.git
cd project
mvn clean install
mvn spring-boot:run
```

### Configuration
```application.yml
spring:
    profiles:
        active: example
```
```application-example.yml
spring:
  application:
    name: di-tu-smart-food-backend

  api-domain: "http://localhost:8080/api"

  data:
    web:
      pageable:
        default-page-size: 20
        max-page-size: 100
    redis:
      host: localhost
      port: 6379
      timeout: 60000ms
      password: ""  # Set password if Redis requires auth

  datasource:
    url: jdbc:postgresql://localhost:5432/di_tu_smart_food
    username: postgres
    password: root_password_123
    driver-class-name: org.postgresql.Driver
    hikari:
      maximum-pool-size: 10
      minimum-idle: 5
      connection-timeout: 20000

  jpa:
    database-platform: org.hibernate.dialect.PostgreSQLDialect
    hibernate:
      ddl-auto: validate  # update for dev, validate for prod
    show-sql: false  # Set to false in production
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect
        format_sql: true
        jdbc:
          batch_size: 20
          fetch_size: 50

  security:
    jwt:
      issuer: "di-tu-smart-food.example.com"
      secret: "your-super-secret-jwt-key-min-256-bits-long-abcdefghijklmnopqrstuvwxyz123456"
      access-token-expiration: 3600000      # 1 hour in milliseconds
      refresh-token-expiration: 1209600000  # 14 days in milliseconds
      algorithm: HS512

  flyway:
    enabled: true
    locations: classpath:db/migration
    baseline-on-migrate: true
    validate-on-migrate: true
    baseline-version: 1

  cloudflare:
    r2:
      end-point: "https://youraccount.r2.cloudflarestorage.com"
      access-key: "your_cloudflare_r2_access_key_here"
      secret-key: "your_cloudflare_r2_secret_key_here"
      bucket: "di-tu-smart-food-bucket"

app:
  name: DiTuSmartFood
  totp:
    algorithm: SHA1
    digits: 6
    period: 30
  debug: true  # Set to false in production
  version: 1.0.0
  base-url: "http://localhost:8080"

momo:
  partner-code: "MOMO5RGX20210920"  # Get from MoMo dashboard
  access-key: "rwZatf8F73RD9X5e"
  secret-key: "khofJU6DiZg8Ph6vVSHhWkqZ4y7JXV21"
  callback-url: "http://localhost:8080/api/v1/payments/momo/callback"
  redirect-url: "http://localhost:3000/payment/success"
  request-type: "captureWallet"
  public-key: "MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEA..."  # Your MoMo public key
  endpoint: "https://test-payment.momo.vn/v2/gateway/api/create"

aws:
  active: true
  secret-key: "wJalrXUtnFEMI/K7MDENG+bPxRfiCYzSAKEY+SECRETKEY"
  access-key: "AKIAIOSFODNN7EXAMPLE"
  region: "ap-southeast-2"  # ap-southeast-1 for Vietnam
  s3:
    bucket: "di-tu-smart-food-bucket"
    prefix: "uploads/"

mail:
  sender: "noreply@ditusmartfood.com"
  smtp:
    host: "smtp.gmail.com"
    port: 587
    username: "your-email@gmail.com"
    password: "your-app-specific-password"  # Use App Password for Gmail
    auth: true
    starttls:
      enable: true
      required: true

viet-map:
  api-key: "your_vietmap_api_key_here"
  tilemap-key: "your_vietmap_tilemap_key_here"
  console-application: "https://maps.vietmap.vn/console-v2"
  apiUrl: "https://maps.vietmap.vn/api/v1"

restaurant:
  lat: 10.762622  # Ho Chi Minh City
  lng: 106.660172
  name: "DITU Smart Food Restaurant"
  address: "123 Nguyen Hue, District 1, HCMC"


server:
  port: 8080
  servlet:
    context-path: /
  compression:
    enabled: true
    min-response-size: 1024
```


## 📚 Documentation

- [API Docs](docs/API.md)
- [Setup Guide](docs/SETUP.md)
- [Architecture](docs/ARCHITECTURE.md)

## 🔗 API Base URL

\`http://localhost:8080/api/v1\`