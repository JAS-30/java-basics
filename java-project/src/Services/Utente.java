package Services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Utente {

    static Scanner scanner = new Scanner(System.in);
    static int id;
    static String nome,cognome,nascita,documento,indirizzo="";

    //aggiungi utente
    public static void addUserList(){
        try {
        System.out.println("Inserisci dati nuovo utente");
        System.out.print("ID: ");
        id = scanner.nextInt();
        //Controllo id utente già in uso
        try{
        while(Utente.userIdTaken(id)==true){
        System.out.println("Inserisci dati nuovo utente"+"\n");
        System.out.print("ID: ");
        id = scanner.nextInt();
        }
        }catch (IOException e) {
        e.printStackTrace();
        }
        System.out.print("Nome: ");
        nome = scanner.next();
        System.out.print("Cognome: ");
        cognome = scanner.next();
        System.out.print("Nascita: ");
        nascita = scanner.next();
        System.out.print("ID documento: ");
        documento =scanner.next();
        System.out.print("Indirizzo: ");
        while(indirizzo.length()==0){
        indirizzo = scanner.nextLine();
        } 
        FileWriter pw = new FileWriter("INPUT_PATH*************/java-project/data/utenti.csv", true);
        pw.append("\n");
        pw.append(id+","+nome+","+cognome+","+nascita+","+indirizzo+","+documento);
        pw.flush();
        pw.close();
        System.out.println(Utils.GREEN+"Utente aggiunto"+Utils.RESET+"\n");
        } catch (IOException e) {
           System.out.println(e);
        }
    }

    //verifica che id selezionato sia disponibile
    public static boolean userIdTaken(int id) throws IOException{

		String currentLine;
        BufferedReader br; 
        boolean new_id = true;

        br = new BufferedReader(new FileReader("INPUT_PATH*************/java-project/data/utenti.csv"));
		
        while ((currentLine = br.readLine()) != null) { 
        String[] str = currentLine.split(",");
		if(str[0].equals(Integer.toString(id))){
        System.out.println(Utils.RED+"ID non disponibile.Riprova."+Utils.RESET+"\n");
        new_id = false;
                }
        }
        if(new_id){
        br.close();
        return false;
        }else{
        br.close();
        return true;
        }
    }


    //verifica che id utente esista nel sistema
    public static boolean userIdExists(int id) throws IOException{

        String currentLine;
        BufferedReader br; 
        boolean fake_id = true;
                   
        br = new BufferedReader(new FileReader("INPUT_PATH*************/java-project/data/utenti.csv"));
      
        while ((currentLine = br.readLine()) != null) { 
        String[] str = currentLine.split(",");
        if(str[0].equals(Integer.toString(id))){
        fake_id = false;
            }
        }
        if(fake_id){
        System.out.println(Utils.RED+"ID: "+id+" non disponibile.Riprova"+Utils.RESET);
        br.close();
        return false;
        }else{
        System.out.println(Utils.GREEN+"In uso ID: "+id+Utils.RESET);
        br.close();
        return true;
        }
}
}
