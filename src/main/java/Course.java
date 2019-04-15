import java.util.List;

public class Course {
    private String id;
    private String name;
    private List<String> instructors;
    private int capacity = 0;
    private transient int secretValue = 0;

    public Course(String id, String name, List<String> instructors, int capacity) {
        this.id = id;
        this.name = name;
        this.instructors = instructors;
        this.capacity = capacity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getInstructors() {
        return instructors;
    }


    public void setInstructors(List<String> instructors) {
        this.instructors = instructors;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getSecretValue() {
        return secretValue;
    }

    public void setSecretValue(int secretValue) {
        this.secretValue = secretValue;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", instructors=" + instructors +
                ", capacity=" + capacity +
                ", secretValue=" + secretValue +
                '}';
    }
}
