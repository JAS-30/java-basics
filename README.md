# java-basics
Creating an ecommerce app for a vegan company
# Italiano:
## Descrizione progetto:
Creazione di un'app ecommerce per un'azienda di prodotti bio.
Per il progetto viene usato esclusivamente java con l'aggiunta di file .csv nella cartella **data** per simulare il database dell'azienda.
L'utente potrà interagire con l'applicazione inserendo un opportuno input nel terminal:
- 0 -> Uscire dal programma
- 1 -> Visualizzare i prodotti
- 2 -> Comprare un prodotto
- 3 -> Visualizzare un ordine
- 4 -> Aggiungere un cliente nel sistema
- 5 -> Creare un file con i prodotti disponibili
  Nota: Una volta comprato un prodotto, non sarà più disponibile.

## Come provare l'applicazione:
1)
Clona la repository in locale.
Nei file nella cartella **src/Services** aggiorna il PATH in cui appare scritto "INPUT_PATH**********" con il proprio PATH.
2)
Compila i file:
Nel terminal raggiungi la cartella **java-project/src** e inserisci:
- javac Main/Application.java
- Se i file in **Services** non vengono compilati in automatico inserisci -> javac Services/*.java
Esegui i file:
Inserisci: java Main/Application
