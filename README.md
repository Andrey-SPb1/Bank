# Банковский счёт

В проекте представлена серверная часть REST API приложения.

## Технологии
- Java 17
- Spring Boot 3.2.4
- Hibernate
- Gradle 8.5
- Postgresql
- Liquibase
- Lombok

## О проекте

Проект взаимодействует с базой данных при использованни **postgresql** и развернутой в
**docker** образе. База данных валидируется при запуске, используя **liquibase**, и добавляет
новые изменения при добавлении changelog.

Сам проект расслоен на Controller, Service и Repository.

## Тех задание:
1) [Должны быть написаны миграции для базы данных с помощью liquibase.](#задача-1)

2) [Обратите особое внимание проблемам при работе в конкурентной среде (1000 RPS по
одному кошельку). Ни один запрос не должен быть не обработан (50Х error)
Предусмотрите соблюдение формата ответа для заведомо неверных запросов, когда
кошелька не существует, не валидный json, или недостаточно средств.](#задача-2)

3) [Приложение должно запускаться в докер контейнере, база данных тоже, вся система
должна подниматься с помощью docker-compose.](#задача-3)

4) [Предусмотрите возможность настраивать различные параметры как на стороне
приложения так и базы данных без пересборки контейнеров.](#задача-4)

5) [Эндпоинты должны быть покрыты тестами.](#задача-5)

## Задача №1

Changelogs можно по директории: src/main/resources/db/changelog

## Задача №2

При возникающих проблемах сервер будет обрабатывать ответы с http статусом 4xx.
Например при несуществующем кошельке:
```java
walletRepository.getBalanceById(transfer.walletId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
```
Или когда недостаточно средтсв:
```java
if (newBalance < 0) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient funds");
```
При помощи зависимости **'org.springframework.boot:spring-boot-starter-validation'**, 
dto всегда имеют валидные данные:
```java
public record TransferDto(
@NotNull
Long walletId,
@Min(1) @Max(10_000_000)
Integer amount,
@NotNull
OperationType operationType) {
}
```
## Задача №3

БД и приложение запускаются через **docker-compose up**

Тесты запускаются в динамическом докер контейнере.

## Задача №4

Была добавлена возможность профилирования метода, 
которую можно включать и выключать во время работы программы.

```java
@Around("@annotation(Profiling)")
public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        if(flag)System.out.printf("%s выполнен за %d мс\n", joinPoint.getSignature(), executionTime);
        return proceed;
        }
```

Чтобы включить или выключить профилирование нужно отправить PUT-запрос 
**/api/v1/admin/true** или **/api/v1/admin/true** соотвественно.

Для того чтобы узнать статус - **/api/v1/admin/status**

## Задача №5

Все ключевые методы покрыты тестами.
