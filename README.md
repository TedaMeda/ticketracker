# Ticket Tracker

## Assumptions

## Features

* New user can signup
* User able to login using username and password
* Only authorized user(s) able to write article(s)
* User able to read comment(s)
* Only authorized user(s) able to write comment(s)

## APIs
| Method     | URL                                                    | Description                              |Security                                        |
| --------   | -------------------------------------------------------| -----------------------------------------| -----------------------------------------------|
| `POST`     | `/users`                                               | Create new user                          |`None`                                          |
| `POST`     | `/users/login`                                         | Login existing user                      |`None`                                          |
| `GET`      | `/articles`                                            | Get all articles                         |`None`                                          |
| `GET`      | `/articles/{article-slug}`                             | Get article by slug name                 |`None`                                          |
| `PATCH`    | `/articles/{article-slug}`                             | Update article by slug name              |`Authentication` `Personalization`              |
| `GET`      | `/articles/{article-slug}/comments`                    | Get article comments by slug name        |`pagination`                                    |
| `POST`     | `/articles/{article-slug}/comments`                    | Write comments on article                |`Authentication`                                |
| `DELETE`   | `/articles/{article-slug}/comments/{comment-id}`       |Delete #comment-id comments from article  |`Authentication` `Personalization`              |
