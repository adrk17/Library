package pl.edu.agh.kis.pz1;

import pl.edu.agh.kis.pz1.util.Library;
import pl.edu.agh.kis.pz1.util.Reader;
import pl.edu.agh.kis.pz1.util.Writer;

/**
 * Main class of the program which creates a Library object and Reader and Writer threads
 */
public class Main {
    public static void main( String[] args ) {
        Library library = new Library(5);
        int numOfReaders = 10;
        int numOfWriters = 3;
        for (int i = 0; i < numOfReaders/2; i++) {
            Reader reader = new Reader(library, i);
            reader.start();
        }
        for (int i = 0; i < numOfWriters; i++) {
            Writer writer = new Writer(library, i);
            writer.start();
        }
        for (int i = 5; i < numOfReaders; i++) {
            Reader reader = new Reader(library, i);
            reader.start();
        }
    }
}
