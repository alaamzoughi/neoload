#
## _Neoload Transformer :_
## _REST API pour la transformation digitale des rapports de tes Test issues du  neoload_


[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master#)](https://travis-ci.org/joemccann/dillinger)

Ce Rest API sert à transformer un document word issu de l'outil de test de performance Neoload vers un document propriétaire à la société en respectant des règles de transformation bien définies et de stocker dans une base de données toutes les informations liées à la transformation


## Features
un compte admin qui pourra exécuter les tâches suivantes :
- ajouter un nouvel utilisateur
- supprimer un  utilisateur existant
- récupérer la liste des utilisateurs
- réinitialiser le mot de passe d'un utilisateur
- récupérer l'historique de génération des documents par  utilisateur , date ou client
- mettre à jour les règles de transformation .
- télécharger un document ( original ou transformé )

un utilisateur peut :
-transformer un document en indiquant différents paramètres ( nom du document , nom du client , logo du client , date de test ....)
- récupérer son historique .
- récupérer l'historique de génération des documents par date ou client .
- télécharger un document ( original ou transformé )



## Tech

Neoload Transformer uses a number of open source projects to work properly:

- [Java 11 ] - As the Programming language
- [PostgreSQL] - As the relational database management system
- [ApachePoi] - for manipulating DOCX file
- [Swagger] - used to share documentation among product managers, testers and developers


## Installation

Neoload Transformer requires JAVA 11 , PostgreSQL , Apache Poi to run.
Install the dependencies , Create the Database  and Start the server.

How to create The DataBase (The credentials can be modified in the Application.properties in The source Code)
Database name = jwt
Database username = jwtuser
Database password = jwtPass

```sh
sudo -u postgres psql
postgres=# create database jwt;
postgres=# create user jwtuser with encrypted password 'jwtPass';
postgres=# grant all privileges on database jwt to jwtuser;
```

How to add ApachePoi dependencies :
Add the Jar Files in the Folder Apache Poi Dependencies in the Project librairies using any IDEs for Java programming



## Testing the Application

Want to Test? Great!



First Tap :
Open Postman and execute
```sh
http://localhost:8080/login
```
Great : You have an access token
PS : The Localhost with Port 8080 can be replaced by The production URL
Second :
Tap in your Browser
```sh
http://localhost:8080/swagger-ui.html#/
```

Third:
Click Authorize and tap
Bearer [ACEESS_TOKEN]
Then click authorize Button

Congratulations , You have all your APIs now ready to execute

## Developped By
Alaa Mzoughi
## Overseen By
RedLean Services
## Contact 
mohamedalaa.mzoughi@eniso.u-sousse.tn

  