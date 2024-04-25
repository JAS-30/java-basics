package Services;

public class Utils {
//colori
public static final String RESET = "\033[0m";  // Text Reset
public static final String CYAN = "\033[0;36m";    // CYAN
public static final String RED = "\033[0;31m";     // RED
public static final String GREEN = "\u001B[32m";   // GREEN
public static final String PURPLE = "\u001B[35m"; //PURPLE
//lista opzioni
public static String[] options = new String[]{"Visualizzare tutti i prodotti nel sistema", "Comprare un prodotto", "Restituire un prodotto","Aggiungere nuovo utente","Esportare file con prodotti disponibili","Uscire dal programma"};

//Stampa opzioni
public static void showOptions() {
       for(int i=0; i<options.length;i++){
       if(i == options.length - 1){
       System.out.println( CYAN +0 +"|"+ options[i]+ RESET+"\n");
       }else{
       System.out.println(CYAN+ (i+1) +"|"+ options[i]+RESET);
        }
       }
}

//Stampa scelta
public static void printOption(int value){
       if(value >= 1 && value <=5){
        System.out.println("Hai scelto: "+Utils.GREEN + options[value - 1 ]+Utils.RESET);
       }
       if(value ==0){
        System.out.println("Hai scelto: "+Utils.GREEN + options[options.length - 1 ]+Utils.RESET);
    }
}
    
}

