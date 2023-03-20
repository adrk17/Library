package pl.edu.agh.kis.pz1.util;


import org.junit.jupiter.api.Assertions;

class LibraryTest {

    @org.junit.jupiter.api.Test
    void constructorTest(){
        Library library = new Library(5);
        Assertions.assertNotNull(library, "Library object created.");
        Assertions.assertNotNull(library.librarySemaphore, "Library semaphore created.");
        Assertions.assertNotNull(library.queueSemaphore, "Queue semaphore created.");
        Assertions.assertEquals(5, library.maxResources);
        Assertions.assertEquals(5, library.currentResources);
        Assertions.assertEquals(0, library.numberOfThreadsInQueue);
    }

    @org.junit.jupiter.api.Test
    void enterLibraryWriter5EmptySemaphores() {
        Library library = new Library(5);
        library.enterLibrary(new IdTuple(2, "Writer"), 5);
        Assertions.assertEquals(0, library.currentResources);
        Assertions.assertEquals(0, library.numberOfThreadsInQueue);
    }

    @org.junit.jupiter.api.Test
    void threadsEnterAndExitLibraryWriterReader() throws InterruptedException {
        Library library = new Library(5);
        library.enterLibrary(new IdTuple(2, "Writer"), 5);
        library.exitLibrary(new IdTuple(2, "Writer"), 5);
        library.enterLibrary(new IdTuple(3, "Reader"), 1);
        Assertions.assertEquals(4, library.currentResources);
        Assertions.assertEquals(0, library.numberOfThreadsInQueue);
    }

    @org.junit.jupiter.api.Test
    void exitLibrary3Writers() throws InterruptedException {
        Library library = new Library(5);
        library.enterLibrary(new IdTuple(2, "Writer"), 5);
        library.exitLibrary(new IdTuple(2, "Writer"), 5);
        library.enterLibrary(new IdTuple(3, "Writer"), 5);
        library.exitLibrary(new IdTuple(3, "Writer"), 5);
        library.enterLibrary(new IdTuple(4, "Writer"), 5);
        library.exitLibrary(new IdTuple(4, "Writer"), 5);
        Assertions.assertEquals(5, library.currentResources);
        Assertions.assertEquals(0, library.numberOfThreadsInQueue);
    }

}