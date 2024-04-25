package Main;

import java.util.Scanner;
import Services.Prodotto;
import Services.Utente;
import Services.Utils;
import Services.Ordine;

public class Application {

public static void main(String[] args){


Scanner scanner = new Scanner(System.in);
//inizializzazione dati
int choice = -1;

Utils.showOptions();

while(choice != 0){

    //scegliere opzione
    System.out.print("Scegli un'opzione: ");
    choice = scanner.nextInt();
  
    //switch casi->scelta
switch (choice) {
        //termina
        case 0:
        Utils.printOption(choice);
        scanner.close();
        break;

        //carica prodotti
        case 1:
        Utils.printOption(choice);
        Prodotto.readProdotti();
        Utils.showOptions();
        break;

        //compra prodotto
        case 2:
        Utils.printOption(choice);
        Prodotto.compraProdotto();
        Utils.showOptions();
        break;

        //carica ordini vendita
        case 3:
        Utils.printOption(choice);
        Ordine.readOrder();
        Utils.showOptions();
        break;

        //aggiungi utente
        case 4:
        Utils.printOption(choice);       
        Utente.addUserList();
        Utils.showOptions();
        break;

        //esporta file prodotti presenti
        case 5:
        Utils.printOption(choice);
        Prodotto.exportAvailable();
        Utils.showOptions();
        break;

        //altri casi
        default:
        System.out.println(Utils.RED+"Opzione non ammessa!"+ Utils.RESET+"\n");
        Utils.showOptions();
        break;
    }
   
}
    }
}
