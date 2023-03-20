package pl.edu.agh.kis.pz1.util;

/**
 * Class representing a tuple of id and type of a thread (Reader or Writer)
 */
public class IdTuple {
    // Id of a thread
    private final int id;
    // Type of a thread
    private final String type;

    /**
     * Constructor of IdTuple
     * @param _id Id of a thread
     * @param _type Type of a thread
     */
    IdTuple(int _id, String _type) {
        id = _id;
        type = _type;
    }

    /**
     * Getter of id
     * @return Id of a thread
     */
    public int getId() {
        return id;
    }

    /**
     * Overriden toString method that returns a string representation of IdTuple where both id and type are included
     * @return String representation of IdTuple
     */
    @Override
    public String toString() {
        return type + " " + id;
    }
}
