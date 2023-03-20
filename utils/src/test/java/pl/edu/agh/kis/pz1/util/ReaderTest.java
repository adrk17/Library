package pl.edu.agh.kis.pz1.util;


import org.junit.jupiter.api.Assertions;

class ReaderTest {
    @org.junit.jupiter.api.Test
    void constructorTest() {
        Reader reader = new Reader(new Library(5), 1);
        org.junit.jupiter.api.Assertions.assertNotNull(reader);
    }

    @org.junit.jupiter.api.Test
    void runTest2ReadersIsAlive() {
        Library library = new Library(5);
        Reader reader = new Reader(library, 1);
        Reader reader2 = new Reader(library, 2);
        reader.start();
        reader2.start();
        try {
            reader.join(1000);
            Assertions.assertTrue(reader.isAlive());
            reader2.join(1000);
            Assertions.assertTrue(reader2.isAlive());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    void runTest6ReadersNoQueueAndFullResources() {
        Library library = new Library(5);
        Reader reader = new Reader(library, 1);
        Reader reader2 = new Reader(library, 2);
        Reader reader3 = new Reader(library, 3);
        Reader reader4 = new Reader(library, 4);
        Reader reader5 = new Reader(library, 5);
        Reader reader6 = new Reader(library, 6);
        reader.start();
        reader2.start();
        reader3.start();
        reader4.start();
        reader5.start();
        reader6.start();
        try {
            reader.join();
            reader2.join();
            reader3.join();
            reader4.join();
            reader5.join();
            reader6.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(0, library.numberOfThreadsInQueue);
        Assertions.assertEquals(library.maxResources, library.currentResources);
    }

    @org.junit.jupiter.api.Test
    void exceptionTest(){
        Library library = new Library(5);
        Reader reader = new Reader(library, 1);
        reader.start();
        try {
            reader.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assertions.assertThrows(IllegalThreadStateException.class, () -> reader.start());
    }
}