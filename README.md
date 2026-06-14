1. application.yml
```
spring:
  profiles:
    active: dev
```
2. application-dev.yml
```
spring:
  application:
    name: di-tu-smart-food-backend

  mail:
    host: smtp.gmail.com
    port: 
    username: 
    password: 
    properties:
      mail:
        smtp:
          auth: true
          starttls:
            enable: true
          ssl:
            trust: smtp.gmail.com

  datasource:
    url: jdbc:postgresql://localhost:5432/...
    username: ...
    password: ...
    driver-class-name: org.postgresql.Driver

  jpa:
    hibernate:
      ddl-auto: create-drop #validate for production
    show-sql: true
    properties:
      hibernate:
        format_sql: true
    open-in-view: false

  security:
    jwt:
      issuer: "Di4food.com"
      secret: ""
      expiration: 3600000
      refresh-expiration: 1209600000

    oauth2:
      client:
        registration:
          google:
            client-id:
            client-secret:
            scope:
            redirect-uri:
        provider:
          google:
            authorization-uri: 
            token-uri:
            user-info-uri: 
            user-name-attribute: 

  flyway:
    enabled: false #true for production
    locations: classpath:db/migration
    baseline-on-migrate: true
    validate-on-migrate: true

  cloudflare:
    r2:
      end-point:
      access-key:
      secret-key: 
      bucket:

mapbox:
  token: 

app:
  name: DiTuSmartFood
  totp:
    algorithm: SHA1
    digits: 6
    period: 30
  debug: true

momo:
  partner-code: 
  access-key: 
  secret-key: 
  public-key: 
  endpoint:




```
