package pl.edu.agh.kis.pz1.util;
/**
 * Class representing a Writer thread that enters and exits the library
 */
public class Writer extends Thread{
    // Time that a Writer thread waits between actions in the library
    final int waitTime = 3000;
    // Library that a Writer thread enters and exits
    Library library;
    // IdTuple that contains id and type of a Writer thread
    IdTuple idTuple;
    int loopCounter = 0;
    /**
     * Constructor of Writer
     * @param _library Library that a Writer thread enters and exits
     * @param _id Id of a Writer thread
     */
    public Writer(Library _library, int _id) {
        library = _library;
        idTuple = new IdTuple(_id, "Writer");
    }

    /**
     * Overriden run method that represents a Writer thread's actions in the library
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
     * Method that represents a Writer thread's action of entering the library
     * It logs a message and calls enterLibrary method of a Library object
     * @throws InterruptedException Thrown when a thread is interrupted
     */
    public void enterLibrary() throws InterruptedException {
        Logger.log("[Writer " + idTuple.getId() + "]: I' going to the library!", ConsoleColors.CYAN);
        library.enterLibrary( idTuple, 5);
    }
    /**
     * Method that represents a Writer thread's action of exiting the library
     * It logs a message and calls exitLibrary method of a Library object
     * @throws InterruptedException Thrown when a thread is interrupted
     */
    public void exitLibrary() throws InterruptedException {
        library.exitLibrary( idTuple, 5);
    }
}
