package Services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Ordine {
    static Scanner scanner = new Scanner(System.in);
    static String fileName = "INPUT_PATH*************/java-project/data/vendite.csv";
    static int id_order;
    
    //aggiungere ordine a vendite.csv
    public static void addOrderList(String id_prodotto, String id_utente, String id_vendita){
      try {
        FileWriter pw = new FileWriter(fileName, true);
        pw.append("\n");
        pw.append(id_vendita+","+id_prodotto+","+id_utente);
        pw.flush();
        pw.close();
        } catch (IOException e) {
        System.out.println(e);
      }
    }
    
    //Caricamento ordine
    public  static void readOrder(){ 
       try {
        System.out.print("ID ordine: ");
        id_order = scanner.nextInt();
    //verifica id ordine esiste
        try {
        while(!Ordine.orderIdexist(id_order)){
        System.out.print("ID ordine: ");
        id_order = scanner.nextInt(); 
        }
        }catch (IOException e) {
        e.printStackTrace();
        }
        String currentLine;
        BufferedReader br; 
        br = new BufferedReader(new FileReader(fileName));
        System.out.println(Utils.PURPLE+"ID |ID prodotto |ID utente"+Utils.RESET);
        while ((currentLine = br.readLine()) != null) { 
        String[] str = currentLine.split(",");
        if(Integer.toString(id_order).equals(str[0])){
        str[0] = String.format("%-3s", str[0]);
        str[1] = String.format("%-12s", str[1]);
        System.out.println(Utils.PURPLE+str[0] + "|" + str[1] + "|" + str[2]+Utils.RESET); 
        }
              
        }
        System.out.println("\n");
        br.close();
        } catch (IOException e) {
        System.out.println(e);
        }
   }

   //verifica che ordine esista
   public static boolean orderIdexist(int id) throws IOException{

    String currentLine;
    BufferedReader br; 
    boolean exists = false;
    br = new BufferedReader(new FileReader("INPUT_PATH*************/java-project/data/vendite.csv"));
   
    while ((currentLine = br.readLine()) != null) { 
    String[] str = currentLine.split(",");
    if(str[0].equals(Integer.toString(id))){
    exists = true;
    }
    }
    if(exists){
    br.close();
    return true;
    }else{
    System.out.println(Utils.RED+"Ordine non esiste"+Utils.RESET+"\n");
    br.close();
    return false;
    }
}

//assegna id vendita
public static int AssignOrderId()throws IOException{

        BufferedReader br; 
        br = new BufferedReader(new FileReader("INPUT_PATH*************/java-project/data/vendite.csv"));
        int max=0;
        while ( br.readLine() != null) { 
          max++;
        }
        br.close();
    return max;
}


}
