import java.util.*;

/**
 * Represents the type/category of a project in an academic context.
 * Projects can be either theoretical (research-oriented) or practical (application-oriented).
 */
enum ProjectType {
    /** Theoretical project focused on research, analysis, or conceptual work. */
    THEORETICAL,

    /** Practical project involving hands-on implementation or applied work. */
    PRACTICAL
}

/**
 * Represents an academic project with a unique identifier and specific type.
 * Projects are immutable in their type but can have their ID modified.
 */
public class Project {
    private int id;
    private ProjectType type;

    public Project(int id) {
        this.id = id;
    }

    /**
     * Gets the project's unique identifier.
     *
     * @return the project ID as an integer
     */
    public int getId() {
        return id;
    }

    /**
     * Updates the project's identifier.
     *
     * @param id the new ID to assign to the project
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the immutable project type (THEORETICAL or PRACTICAL).
     *
     * @return the project's category
     * @see ProjectType
     */
    public ProjectType getType() {
        return type;
    }

    /**
     * Returns a string representation of the project in the format:
     * {@code Project{id=ID, type=TYPE}}
     *
     * @return a string containing the project's ID and type
     */
    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", type=" + type +
                '}';
    }
}