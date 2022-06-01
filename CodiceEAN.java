import java.util.Arrays;
import java.util.Random;

/**Il codice EAN è un formato di codice a barre usato in Europa per la marcatura dei prodotti destinati alla vendita al
 dettaglio. Il codice EAN 13 è un codice composto da 13 cifre suddivise in 4 gruppi:

 13|12|11|10|09|08|07|06|05|04|03|02|01
 v  v |b  b  b  b  b | g  g  g  g  g| r

 v >> nazione
 b >> codice ditta
 g >> codice articolo
 r >> check digit

 Per la determinazione di tale cifra è comodo numerare i singoli caratteri da destra verso sinistra, in modo che il check
 digit sia in posizione 1, quindi:
 1) si sommano i valori dei caratteri in posizione pari;
 2) il risultato così ottenuto si moltiplica per 3;
 3) si sommano i valori dei caratteri in posizione dispari a partire dalla posizione 3;
 4) si sommano i due risultati parziali;
 5) come check digit si individua il numero da sommare al risultato precedente per ottenere un multiplo di 10.
 */

public class CodiceEAN {

    public static void main(String[] args){
        //variabili
        CalcoloCodice cc = new CalcoloCodice();

        //calcoli

        //sysout
        System.out.println("Il codice EAN popolato è >> " + Arrays.toString(cc.codiceEANPopolato));
        int somma = cc.sommaPosizioniPari(cc.codiceEAN, cc.lunghezzaCodiceEAN, cc.sommaP);
        System.out.println("La somma dei numeri in posizioni pari è >> " + somma);
        int moltiplica = cc.moltiplicaPerTre(cc.sommaP);
        System.out.println("La moltiplicazione per tre della somma dei numeri pari è uguale a >> " + moltiplica);
        int sommaDispari = cc.sommaPosizioniDispari(cc.codiceEANPopolato, cc.lunghezzaCodiceEAN, cc.sommaD);
        System.out.println("La somma dei numeri in posizioni dispari a partire dalla terza posizione è >> "
                + sommaDispari);
        int sommaTotale = cc.sommaRisultati(cc.codiceEANPopolato, cc.lunghezzaCodiceEAN, cc.sommaP, cc.sommaD);
        System.out.println("La somma dei numeri in posizione pari coi numeri della posizione dispari è uguale a >> "
                + sommaTotale);
        int ultimaDigit = cc.checkDigit(cc.codiceEANPopolato, cc.lunghezzaCodiceEAN, cc.sommaP, cc.sommaD, cc.checkDigit, cc.sommaDiVerifica);
        System.out.println("L'ultima cifra del codice è >> " + ultimaDigit);
    }
}

class CalcoloCodice {
    //attributi
    public int[] codiceEAN = new int[13];
    public int lunghezzaCodiceEAN = 13;
    Random rd = new Random();
    public int[] codiceEANPopolato = popolaCodiceEAN(lunghezzaCodiceEAN, codiceEAN, rd);
    public int sommaP = 0;
    public int sommaD = 0;
    public int checkDigit = 0;
    public int sommaDiVerifica = 0;

    //metodi
    //somma posizioni pari (passo 1) --------------------------------------------------
    public int sommaPosizioniPari(int[] codiceEANPopolato, int lunghezzaCodiceEAN, int sommaP){
        for(int i = 0; i < lunghezzaCodiceEAN; i++){
            if(i % 2 == 0){
                sommaP = sommaP + codiceEANPopolato[i];
            }
        }

        return sommaP;
    }

    //moltiplica per 3 (passo 2) ------------------------------------------------------
    public int moltiplicaPerTre(int sommaP){
        int moltiplicazione = sommaPosizioniPari(codiceEANPopolato,lunghezzaCodiceEAN, sommaP) * 3;
        return moltiplicazione;
    }

    //somma posizioni dispari a partire dalla posizione 3 (passo 3)--------------------
    public int sommaPosizioniDispari(int[] codiceEANPopolato, int lunghezzaCodiceEAN, int sommaD){
        for(int i = 2; i < lunghezzaCodiceEAN; i++){
            if(i % 2 == 1){
                sommaD = sommaD + codiceEANPopolato[i];
            }
        }
        return sommaD;
    }

    //si sommano i due risultati parziali (passo 4) -----------------------------------
    public int sommaRisultati(int[] codiceEANPopolato, int lunghezzaCodiceEAN, int sommaP, int sommaD){
        return sommaPosizioniPari(codiceEANPopolato, lunghezzaCodiceEAN, sommaP) +
                sommaPosizioniDispari(codiceEANPopolato, lunghezzaCodiceEAN, sommaD);
    }

    //come check digit si individua il numero da sommare al risultato precedente per ottenere un multiplo di 10
    public int checkDigit(int[] codiceEANPopolato, int lunghezzaCodiceEAN, int sommaP, int sommaD, int checkDigit, int sommaDiVerifica){

        sommaDiVerifica = sommaRisultati(codiceEANPopolato, lunghezzaCodiceEAN, sommaP, sommaD);

        if (sommaRisultati(codiceEANPopolato, lunghezzaCodiceEAN, sommaP, sommaD) % 10 == 0){
            checkDigit = 0;

        } else {
            do{
               sommaDiVerifica += 1;
                checkDigit++;

            } while (sommaDiVerifica % 10 != 0);
        }
        return checkDigit;
    }

    //popola il codice EAN ------------------------------------------------------------
    public int[] popolaCodiceEAN(int lunghezzaCodiceEAN, int[] codiceEANPopolato, Random rd){
        for (int i = 0; i < lunghezzaCodiceEAN; i++) {
            codiceEANPopolato[i] = rd.nextInt(9);
        }

        return codiceEANPopolato;
    }
}
