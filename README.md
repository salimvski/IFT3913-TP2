## Tache 2

# Il est ideal d'essayer les scripts sous un environnement linux

# Pour les metriques Age, Issues, TPC (tests par classe)
# Il suffit de lancer la commande ``` java main.java ```
# En resultat, on obtient un fichier result.csv pour les resultats de metriques
# sur le projet jfreechart


## Pour la metrique RTCTT (Ratio taille code / taille test)
# il suffit la commande ``` ./src/bash/metrics/RTCTT.bash ``` en resultat
# le fichier RTCTT_metric.txt qui donne le ratio taille test / taille code
# de chaque fichier java qui n'est pas un test et qui a un fichier correspondant
# la dernier du fichier RTCTT_metric.txt donne la moyenne de tout les ratios


## Pour la metrique DC (densite commentaire)
# Elle calcule le ratio nombre ligne de code de commentaire / par ligne de code total
# supprimer avant le fichier DC_metric.txt s'il existe deja
# execution : `./src/python/metrics/DC.bash `
# En sortie le resultat du ratio commentaire / ligne de code
# pour chaque fichier java dans le main (non Test).
# et dans la derniere ligne du fichier on obtient la moyenne de tout les rapport
