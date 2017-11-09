

public class TRLApp {

	public static Controller TRLController = new Controller();
	
	
	private static void welcomeMessage() {
		StdOut.println("Welcome to the TRL");
	}
	
	private static String promptForWorkerIDAndLogin() {
		String workerID;

		StdOut.print("Please enter your WorkerID: ");
		workerID = StdIn.readString();

		while (!TRLController.validateAndLoginWorker(workerID)) {
			StdOut.println("The worker ID of: " + workerID + " is not valid.");
			StdOut.print("Please enter your WorkerID: ");
			workerID = StdIn.readString();
		}

		StdOut.println(workerID + " was successfully logged in.");
		
		return workerID;
	}

	private static String promptForPatronIDandSetPatron() {
		String patronID;

		StdOut.print("Please enter the PatronID: ");
		patronID = StdIn.readString();

		while (!TRLController.validateAndSetPatron(patronID)) {
			StdOut.println("The patron ID of: " + patronID + " is not valid.");
			StdOut.print("Please enter the PatronID: ");
			patronID = StdIn.readString();
		}

		return patronID;
	}

	private static String promptForAndSetTransactionType() {
		
		String transactionType;

		StdOut.print("Please enter the transaction type (out/in): ");
		transactionType = StdIn.readString();

		while (!TRLController.setTransactionType(transactionType)) {
			StdOut.println("The transaction type of: " + transactionType + " is not valid.");
			StdOut.print("Please enter the transaction type (out/in): ");
			transactionType = StdIn.readString();
		}

		return transactionType;	
		
	}
	
	public static void main(String[] args) {
		String workerID;
		String patronID;
		String transactionType;
		
		welcomeMessage();
		
		workerID = promptForWorkerIDAndLogin();

		patronID = promptForPatronIDandSetPatron();

		transactionType = promptForAndSetTransactionType();
		
	}

}
