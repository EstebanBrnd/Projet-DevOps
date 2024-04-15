# Projet de DevOps de M1 INFO

![Workflow](https://github.com/EstebanBrnd/Projet-DevOps/actions/workflows/main.yaml/badge.svg)
![tests-check](https://github.com/<OWNER>/<REPO>/actions/workflows/tests-check.yml/badge.svg)

Collaborateurs :   

Lilou Bouvier - lilou.bouvier@etu.univ-grenoble-alpes.fr  
David Quintela - david.quintela@etu.univ-grenoble-alpes.fr  
Lukasz Matyasik - lukasz.matyasik@etu.univ-grenoble-alpes.fr  
Esteban Barneaud - esteban.barneaud@etu.univ-grenoble-alpes.fr

Go to [Authors](/AUTHORS)


## Sommaire

- [Structure du projet ](#structure-du-projet)
- [Fonctionnalités](#fonctionnalités)
  - [Constructeurs](#constructeurs)
  - [Affichage](#affichage)
  - [Selection](#selection)
  - [Statistiques](#statistiques)
- [Outils utilisés](#outils-utilisés)
  - [Maven](#maven)
  - [Git](#git)
  - [JUnit](#junit)
  - [JaCoCo](#jacoco)
- [Intégration continue](#intégration-continue)
  - [GitHub Actions](#github-actions)
  - [Procédure validation PR/MR](#procédure-validation-prmr)
- [Feedback](#feedback)




## Structure du projet

Projet géré avec Maven

## Fonctionnalités

### Constructeurs

Nous avons implémenté 2 constructeurs pour le dataframe. L'un permet de créer un dataframe à partir d'un fichier CSV ([voir ici](#constructeur-csv)), l'autre permet de créer un dataframe à partir d'une liste contenant les labels et types de chaque colonne ([voir ici](#constructeur-tableau)).

#### Constructeur CSV

Le constructeur CSV permet de créer un dataframe à partir d'un fichier CSV. Il prend en paramètre le chemin du fichier CSV et crée un dataframe à partir de ce fichier. Le constructeur lève une exception si le fichier n'existe pas ou si le fichier n'est pas un fichier CSV.

Voici un exemple d'utilisation du constructeur CSV :
```java
Dataframe dataframe = new Dataframe("src/test/resources/data.csv");
```

#### Constructeur Tableau

Le constructeur Tableau permet de créer un dataframe à partir d'une liste contenant les labels et types de chaque colonne. Il prend en paramètre une liste de labels et une liste de types. 

Voici un exemple d'utilisation du constructeur Tableau :
```java
ArrayList<Couple<String,Class>> types = new ArrayList<>();
types.add(new Couple<String,Class>("nom 1ere colonne", String.class));
types.add(new Couple<String,Class>("nom 2eme colonne", Integer.class));
types.add(new Couple<String,Class>("nom 3eme colonne", Integer.class));
types.add(new Couple<String,Class>("nom 4eme colonne", Double.class));
types.add(new Couple<String,Class>("nom 5eme colonne", String.class));
Dataframe dataframe = new Dataframe(types);
```

### Affichage

Nous avons implémenté plusieurs fonctions d'affichage pour le dataframe. La première permet d'afficher le dataframe dans son intégralité, la seconde permet d'afficher les premières lignes du dataframe tandis que la troisième permet d'afficher les dernières lignes du dataframe.

Voici un exemple d'utilisation de ces fonctions :
```java
Dataframe dataframe = new Dataframe("src/test/resources/data.csv");
dataframe.afficheData();
dataframe.afficherPremieresLignes(5); // Affiche les 5 premières lignes
dataframe.afficherDernieresLignes(5); // Affiche les 5 dernières lignes
```

### Selection

### Statistiques

## Outils utilisés

### Maven

Nous avons utilisé Maven pour gérer les dépendances de notre projet. Ainsi nous avons pu configurer nos tests et notre couverture de code par exemple en ajoutant les dépendances nécessaires dans le fichier `pom.xml`.

### Git

Nous avons utilisé Git pour gérer notre projet. Nous avons créé un dépôt Git sur GitHub et avons travaillé en collaboration sur ce dépôt. Nous avons utilisé des branches pour travailler sur des fonctionnalités différentes et avons fusionné ces branches avec la branche principale une fois les fonctionnalités terminées.

### JUnit

Nous avons utilisé JUnit pour écrire des tests unitaires pour notre projet. Nous avons écrit des tests pour les différentes fonctionnalités de notre projet et avons vérifié que ces tests passaient correctement en les ajoutant à notre pipeline d'intégration continue.

### JaCoCo

Nous avons utilisé JaCoCo pour mesurer la couverture de code de notre projet. Nous avons configuré JaCoCo dans notre pipeline d'intégration continue pour vérifier que notre code était bien testé et que la couverture de code était suffisante. Nous avons choisi un seuil de couverture de code de 70% pour notre projet.

## Intégration continue

### GitHub Actions

Nous avons configuré un pipeline d'intégration continue pour notre projet. Ce pipeline est déclenché à chaque fois qu'une Pull Request concernant la branche main ou dev est ouverte sur notre dépôt Git. Le pipeline vérifie que les tests passent correctement et que la couverture de code est suffisante. Si ces conditions sont remplies, alors le feu vert est donné pour ces tests là.

### Procédure validation PR/MR

Additionnellement, nous avons mis en place une procédure de validation des Pull Requests. Ainsi, chaque Pull Request doit être validée par un ou deux autre(s) membre(s) de l'équipe (selon la branche) avant d'être fusionnée avec la branche cible. Cette procédure permet de rajouter une couche de sécurité supplémentaire pour éviter de fusionner du code non testé, non fonctionnel, non conforme à la norme de code ou encore complexe à comprendre.

## Feedback

```

```


