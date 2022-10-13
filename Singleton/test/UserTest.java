import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    User user = User.getInstance();
    @Before
    public void setUp() throws Exception {
        user.setId(1);
        user.setAge(22);
        user.setFirstName("Matteo");
        user.setLastName("Mansi");
    }

    @Test
    public void getId() {
        assertNotNull(user.getId());
    }

    @Test
    public void setId() {
        long old_id = user.getId();
        user.setId(2);
        assertNotEquals(user.getId(),old_id);
    }

    @Test
    public void getFirstName() {
        assertNotNull(user.getFirstName());
    }

    @Test
    public void setFirstName() {
        String old_name = user.getFirstName();
        user.setFirstName("Mario");
        assertNotEquals(user.getFirstName(),old_name);
    }

    @Test
    public void getAge() {
        assertNotNull(user.getAge());
    }

    @Test
    public void setAge() {
        int old_age = user.getAge();
        user.setAge(old_age+1);
        assertNotEquals(user.getAge(),old_age);
    }
}