# Ticket Tracker

## Assumptions

## Features

* New user can signup
* User able to login using username and password
* Only authorized(role based authentication) user(s) able to update other user(s) role
* Only authorized user(s) able to create ticket(s)

## Tools and Technologies

* Spring boot
* Spring security
* Spring data JPA
* Gradle
* MySQL DB
* AWS RDS for prod
* JWT
* Lombok
* Model mapper

## APIs
| Method     | URL                                                    | Description                              |Security                                        |
| --------   | -------------------------------------------------------| -----------------------------------------| -----------------------------------------------|
| `POST`     | `/users/register`                                      | Register new user                        |`None`                                          |
| `POST`     | `/users/login`                                         | Login existing user                      |`None`                                          |
| `PUT`      | `/users/update-role`                                   | Update user's role                       |`Authentication`                                |
| `PUT`      | `/users/update-user`                                   | Update user's details                    |`Authentication` `Personalization`              |
| `PUT`      | `/users/update-password`                               | Update user's password                   |`Authentication` `Personalization`              |
| `POST`     | `/departments`                                         | Create new department                    |`Authentication`                                |
| `GET`      | `/departments`                                         | Get all departments                      |`Authentication`                                |
| `DELETE`   | `/departments/{department-name}`                       | Delete department                        |`Authentication`                                |
| `POST`     | `/tickets`                                             | Create new ticket                        |`Authentication`                                |
| `GET`      | `/tickets`                                             | Get all tickets                          |`Authentication` `Pagination` `Filter`          |
| `GET`      | `/tickets/my-ticket`                                   | Get all tickets created by user          |`Authentication` `Personalization`              |
| `PUT`      | `/tickets/{ticket-id}/assign-ticket`                   | Assign ticket to user                    |`Authentication`                                |
| `PUT`      | `/tickets/{ticket-id}/update-status`                   | Update ticket status                     |`Authentication`                                |
