# Arquitectura de un mini API-Rest simulado
> Este proyecto pretende ser un ejemplo sencillo de arquitectura de un API-Rest simulado para comprender las capas que intervienen y la organización de los diferentes tipos de test, con integración continua y control de la calidad del código
> #### [Máster en Ingeniería Web por la Universidad Politécnica de Madrid (miw-upm)](http://miw.etsisi.upm.es)
> #### Asignatura: *Arquitectura y Patrones para Aplicaciones Web*

[![Build Status](https://travis-ci.org/iraticasi/APAW-ECP2-iraticasi.svg?branch=develop)](https://travis-ci.org/iraticasi/APAW-ECP2-iraticasi)

## Diseño de entidades
![entities-class-diagram](/docs/entities-class-diagram.png)

## API
### POST /users
#### Parámetros del cuerpo
- `email`: String (**requerido**)
#### Respuesta
- 200 OK 
  - `id`: String
- 403 BAD_REQUEST
---
### POST /playlists
#### Parámetros del cuerpo
- `name`: String (**requerido**)
- `user-id`: String (**requerido**)
#### Respuesta
- 200 OK 
  - `id`: String
- 403 BAD_REQUEST
- 404 NOT_FOUND
---
### POST /songs
#### Parámetros del cuerpo
- `name`: String (**requerido**)
- `singer`: String 
- `seconds`: Integer
- `date`: String
- `genre`: String
#### Respuesta
- 200 OK 
  - `id`: String
- 403 BAD_REQUEST
---
### POST /podcasts
#### Parámetros del cuerpo
- `name`: String (**requerido**)
- `description`: String 
#### Respuesta
- 200 OK 
  - `id`: String
- 403 BAD_REQUEST
---
### GET /podcasts
#### Respuesta
- 200 OK 
  - `[ {id:String,name:String,description:String} ]`
---
### PUT /users/{id}
#### Parámetros del cuerpo
- `email`: String (**requerido**)
#### Respuesta
- 200 OK 
- 403 BAD_REQUEST
- 404 NOT_FOUND
--- 
### DELETE /playlists/{id}
#### Respuesta
- 200 OK 
---
### PATH /playlists/{id}/songs
#### Parámetros del cuerpo
- `song-id`: String (**requerido**)
#### Respuesta
- 200 OK 
- 403 BAD_REQUEST
- 404 NOT_FOUND
---
### GET /playlists/search?user={id}
#### Respuesta
- 200 OK 
  - `[ {id:String,name:String,collaborative:Boolean,secret:Boolean} ]`
---

##### Autora: Irati Casi
