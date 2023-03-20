package pl.edu.agh.kis.pz1;


import org.junit.jupiter.api.Assertions;


public class MainTest {
    /**
     * Test for the construction of Library object and threads
     */
    @org.junit.jupiter.api.Test
    public void shouldCallTheMainMethod(){
        Main main = new Main();
        Assertions.assertNotNull(main, "Main method called.");
    }

}


