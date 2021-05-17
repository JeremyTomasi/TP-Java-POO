# TP-Java-POO

Ce repository sert à stocker les sources du TP "Top Chef" lors du module de POO
réalisé en Semestre 4 de L2 Informatique à l'université Polytechnique Hauts-de-France.

Les sources se situent dans le dossier `src/main/java` et les tests se situent dans le dossier `src/test/java`.  

Le dossier `src/main/java/fr/jtomasi/exceptions` contient toutes les exceptions utilisées dans le projet.  
Le dossier `src/main/java/fr/jtomasi/ingredients` contient toutes les classes liés aux ingrédients dans le projet.  
Le dossier `src/main/java/fr/jtomasi/personnes` contient toutes les classes en relation avec les différentes personnes dans le projet.

La base de données MariaDB est à lancer via l'outil Docker via la commande `docker compose up`.  

Le fichier `src/main/resources/META-INF/persistence.xml.example` est à renommer en `src/main/resources/META-INF/persistence.xml` et contient la configuration permettant la connexion à la base de données MySQL ou MariaDB.  
Pour changer l'URL permettant de connecter votre base de données MySQL, il suffit de changer la valeur de l'attribut `javax.persistance.jdbc.url` en suivant le schéma suivant :  
`jdbc:mysql://NOM_HOTE:3306/NOM_DB`  
Pour modifier votre username et votre mot de passe, il suffit de changer les valeurs correspondant aux attributs `javax.persistence.jdbc.user` et à `javax.persistence.jdbc.password`