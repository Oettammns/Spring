public class User {
    private static User INSTANCE;
    private long id;
    private String firstName;
    private String lastName;
    private int age;

    private User() {
    }

    public static User getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new User();
        }

        return INSTANCE;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
