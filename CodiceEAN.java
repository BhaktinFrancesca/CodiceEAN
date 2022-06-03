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
        System.out.println("Il codice EAN popolato è >> " + Arrays.toString(cc.getCodiceEANPopolato()));
        System.out.println("L'ultima cifra del codice è >> " + cc.getCheckDigit());
    }
}

class CalcoloCodice {
    //attributi
    private int lunghezzaCodiceEAN = 13;
    private int[] codiceEANPopolato = new int[lunghezzaCodiceEAN];
    private int checkDigit = 0;

    //costruttore
    public CalcoloCodice() {
        popolaCodiceEAN();

        int sommaVerifica = sommaRisultati(); // (sommaPosizioniPari() * 3) + sommaDispari()
        codiceEANPopolato[lunghezzaCodiceEAN - 1] = checkDigit(sommaVerifica);
    }

    public int[] getCodiceEANPopolato() {
        return codiceEANPopolato;
    }

    public int getCheckDigit() {
        return checkDigit;
    }

    //metodi
    //somma posizioni pari (passo 1) --------------------------------------------------
    private int sommaPosizioniPari(){
        int sommaP = 0;
        for (int i = 0; i < lunghezzaCodiceEAN; i++){
            if((i+1) % 2 == 0){
                sommaP += codiceEANPopolato[i];
            }
        }

        return sommaP;
    }

    //moltiplica per 3 (passo 2) ------------------------------------------------------
    private int moltiplicaPerTre() {
        int moltiplicazione = sommaPosizioniPari() * 3;
        return moltiplicazione;
    }

    //somma posizioni dispari a partire dalla posizione 3 (passo 3)--------------------
    private int sommaPosizioniDispari(){
        int sommaD = 0;
        for(int i = 0; i < lunghezzaCodiceEAN-1; i++){
            if((i+1) % 2 == 1){
                sommaD += codiceEANPopolato[i];
            }
        }
        return sommaD;
    }

    //si sommano i due risultati parziali (passo 4) -----------------------------------
    private int sommaRisultati(){
        return moltiplicaPerTre() + sommaPosizioniDispari();
    }

    //come check digit si individua il numero da sommare al risultato precedente per ottenere un multiplo di 10
    private int checkDigit(int sommaDiVerifica){
        if (sommaDiVerifica % 10 == 0){
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
    private void popolaCodiceEAN(){
        Random rd = new Random();
        for (int i = 0; i < codiceEANPopolato.length - 1; i++) {
            codiceEANPopolato[i] = rd.nextInt(9);
        }
    }
}
