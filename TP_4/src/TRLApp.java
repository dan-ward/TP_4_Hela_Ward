

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

	private static void checkoutCopy() {
		
		String copyID;
		
		StdOut.print("Please enter the copy ID: ");
		copyID = StdIn.readString();

		while (!TRLController.validateAndCheckOutCopy(copyID)) {
			StdOut.println("The copy ID of: " + copyID + " is not valid.");
			StdOut.print("Please enter a valid copy ID: ");
			copyID = StdIn.readString();
		}

		StdOut.println("Copy " + copyID + " was successfully added to the checkout queue");	
		
	}

	private static void checkoutCopies() {
		String checkOutAnother = "y";
		while (checkOutAnother.equalsIgnoreCase("y")) {
			checkoutCopy();
			StdOut.print("Would you like to checkout another y/n? ");
			checkOutAnother = StdIn.readString();
		}
		
		try {
			TRLController.completeSession();
			StdOut.println("All of your copies have been checked out.  Thank you!");
		} catch (HoldException e) {
			StdOut.println("Sorry your copies failed to checkout, please try again later.");
		}
	}
	
	public static void main(String[] args) {
		String workerID;
		String patronID;
		String transactionType;
		
		welcomeMessage();
		
		workerID = promptForWorkerIDAndLogin();

		patronID = promptForPatronIDandSetPatron();

		transactionType = promptForAndSetTransactionType();
		
		if (transactionType.equals("out")) {
			checkoutCopies();
		} else {
			StdOut.println("Sorry you entered an invalid transaction type");
		}
			
		 
	}

}
