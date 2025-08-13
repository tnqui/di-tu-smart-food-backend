1. application.yml
```
spring:
  application:
    name: di-tu-smart-food-backend
  profiles:
    active: local

server:
  servlet:
    context-path: /api
```
2. application-local.yml
```
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/di_tu_smart_food_dev
    username: di_tu_smart_food_dev_user
    password: 123456
    driver-class-name: org.postgresql.Driver

  jpa:
    hibernate:
      ddl-auto: validate
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect
        format_sql: true
    open-in-view: false

  flyway:
    enabled: true
    locations: classpath:db/migration
    baseline-on-migrate: true
    validate-on-migrate: true
```
