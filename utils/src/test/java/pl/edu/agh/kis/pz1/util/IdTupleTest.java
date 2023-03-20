package pl.edu.agh.kis.pz1.util;


import org.junit.jupiter.api.Assertions;

class IdTupleTest {
    @org.junit.jupiter.api.Test
    void combinedTest() {
        IdTuple idTuple = new IdTuple(1, "Reader");
        Assertions.assertEquals(1, idTuple.getId());
        Assertions.assertEquals("Reader 1", idTuple.toString());
    }

    @org.junit.jupiter.api.Test
    void getIdShouldBe2() {
        IdTuple idTuple = new IdTuple(2, "Reader");
        org.junit.jupiter.api.Assertions.assertEquals(2, idTuple.getId());
    }

    @org.junit.jupiter.api.Test
    void testToStringShouldBeWriter5() {
        IdTuple idTuple = new IdTuple(5, "Writer");
        org.junit.jupiter.api.Assertions.assertEquals("Writer 5", idTuple.toString());
    }
}