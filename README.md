# CodiceEAN
Il codice EAN è un formato di codice a barre usato in Europa per la marcatura dei prodotti destinati alla vendita al  dettaglio. Il codice EAN 13 è un codice composto da 13 cifre suddivise in 4 gruppi:

13|12|11|10|09|08|07|06|05|04|03|02|01
v  v |b  b  b  b  b | g  g  g  g  g| r 

v >> nazione  b >> codice ditta  g >> codice articolo  r >> check digit

Per la determinazione di tale cifra è comodo numerare i singoli caratteri da destra verso sinistra, in modo che il check  digit sia in posizione 1, quindi:
1) si sommano i valori dei caratteri in posizione pari;
2) 2) il risultato così ottenuto si moltiplica per 3;
3) 3) si sommano i valori dei caratteri in posizione dispari a partire dalla posizione 3;
4) 4) si sommano i due risultati parziali;
5) 5) come check digit si individua il numero da sommare al risultato precedente per ottenere un multiplo di 10.
