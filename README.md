# Simulation d'un feu de forêt

## Énoncé :
L'objectif est d'implémenter une simulation de la propagation d’un feu de forêt.

Durée indicative de l’exercice : environ 2h/3h

La forêt est représentée par une grille de dimension h x l.\
La dimension temporelle est discrétisée. Le déroulement de la simulation se fait donc étape par étape.\
Dans l’état initial, une ou plusieurs cases sont en feu.

Si une case est en feu à l’étape t, alors à l’étape t+1 :
- Le feu s'éteint dans cette case (la case est remplie de cendre et ne peut ensuite plus brûler)
- Il y a une probabilité p que le feu se propage à chacune des 4 cases adjacentes

La simulation s’arrête lorsqu’il n’y a plus aucune case en feu\
Les dimensions de la grille, la position des cases initialement en feu, ainsi que la probabilité de propagation, sont des paramètres du programme stockés dans un fichier de configuration (format libre).

Ce qui nous intéresse n’est pas de simplement voir un programme de simulation tourné avec la meilleur IHM mais surtout de comprendre :
- Comment vous appréhendez un problème
- Comment vous codez
- Quels sont vos choix architecturaux
- Comment vous présentez votre travail une fois réalisé

## Utilisation

Vérifiez que votre variable d'environnement Java pointe bien sur la version 21, car c'est elle qui a été utilisée pour développer cette application, et est indiquée dans le fichier pom.xml

Construisez le fichier JAR à travers votre console, depuis le repertoire du projet :\
```mvn package```

Puis exécutez le JAR depuis le même repertoire :\
```java -jar ./target/ForestFire-1.0.jar```

Pour la bonne exécution du programme, un fichier ```config.json``` doit être présent dans le répertoire depuis lequel
la commande d'exécution du programme est lancée.\
Voici son format attendu :
```
{
  "forestHeight": int,
  "forestWidth": int,
  "fireSpreadProbability": double,
  "ignitedTrees": [
    [int, int]
  ],
  "treeHeight": int,
  "treeWidth": int
}
```
- forestHeight & forestWidth définissent les dimensions de la forêt (nombre d'arbres)
- fireSpreadProbability représente la probabilité, comprise entre 0 et 1 inclus, que le feu se propage aux arbres voisins
- ignitedTrees est un tableau de coordonnées [x, y] d'arbres en feu à la première étape de la simulation
- treeHeight & treeWidth définissent les dimensions en pixel de chaque arbre dans l'interface graphique de la simulation