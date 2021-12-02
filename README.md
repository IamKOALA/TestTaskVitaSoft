# VitaSoft Test task
БД - postgreSQL

В репозитории прикреплены два файла:
* dbInit.sql - создает таблицы, использующиеся в работе приложения
* dbFill.sql - заполняет данными созданные таблицы 

## Описание

Система предоставляет из себя REST back-приложение.
Функции приложения описаны в тестовом задании.
Система построена на основе микросервисной архитектуры. Существуют два различных сервиса:
* UserService - отвечает за получение списка пользователей и предоставление им прав оператора.
* TaskService - отвечает за создание, редактирование и просмотр заявок.

У пользователей существуют следующие возможные роли:
* `ADMIN`
* `OPERATOR`
* `USER`

Возможные статусы заявки:
* `DRAFT` - черновик
* `SUBMITTED` - заявка отправлена на рассмотрение оператору
* `ACCEPTED` - заявка принята
* `DECLINED` - заявка отклонена

## API

* ##### UserService
    * `/users/`
        * Method `GET`
        * Возвращает список пользователей в системе
        * Доступен только пользователю с ролью `ADMIN`
    * `/users/{login}`
        * Method `POST`
        * Выдает пользователю с таким Логином роль оператора
            * `HTTP 404` если пользователь не найден
        * Доступен только пользователю с ролью `ADMIN`
* TaskService
    * `/tasks/submitted`
        * Method `GET`
        * Возвращает список заявок со статусом `SUBMITTED`
        * Доступен только пользователю с ролью `OPERATOR`
    * `/tasks/submitted/{id}`
        * Method `POST`
        * Параметры запроса:
            * Content-Type: text/plain
            * Тело запроса - строка с одним из значений статуса заявки
        * Устанавливает заявке с заданным ID статус из тела запроса.
            * `HTTP 403` если статус заявки не совпадает с `ACCEPTED` или `DECLINED`
        * Доступен только пользователю с ролью `OPERATOR`
    * `/tasks/my`
        * Method `GET`
        * Возвращает список заявок, созданный авторизованным пользователем
        * Доступен только пользователю с ролью `USER`
    * `/tasks/my/{id}`
        * Method `PUT`
        * Параметры запроса
            * Content-Type: text/plain
            * Тело запроса - строка
        * Изменяет сообщение заявки с заданным ID на переданное в запросе
            * `HTTP 404` если заявка с таким ID не найдена
        * Доступен только пользователю с правами `USER`
    * `/task/my/{id}`
        * Method `POST
        * Изменяет статус заявки с заданным ID на `SUBMITTED`
            * `HTTP 403` если текущий статус заявки не `DRAFT`
        * Доступен только пользователю с правами `USER`
    * `/task/new`
        * Method `POST`
        * Параметры запроса
            * Content-Type: text/plain
            * Тело запроса - строка
        * Создает новую заявку в статусе `DRAFT` с сообщением из тела запроса
        * Доступен только пользователю с правами `USER`
