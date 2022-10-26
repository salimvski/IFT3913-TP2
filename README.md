## Tache 2

- Il est ideal d'essayer les scripts sous un environnement linux
- Ne pas oublier de cloner le projet jfreechart
- ``` git clone git@github.com:jfree/jfreechart.git ```

# Pour les metriques Age, Issues, TPC (tests par classe)
- Il suffit de lancer la commande ``` java main.java ```
- En resultat, on obtient un fichier result.csv pour les resultats de metriques
sur le projet jfreechart


## Pour la metrique RTCTT (Ratio taille code / taille test)
- il suffit la commande ``` ./src/bash/metrics/RTCTT.bash ``` en resultat
le fichier RTCTT_metric.txt qui donne le ratio taille test / taille code
de chaque fichier java qui n'est pas un test et qui a un fichier correspondant.
la derniere ligne du fichier RTCTT_metric.txt donne la moyenne de tout les ratios


## Pour la metrique DC (densite commentaire)
- Elle calcule le ratio nombre ligne de code de commentaire / par ligne de code total
supprimer avant le fichier DC_metric.txt s'il existe deja
execution : `./src/python/metrics/DC.bash `
- En sortie le resultat du ratio commentaire / ligne de code dans le fichier DC_metric.txt
pour chaque fichier java dans le main (non Test), et dans la derniere ligne du fichier on obtient la moyenne de tout les rapport

## Pour la metrique qui check le duplicat de code

- Nous avons utilise l'outil extern https://pmd.github.io/

- Elle voit dans tout le projet jfreechart les duplicats de code entre different fichier du projet
- Pour executer la commande il suffit de run `./src/python/metrics/duplicate_metrics/CC.bash 100
- 100 represente une valeur choisie au hasard qui represente le nombre de charactere minumum 
duplique doit chercher le script entre les fichier du projet
- CC_metric.txt contient les output des duplicats en quesiton
- le output de la commande bash ci-dessous donne le nombre de duplicat environ
dans tout le projet Jfreechart ou avec au minumum 100 characteres 

## Pour la metrique couplage qui check le couplage du code

- Nous n'avons pas eu le temps de la faire car notre programme correspondant
dans le TP1 parse specialement pour le dossier propose dans le TP1.
- Si c'etait le meme dossier, nous aurions recuperer le score pour chaque class
de chaque fichier dans jfreechart et nous aurions fait la moyenne. 