

public class TRLApp {

	public static Controller TRLController = new Controller();
	
	public static void main(String[] args) {
		String workerID;
		
		StdOut.println("Welcome to the TRL");
		
		StdOut.print("Please enter your WorkerID: ");
		workerID = StdIn.readString();

		while (!TRLController.validateAndLoginWorker(workerID)) {
			StdOut.println("The worker ID of: " + workerID + " is not valid.");
			StdOut.print("Please enter your WorkerID: ");
			workerID = StdIn.readString();
		}

		StdOut.println(workerID + " was successfully logged in.");
		
	}

}
