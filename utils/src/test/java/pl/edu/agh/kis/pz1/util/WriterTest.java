package pl.edu.agh.kis.pz1.util;

import org.junit.jupiter.api.Assertions;

class WriterTest {
    @org.junit.jupiter.api.Test
    void constructorTest() {
        Writer writer = new Writer(new Library(5), 1);
        org.junit.jupiter.api.Assertions.assertNotNull(writer);
    }

    @org.junit.jupiter.api.Test
    void runTest2WritersIsAlive() {
        Library library = new Library(5);
        Writer writer = new Writer(library, 1);
        Writer writer2 = new Writer(library, 2);
        writer.start();
        writer2.start();
        try {
            writer.join(1000);
            Assertions.assertTrue(writer.isAlive());
            writer2.join(1000);
            Assertions.assertTrue(writer2.isAlive());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    void runTest3WritersNoQueueAndFullResources() {
        Library library = new Library(5);
        Writer writer = new Writer(library, 1);
        Writer writer2 = new Writer(library, 2);
        Writer writer3 = new Writer(library, 3);
        writer.start();
        writer2.start();
        writer3.start();
        try {
            writer.join();
            writer2.join();
            writer3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(0, library.numberOfThreadsInQueue);
        Assertions.assertEquals(library.maxResources, library.currentResources);
    }

    @org.junit.jupiter.api.Test
    void exceptionTest(){
        Library library = new Library(5);
        Writer writer = new Writer(library, 1);
        writer.start();
        try {
            writer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assertions.assertThrows(IllegalThreadStateException.class, () -> writer.start());
    }
}

