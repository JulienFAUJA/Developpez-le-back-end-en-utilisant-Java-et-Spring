# :star: Chatop :star:

<div align="center">

[![forthebadge](https://forthebadge.com/images/badges/built-with-love.svg)](https://forthebadge.com) [![forthebadge](https://forthebadge.com/images/badges/powered-by-coffee.svg)](https://forthebadge.com)
[![forthebadge](https://forthebadge.com/images/badges/uses-git.svg)](https://forthebadge.com) [![forthebadge](https://forthebadge.com/images/badges/made-with-java.svg)](https://forthebadge.com) [![forthebadge](https://forthebadge.com/images/badges/build-with-spring.svg)](https://forthebadge.com) [![forthebadge](https://forthebadge.com/images/badges/build-with-spring-boot.svg)](https://forthebadge.com)

</div>
<hr/>

## Introduction

Cette application permet de gérer les biens locatifs d'une agence immobilière. Le focus y est mis sur la partie Back-end qui est développée en **[Java](#java)** avec le **[framework](#framework)** **[Spring](#spring)** et spécifiquement **[Spring Security](#spring_security)**. Quant à la partie Front-end, elle est développée en **[Angular](#angular)**, qui est un framework **[Typescript](#typescript)**, qui lui-même est un langage basé sur **[Javascript](#javascript)**.

Ce projet a été généré avec [Angular CLI](https://github.com/angular/angular-cli) version 14.1.0. ![Angular](https://img.shields.io/badge/angular_CLI-v14.1.0-blue)

## Démarrez le projet

### Clonez le projet git:

> git clone https://github.com/JulienFAUJA/Developpez-le-back-end-en-utilisant-Java-et-Spring

Allez dans le dossier:

> cd Developpez-le-back-end-en-utilisant-Java-et-Spring

### Installez les dependances:

> npm install

### Lancez le Front-end:

> ng serve

Exécutez `ng serve` pour démarrer le serveur de développement. Accédez à `http://localhost:4200/`. L'application se rechargera automatiquement si vous modifiez l'un des fichiers source.

### Lancez le Back-end:

Lancement en Développement:
pour lancer le Back-end dans un environnement de développement vous devez ouvrir le dossier `backend` avec le logiciel Eclipse ou autre en fonction de vos préférences et cliquer sur `run` ou `build` selon votre logiciel.

Lancement en Production:
Exécutez la commande:

> `mvn clean package`

Cela va compiler votre projet, exécuter les tests (s'il y en a), et générer le fichier JAR dans le répertoire target.

Une fois le fichier JAR généré, vous pouvez l'exécuter en utilisant la commande java -jar nom_du_fichier.jar.

### Base de données (MySQL):

Vous devez avoir MySQL d'installé et pouvoir l'utiliser en ligne de commande pour pouvoir réaliser les étapes suivantes sur la base de données.

#### Créez la base de données:

Dans votre invite de commande, une fois connecté en `root` suivez ces étapes:

> CREATE DATABASE chatop;

Ce qui créera la base de données.

> USE chatop;

Ce qui permettra de sélectionner la base de données nouvellement créée pour travailler dessus pendant les étapes suivantes.

en faisant SOURCE `chemin vers le fichier script2.sql`;

ex:

> SOURCE `ressources/sql/script.sql`;

Ce qui lancera les instructions incluses dans le fichier de script.

> ATTENTION, je vous ai fait volontairement créer la base de donnée dans mon exemple mais elle est totalement supprimée et recréée dans le fichier de script. Je l'ai fait pour vous expliquer les étapes.

#### Exploration du fichier de script:

> DROP DATABASE `chatop`;

Supprime la base de données nommée chatop.

---

> CREATE DATABASE `chatop`;

Crée une base de données nommée chatop.

---

```sql

CREATE TABLE `USERS` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `email` varchar(255),
  `name` varchar(255),
  `password` varchar(255),
  `created_at` timestamp,
  `updated_at` timestamp
);

```

Crée une table nommée USERS avec les champs suivants:
id: ce sera l'identifiant de chaque enregistrement et il s'incrémentera de manière automatique.
email: l'email de l'utilisateur en texte avec un maximum de 255 charactères.
name: le nom de l'utilisateur en texte avec un maximum de 255 charactères.
password: le mot de passe de l'utilisateur en texte avec un maximum de 255 charactères.
created_at: la date de création du profil en timestamp.
updated_at: la date de dernière modification du profil en timestamp.

---

```sql
CREATE TABLE `RENTALS` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255),
  `surface` numeric,
  `price` numeric,
  `picture` varchar(255),
  `description` varchar(2000),
  `owner_id` integer NOT NULL,
  `created_at` timestamp,
  `updated_at` timestamp
);

```

Crée une table nommée RENTALS avec les champs suivants:
id: ce sera l'identifiant de chaque enregistrement et il s'incrémentera de manière automatique.
name: le nom de l'annonce en texte avec un maximum de 255 charactères.
price: le prix de la location.
picture: le chemin de l'image avec un maximum de 255 charactères.
description: la description du bien avec un maximum de 2000 charactères.
owner_id: l'id de l'utilisateur qui a posté l'annonce (c'est une clé étrangère qui ne peut pas être nulle).
created_at: la date de création de l'annonce en timestamp.
updated_at: la date de dernière modification de l'annonce en timestamp.

---

```sql
CREATE TABLE `MESSAGES` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `rental_id` integer,
  `user_id` integer,
  `message` varchar(2000),
  `created_at` timestamp,
  `updated_at` timestamp
);

```

Crée une table nommée MESSAGES avec les champs suivants:
id: ce sera l'identifiant de chaque enregistrement et il s'incrémentera de manière automatique.
rental_id: l'id de l'annonce qui concerne ce message.
price: le prix de la location.
user_id: l'id de l'utilisateur qui a posté le message.
message: le message avec un maximum de 2000 charactères.
created_at: la date de création du message en timestamp.
updated_at: la date de dernière modification du message en timestamp.

---

> CREATE UNIQUE INDEX `USERS_index` ON `USERS` (`email`);

Crée un index de type UNIQUE sur les emails, qui aura pour but de ne pas pouvoir enregistrer un deuxième utilisateur avec un email déjà en base de données.

---

> ALTER TABLE `RENTALS` ADD FOREIGN KEY (`owner_id`) REFERENCES `USERS` (`id`);

Modifie la table RENTALS pour désigner que owner_id est une clé étragère reliée à l'id du la table USERS.

---

> ALTER TABLE `MESSAGES` ADD FOREIGN KEY (`user_id`) REFERENCES `USERS` (`id`);

Modifie la table MESSAGES pour désigner que user_id est une clé étragère reliée à l'id du la table USERS.

---

> ALTER TABLE `MESSAGES` ADD FOREIGN KEY (`rental_id`) REFERENCES `RENTALS` (`id`);

Modifie la table MESSAGES pour désigner que rental_id est une clé étragère reliée à l'id du la table RENTALS.

---

```sql
INSERT INTO USERS (email, name, password, created_at) VALUES
  ('test1@test.com', 'test1', 'test1', now()),
  ('test2@test.com', 'test2', 'test2', now()),
  ('test3@test.com', 'test3', 'test3', now());

```

Insère trois utilisateurs en base de données (dans la table USERS).

---

```sql
INSERT INTO RENTALS (name, surface, price, picture, description, owner_id, created_at) VALUES
  ('Chambre cosy', 18, 250, 'https://blog.technavio.org/wp-content/uploads/2018/12/Online-House-Rental-Sites.jpg', 'Chambre douillette dans maison luxueuse', 3, now()),
  ('T2 sympa', 65, 450, 'https://blog.technavio.org/wp-content/uploads/2018/12/Online-House-Rental-Sites.jpg', 'Appartement Coquet', 2, now()),
  ('Loft vintage', 500, 4250, 'https://blog.technavio.org/wp-content/uploads/2018/12/Online-House-Rental-Sites.jpg', 'Loft spacieux, parfait pour artistes', 1, now()),
  ('Manoir victorien', 2000, 42485, 'https://blog.technavio.org/wp-content/uploads/2018/12/Online-House-Rental-Sites.jpg', 'Manoir de type Victorien pour fiestas mondaines', 1, now());


```

Insère quatre biens en base de données (dans la table RENTALS).

---

```sql
INSERT INTO MESSAGES (rental_id, user_id, message, created_at) VALUES
  (1, 1, 'Les chiens sont-ils admis ?', now()),
  (2, 2, 'Un lit enfant est-il fourni ?', now()),
  (3, 3, 'Peut-on le louer pour une semaine ?', now()),
  (4, 1, 'Est-il admis de tourner un film dans ce manoir ?', now());

```

Insère quatre messages en base de données (dans la table MESSAGES).

---

## Ressources de développement

### Environement Mockoon

Mockoon était là pour permettre au développeur d'activer les routes du projet.

Lien de téléchargement de Mockoon: https://mockoon.com/download/

Après l'installation on le lance et on importe le fichier json contenant les routes

> ressources/mockoon/rental-oc.json

En faisant:

> File > Open environmement

Pour le lancer on clique sur le bouton `play`

Documentation de Mockoon: https://mockoon.com/docs/latest/about/

### collection Postman

Postman permettait de tester les routes du projet. Il fallait charger le json suivant:

> ressources/postman/rental.postman_collection.json

La documentation de Postman:

https://learning.postman.com/docs/getting-started/importing-and-exporting-data/#importing-data-into-postman

---

## Swagger:

L'url du Swagger:
http://localhost:8080/swagger-ui/index.html

## Index

---

#### Angular

<div id="angular">
Angular est un framework open-source développé et maintenu par Google, utilisé pour construire des applications web à grande échelle. Il s'appuie sur TypeScript, un sur-ensemble de JavaScript, et suit le modèle de conception MVC (Modèle-Vue-Contrôleur) ou plus précisément le modèle MVVM (Modèle-Vue-Modèle).
</div>

#### Framework

<div id="framework">
Un Framework (cadre de travail, ou cadriciel dans d'autres pays Francophones comme au Canada) est un ensemble structuré d'outils, de composants et de conventions de programmation conçus pour faciliter le développement d'applications logicielles. Les frameworks fournissent un cadre de travail standardisé qui permet aux développeurs de construire, déployer et maintenir des applications plus rapidement et efficacement en réutilisant des solutions prédéfinies pour des problèmes courants.
</div>

#### Java

<div id="java">
Java est un langage de programmation multiplateformes.
</div>

#### Javascript

<div id="javascript">
JavaScript est un langage de programmation de haut niveau, orienté objet et principalement utilisé pour le développement web côté client. JavaScript est un langage de programmation polyvalent et puissant, largement utilisé dans le développement web pour créer des applications interactives et dynamiques. Sa simplicité, sa flexibilité et son écosystème riche en font l'un des langages les plus populaires et les plus demandés dans l'industrie du développement logiciel.
</div>

#### Spring

<div id="spring">
Spring est un framwork Java.
</div>

#### Spring Security

<div id="spring_security">
Spring security est un (module) starter de spring, pour gérer la sécurité dans une application Java.
</div>

#### Typescript

<div id="typescript">
TypeScript est un langage de programmation open source développé par Microsoft. Il s'agit d'un sur-ensemble de JavaScript qui ajoute des fonctionnalités de typage statique optionnel, ce qui signifie que vous pouvez spécifier le type de données des variables, des paramètres de fonction, des propriétés d'objet, etc.
</div>
