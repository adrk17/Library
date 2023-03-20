package pl.edu.agh.kis.pz1.util;
import java.util.concurrent.Semaphore;
/**
 * Class representing library with limited number of readers and writers
 */
public class Library {
    // Max number of readers in library at the same time
    int maxResources;
    // Semaphore that that limits the number of people in the library, it lets in several readers at the same time
    // but only one writer, the writer takes all the places in the library
    Semaphore librarySemaphore;
    // Semaphore that manages the queue of readers and writers, it ensures that both readers and writers are served
    // in the order they came to the library
    Semaphore queueSemaphore;
    // Counter that tracks the number of free places in the library
    int currentResources;
    // Counter that tracks the number of threads that are waiting in the queue
    int numberOfThreadsInQueue = 0;
    // Queue to modify counters
    Semaphore mutex = new Semaphore(1, true);


    /**
     * Constructor of the library
     * @param _maxResources max number of readers in the library at the same time
     * it sets the maxResources to the given value and initializes the semaphores
     */
    public Library(int _maxResources) {
        librarySemaphore = new Semaphore(_maxResources, true);
        queueSemaphore = new Semaphore(1, true);
        this.maxResources = _maxResources;
        this.currentResources = _maxResources;
        Logger.log("Library capacity ["+currentResources+"/"+maxResources+"]", ConsoleColors.YELLOW);
    }

    /**
     * Method that lets a reader/writer enter the library, it first puts them in a line and then lets them in one by one
     * in the order they came to the library as well as lets them in to the library if there still are free places
     * if the thread goes in to the library it also releases the queueSemaphore so that the next thread can be 1st in line to get in
     * @param idTuple id of the thread that wants to enter the library
     * @param resources number of places that the thread wants to take in the library
     */
    public void enterLibrary( IdTuple idTuple, int resources){
        try {
            // Update the queue length counter and log the message
            mutex.acquire();
            Logger.log("| + | Queue length: " + ++numberOfThreadsInQueue , ConsoleColors.PURPLE);
            mutex.release();

            queueSemaphore.acquire();
            librarySemaphore.acquire(resources);
            queueSemaphore.release();

            mutex.acquire();
            currentResources -= resources;
            String message = ConsoleColors.GREEN+"\n| + | "+ idTuple + " entered library"+ConsoleColors.RESET+ "\n"+
                    ConsoleColors.PURPLE+ "| - | Queue length: " + --numberOfThreadsInQueue + ", "+ idTuple + " left the queue" + ConsoleColors.RESET+  "\n"+
                    ConsoleColors.YELLOW + "From thread: ["+idTuple+"], Library capacity ["+currentResources+"/"+maxResources+"]" + ConsoleColors.RESET + "\n";
            Logger.log(message);
            mutex.release();

        } catch (InterruptedException e) {
            e.printStackTrace();
            Logger.log("InterruptedException: " + e);
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Method that lets a reader/writer exit the library, it also releases the librarySemaphore so that the next thread can enter the library
     * @param idTuple id of the thread that wants to exit the library
     * @param resources number of places that the thread wants to release in the library, it is always 1 for readers and maxResources for writers
     */
    public void exitLibrary(IdTuple idTuple, int resources) throws InterruptedException {
        mutex.acquire();
        currentResources += resources;
        String message = ConsoleColors.RED +"\n | - | "+idTuple + " exits the library" + "\n" + ConsoleColors.RESET +
                ConsoleColors.YELLOW + "From thread: ["+idTuple+"], Library capacity ["+currentResources+"/"+maxResources+"]" + ConsoleColors.RESET + "\n";
        Logger.log(message);
        librarySemaphore.release(resources);
        mutex.release();
    }
}
