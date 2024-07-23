# Ticketing_Service

## Purpose
다량의 요청이 순식간에 인입 됐을 때, 유저의 티켓팅 진입 자체를 대기열을 통해 관리하여 장애를 회피

## dependencies

```ruby
implementation "org.springframework.boot:spring-boot-starter-data-redis-reactive"
```


## application.yml

```ruby
spring:
  application:
    name: QueueService
  redis:
    host: localhost
    port: 6379
```

## API
- POST /queue/enter?userId={userId}
- POST /queue/exit?userId={userId}
- GET  /queue/position?userId={userId}
- GET  /queue/size
  
