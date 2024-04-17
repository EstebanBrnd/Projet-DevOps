# Projet de DevOps de M1 INFO

![Workflow](https://github.com/EstebanBrnd/Projet-DevOps/actions/workflows/main.yaml/badge.svg)

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
- [Livraison continue](#livraison-continue)
- [Site Github Pages](#site-gh-pages)
- [Feedback](#feedback)




## Structure du projet

Ce projet a été créé avec le gestionnnaire de dépendances Maven.

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

Le constructeur Tableau permet de créer un dataframe à partir de deux listes. La première contenant des listes de données et la deuxième le label de chaque colonne du dataframe (pour chacune des listes de données).

Voici un exemple d'utilisation du constructeur Tableau :
```java
ArrayList<ArrayList<String>> data = new ArrayList<>();
ArrayList<String> columnNames = new ArrayList<>();
columnNames.add("Nom Colonne 1");
columnNames.add("Nom Colonne 2");
ArrayList<String> D1 = new ArrayList<>();
D1.add("Test");
ArrayList<String> D2 = new ArrayList<>();
D2.add("2");
ArrayList<String> D3 = new ArrayList<>();
D3.add("23");
ArrayList<String> D4 = new ArrayList<>();
D4.add("2f");
ArrayList<String> D5 = new ArrayList<>();
D5.add("7/4/2024");
data.add(D2);
data.add(D3);
data.add(D4);
data.add(D5);
Dataframe test = new Dataframe(data, columnNames);
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

Nous avons implémenté des fonctions de sélection pour le dataframe. Ainsi, il est possible de sélectionner des données grâce à leur index en ligne ou en ligne et colonne, ou encore grâce à leur label de colonne et même de ligne et colonne si la première colonne est une chaîne de caractères.

```java
Dataframe dataframe = new Dataframe("src/test/resources/data.csv");
dataframe.iloc(0); // Selectionne la première ligne
dataframe.iloc(0, 0); // Selectionne la première ligne et la première colonne
dataframe.iloc([1,2]); // Sectionne la ligne 2 et 3
dataframe.iloc([true,false,true]) // Selectionne la ligne 1 et 3

dataframe.loc("Nom"); // Selectionne la colonne "Nom"
dataframe.loc(["Nom", "Age"]); // Selectionne les colonnes "Nom" et "Age"
dataframe.loc("Nom", "Test"); // Selectionne la colonne de "Nom" où la première ligne est "Test"
dataframe.loc([true,false,false,false,true]); // Selectionne les colonnes 1 et 5
```

### Statistiques

Nous avons implémenté des fonctions de statistiques permettant de calculer la moyenne, le minimum ou le maximum de deux façons différentes. La première, prend un nom de colonne et renvoie la statistique demandée pour cette colonne. La deuxième ne prend rien en argument et renvoie une liste contenant la statistique demandée pour chaque colonne ou null si cette colonne ne contient ni des entiers, ni des flottants.  

## Outils utilisés

### Maven

Nous avons utilisé Maven pour gérer les dépendances de notre projet. Ainsi nous avons pu configurer nos tests et notre couverture de code par exemple en ajoutant les dépendances nécessaires dans le fichier `pom.xml`. Nous avons également pu configurer la cible de notre jar comme étant la démonstration de notre projet.

### Git

Nous avons utilisé Git pour gérer notre projet. Nous avons créé un dépôt Git sur GitHub et avons travaillé en collaboration sur ce dépôt. Nous avons utilisé des branches pour travailler sur des fonctionnalités différentes et avons fusionné ces branches avec la branche principale une fois les fonctionnalités terminées.

Ainsi, main est la branche dans laquelle les "clients" récupereraient notre projet sous sa version stable. dev est une branche où nous, les developpeurs préparont cette version stable et fusionnons project actuel et nouvelles fonctionnalités. Il y a ensuite les branches pour chaque fonctionnalité qui se subdivise parfois en de nouvelles sous-branches (exemple: constructeur avec constructeur-tableau et constructeur-csv).

### JUnit

Nous avons utilisé JUnit pour écrire des tests unitaires pour notre projet. Nous avons écrit des tests pour les différentes fonctionnalités de notre projet et avons vérifié que ces tests passaient correctement en les ajoutant à notre pipeline d'intégration continue.

Ces tests produisent ensuite des badges que nous affichons en haut de notre ReadMe comme vous pouvez le constater.

### JaCoCo

Nous avons utilisé JaCoCo pour mesurer la couverture de code de notre projet. Nous avons configuré JaCoCo dans notre pipeline d'intégration continue pour vérifier que notre code était bien testé et que la couverture de code était suffisante. Nous avons choisi un seuil de couverture de code de 70% pour notre projet.

Le rapport de ces tests est ensuite mis à disposition avec la page web générée par jacoco.

## Intégration continue

### GitHub Actions

Nous avons configuré un pipeline d'intégration continue pour notre projet. Ce pipeline est déclenché à chaque fois qu'une Pull Request concernant la branche main ou dev est ouverte sur notre dépôt Git. Le pipeline vérifie que les tests passent correctement et que la couverture de code est suffisante. Si ces conditions sont remplies, alors le feu vert est donné pour ces tests là.
Nous avons également la création du jar et le dépot docker qui est réalisée dans la pipeline.

Voir nos fichiers yaml :
[ici](/.github/workflows/main.yaml) et [ici](/.github/workflows/docker.yaml)

### Procédure validation PR/MR

Additionnellement, nous avons mis en place une procédure de validation des Pull Requests. Ainsi, chaque Pull Request doit être validée par un ou deux autre(s) membre(s) de l'équipe (selon la branche) avant d'être fusionnée avec la branche cible. Cette procédure permet de rajouter une couche de sécurité supplémentaire pour éviter de fusionner du code non testé, non fonctionnel, non conforme à la norme de code ou encore complexe à comprendre.

Nos branches main et dev nécessite donc 2 revues de code avant de merge dedans. Tandis que constructeur nécessite une seule revue car il ne s'agit pas d'une branche principale mais comme la branche se subdivise, il est plus raisonnable d'ajouter une revue.

## Livraison continue

Nous avons pu déployer une image docker réalisant une démonstration rapide de quelques fonctions de notre librairie ([voir le fichier](/src/main/java/projetdevops/Demo.java)). Cette image docker est accessible en suivant ce lien :
```
ghcr.io/estebanbrnd/projetdevops
```
Il suffit de pull cette image et de la run.

## Site GH Pages

Nous avons également pu faire en sorte que ce ReadMe soit transformé en une page web accesible en cliquant [ici](https://estebanbrnd.github.io/Projet-DevOps/).
A chaque mise à jour du ReadMe sur la branche dev, le site est donc mis à jour.


## Feedback

```
Les revues de code obligatoires avant merge sont autant un avantage permettant plus de sécurité dans le développement, autant qu'un frein au développement car cela nous force à attendre les revues d'autres collaborateurs.
Les problèmes de synchronisation entre les différentes branches ont été récurents et difficiles à résoudre, nous faisant perdre souvent beaucoup de temps inutilement. Nous avons donc réussi tant bien que mal à contourner ce problème de temps à autre afin d'avancer un peu plus rapidement.
```


