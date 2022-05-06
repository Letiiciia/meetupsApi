<h1 align="center">🚀 Meetups Scheduler 🚀</h1>
<p align="center">  In this project we have a meetup scheduler using API Spring boot, Java, git and swagger.
</p>

## :heavy_check_mark: Table of contents

<!--ts-->
- [Table of contents ](#table-of-contents)
- [Features](#Features)
- [Installation](#installation)
- [Usage](#usage)
- [Tests](#tests)
<!--te-->


## :heavy_check_mark: Features

- [x] SWAGGER with endpoints access
- [x] Registration of user in determined meetup
- [x] Creation of Meetup
- [x] Listing of all Meetups
- [x] Listing of all Registrations
- [x] Update to Meetup
- [x] Update to Registrations
- [x] Deletion to Meetup
- [x] Deletion to Registration

## :heavy_check_mark: Installation

### 🛠 Technologies and Dependencies
* [Git](#Git)
* [Java11](#Java11)
* [Gradle](#Gradle)
* [Springboot2.5.13](#Springboot2.5.13)
* [JPA](#JPA)
* [Lombok](#Lombok)
* [BancoH2](#BancoH2)
* [junit](#junit)

### :arrow_forward: How to use this repository
```bash
$ git clone https://github.com/Letiiciia/meetupsApi-WomakersCode
```
- Open the project in the Editor
- After that you can RUN


## :arrow_forward: Usage

- Run project
- Start swagger: http://localhost:8080/swagger-ui.html#

## :heavy_check_mark: Tests
### :heavy_check_mark: Add new Registration
Input:
POST:"/api/registration"
```bash
{
	"name":"Laura Lima",
	"dateOfRegistration":"2022-05-03",
	"nickName": "lala24",
	"meetupId": 1
}
```
### :heavy_check_mark: Add new Meetup
Input:
POST:"/api/meetup"
```bash
{
    "meetupName": "Bootcamp Back End Java",
    "meetupDate": "2022-04-29",
    "registrated": "true"
}
```

### :heavy_check_mark: Fetch all Registrations
Input:
GET:"/api/registration"
 ```bash
[
    {
        "id": 1,
        "name": "Laura Lima",
        "dateOfRegistration": "2022-05-03",
        "nickName": "Lala24",
        "meetup": {
            "id": 1,
            "meetupName": "Bate Papo",
            "meetupDate": "2022-04-29",
            "registrated": true
        }
    },
    {
        "id": 2,
        "name": "Letícia Lima",
        "dateOfRegistration": "2022-05-03",
        "nickName": "lefifes",
        "meetup": {
            "id": 2,
            "meetupName": "Bootcamp Back End Java",
            "meetupDate": "2022-04-29",
            "registrated": true
        }
    }
]

```


### :heavy_check_mark: Fetch all Meetup
GET:"/api/meetup"
```bash
[
    {
        "id": 1,
        "meetupName": "Bate Papo",
        "meetupDate": "2022-04-29",
        "registrated": true,
        "registration": [
            {
                "id": 1,
                "name": "Laura Lima",
                "dateOfRegistration": "2022-04-03",
                "nickName": "Lala24"
            }   
        ]
    },
    {
        "id": 2,
        "meetupName": "Bootcamp Back End Java",
        "meetupDate": "2022-04-29",
        "registrated": true
        "registration": [
            {
                "id": 2,
                "name": "Letícia Lima",
                "dateOfRegistration": "2022-04-03",
                "nickName": "lefifes",
            }
        ]
    }
]
```

### :heavy_check_mark: Update a Registration
Put:"/api/registration/1"
```bash
 {
        "id": 1,
        "name": "Laura Lima",
        "dateOfRegistration": "2022-05-03",
        "nickName": "Lala24",
        "meetup": {
            "id": 1,
            "meetupName": "Bate Papo",
            "meetupDate": "2022-04-29",
            "registrated": true
}
```
### :heavy_check_mark: Update a Meetup
Put:"/api/meetup/1"
```bash
{
        "id": 1,
        "meetupName": "Bate Papo",
        "meetupDate": "2022-04-29",
        "registrated": true,
        "registration": [
            {
                "id": 1,
                "name": "Laura Lima",
                "dateOfRegistration": "2022-04-03",
                "nickName": "Lala24"
            }   
        ]
}
```
### :heavy_check_mark: Delete a Registration
DELETE:"/api/registration/1"
```bash
status 204
```
### :heavy_check_mark: Delete a Meetup
DELETE:"/api/meetup/1"
```bash
status 204
```

