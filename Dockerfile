# Utilisez une image de base Java
FROM openjdk:8-jdk-alpine

# Définir le répertoire de travail dans le conteneur
WORKDIR /app

# Copier le fichier JAR dans le conteneur
COPY target/projetdevops-y.jar /app/demo.jar

# Commande pour exécuter lors du démarrage du conteneur
CMD ["java", "-jar", "/app/demo.jar"]