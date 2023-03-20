package pl.edu.agh.kis.pz1.util;
/**
 * Class representing a Reader thread that enters and exits the library
 */
public class Reader extends Thread{
    // Time that a Reader thread waits between actions in the library
    final int waitTime = 3000;
    // Library that a Reader thread enters and exits
    Library library;
    // IdTuple that contains id and type of a Reader thread
    IdTuple idTuple;
    int loopCounter = 0;
    /**
     * Constructor of Reader
     * @param _library Library that a Reader thread enters and exits
     * @param _id Id of a Reader thread
     */
    public Reader(Library _library, int _id) {
        library = _library;
        idTuple = new IdTuple(_id, "Reader");
    }
    /**
     * Overriden run method that represents a Reader thread's actions in the library
     * It is a loop that enters the library, waits for a while and exits the library
     */
    @Override
    public void run() {
        while (loopCounter < 2){
            try {
                enterLibrary();
                sleep(waitTime);
                exitLibrary();
                sleep(waitTime);
                loopCounter++;
            } catch (InterruptedException e) {
                e.printStackTrace();
                Logger.log("InterruptedException: " + e);
                Thread.currentThread().interrupt();
            }
        }

    }

    /**
     * Method that represents a Reader thread's action of entering the library
     * It logs a message and calls enterLibrary method of a Library object
     * @throws InterruptedException Thrown when a thread is interrupted
     */
    private void enterLibrary() throws InterruptedException {
        Logger.log("[Reader " + idTuple.getId() + "]: I'm going to the library!");
        library.enterLibrary(idTuple, 1);
    }

    /**
     * Method that represents a Reader thread's action of exiting the library
     * It logs a message and calls exitLibrary method of a Library object
     * @throws InterruptedException Thrown when a thread is interrupted
     */
    private void exitLibrary() throws InterruptedException {
        library.exitLibrary( idTuple, 1);
    }
}
