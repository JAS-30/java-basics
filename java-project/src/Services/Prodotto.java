package Services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.RandomAccessFile;
import java.lang.NumberFormatException;
import java.time.LocalDate;
import java.util.Scanner;

public class Prodotto {

	static Scanner scanner = new Scanner(System.in);
	static public int id_prodotto,id_user,id_vendita;
    static String Disponibile = "No";
   
    //stampa prodotti
        public  static void readProdotti(){ 
          try{
			String fileName = "C://Users/Utente/Desktop/Desktop/java-project/data/prodotti.csv";
			String currentLine;
            BufferedReader br; 
            br = new BufferedReader(new FileReader(fileName));
			
            while ((currentLine = br.readLine()) != null) { 
            String[] str = currentLine.split(",");
			//formattazione per ragioni estetiche
			str[0] = String.format( "%-3s", str[0]);
			str[1] = String.format( "%-18s", str[1]);
			str[2] = String.format( "%-10s", str[2]);
			str[3] = String.format( "%-30s", str[3]);
			str[4] = String.format( "%-6s", str[4]);
				
			System.out.println((Utils.PURPLE) + str[0] + "|" + str[1] + "|" + str[2] + "|" + str[3] + "|" + str[4] + "|" + str[5]+Utils.RESET); 
			}
			br.close();
            System.out.println("\n");
		    }catch(IOException e){
			System.out.println(e);
		  }
			
        }
      
    //aggiorna file prodotti.csv
	public static void updateProduct(String id_prodotto){
        
		try {
			String Notavailable = "No";
			String available;
			String nameNumberString;
			String checkId;
			int index;
            File file = new File("C://Users/Utente/Desktop/Desktop/java-project/data/prodotti.csv");

			RandomAccessFile raf= new RandomAccessFile(file, "rw");
			boolean found = false;
    //lettura file
			while (raf.getFilePointer() < raf.length()) {
            nameNumberString = raf.readLine();
            String[] lineSplit = nameNumberString.split(",");
            checkId = lineSplit[0];
            available = lineSplit[5];
			if(checkId.equals(id_prodotto)&&available.equals("Si")) { 
			found = true;
			break;
				}
			if(checkId.equals(id_prodotto)&&available.equals(Notavailable)){
			System.out.println(Utils.RED+"Prodotto non disponibile."+Utils.RESET+"\n");
			found = false;
			break;
				}
			}

	// Aggiorna file
			if (found == true) {
    //Creazione file temporaneo
			File tmpFile = new File("C://Users/Utente/Desktop/Desktop/java-project/data/temp.csv");
			RandomAccessFile tmpraf= new RandomAccessFile(tmpFile, "rw");
            raf.seek(0);
            while (raf.getFilePointer()< raf.length()) {
            nameNumberString = raf.readLine();
            String[] lineSplit = nameNumberString.split(",");
			index = nameNumberString.indexOf(',');
			checkId = nameNumberString.substring(0, index);
            if (checkId.equals(id_prodotto)) {
    // Aggiorna prodotto e copia in temp.csv
			nameNumberString = checkId+","+lineSplit[1]+","+lineSplit[2]+","+lineSplit[3]+","+lineSplit[4]+","+ Notavailable;
			}
            tmpraf.writeBytes(nameNumberString);
            tmpraf.writeBytes(
			System.lineSeparator());
			}
            raf.seek(0);
			tmpraf.seek(0);
    //Copiare da temp.csv a prodotti.csv
			while (tmpraf.getFilePointer()< tmpraf.length()) {
			raf.writeBytes(tmpraf.readLine());
			raf.writeBytes(System.lineSeparator());
			}
            raf.setLength(tmpraf.length());
            tmpraf.close();
			raf.close();
    // Cancellazione file temporaneo
			tmpFile.delete();
            System.out.println(Utils.GREEN+"Prodotti.csv aggiornato"+"\n"+Utils.RESET);
			}
			else{
			raf.close();
			System.out.println(Utils.RED+"Prodotto non esiste o non è disponibile."+Utils.RESET);
			}
		    }
            catch (IOException ioe) {
			System.out.println(ioe);
		    }
            catch (NumberFormatException nef) {
			System.out.println(nef);
		    }
	        }
    //esporta file con prodotti disponibili
    public static void exportAvailable(){
            try {
		    LocalDate date = LocalDate.now();
	        String nameNumberString;
    //Inserire path qui!-------------------------------------------------------
			File file = new File("C://Users/Utente/Desktop/Desktop/java-project/data/prodotti-"+date+".csv");
			file.createNewFile();
			if(file.exists()){
			System.out.println(Utils.GREEN+"File creato"+Utils.RESET);
			}
			RandomAccessFile raf= new RandomAccessFile("C://Users/Utente/Desktop/Desktop/java-project/data/prodotti.csv", "rw");
			RandomAccessFile copy_raf= new RandomAccessFile(file, "rw");
    //inizializza nuovo file
			copy_raf.writeBytes("ID,Data inserimento,Marca,Nome,Prezzo,Disponibile");
			copy_raf.writeBytes(System.lineSeparator());
            while (raf.getFilePointer()< raf.length()) {
            nameNumberString = raf.readLine();
            String[] lineSplit = nameNumberString.split(",");
	//se prodotto disponibile aggiungi a file
			if (lineSplit[5].equals("Si")) {
            nameNumberString = lineSplit[0]+","+lineSplit[1]+","+lineSplit[2]+","+lineSplit[3]+","+lineSplit[4]+","+ lineSplit[5];
			copy_raf.writeBytes(nameNumberString);
            copy_raf.writeBytes(System.lineSeparator());
		    }
		    }		
			copy_raf.close();
			raf.close();
	        }catch (Exception e) {
		    System.out.println(e);
	        }

			}

    //verifica che prodotto esista
    public static boolean productIdexist(int id) throws IOException{

			String currentLine;
			BufferedReader br; 
			boolean exists = false;  
			br = new BufferedReader(new FileReader("C://Users/Utente/Desktop/Desktop/java-project/data/prodotti.csv"));
				
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
			System.out.println(Utils.RED+"Prodotto non esiste"+Utils.RESET+"\n");
			br.close();
			return false;
			}
		    }

	//comprare un prodotto		
	public static void compraProdotto(){
			System.out.print("User ID: ");
			id_user = scanner.nextInt();
			//verifica che utente sia registrato
			try {
			while(!Utente.userIdExists(id_user)){
			System.out.print("User ID: ");
			id_user = scanner.nextInt();
			}
			}catch (IOException e) {
			e.printStackTrace();
			}
			System.out.print("Product ID: ");
			id_prodotto = scanner.nextInt();
			//verifica che prodotto selezionato esista
			try {
			while(!Prodotto.productIdexist(id_prodotto)){
			System.out.print("Product ID: ");
			id_prodotto = scanner.nextInt();
			}
			}catch (IOException e) {
			e.printStackTrace();
			}
			//prodotto->Disponibile = No
			Prodotto.updateProduct(Integer.toString(id_prodotto));  
			//assegna id vendita
			try{
			id_vendita = Ordine.AssignOrderId();
			}catch (IOException e) {
			 e.printStackTrace();
			}
			//Aggiungi acquisto a vendite.csv
			Ordine.addOrderList(Integer.toString(id_prodotto),Integer.toString(id_user),Integer.toString(id_vendita));
		}

}


    

