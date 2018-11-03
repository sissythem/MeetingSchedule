package gr.demokritos.meetingscheduler.datalayer.repositories;

import gr.demokritos.meetingscheduler.datalayer.persistence.entities.User;
import gr.demokritos.meetingscheduler.datalayer.utils.DbConstants;
import org.apache.commons.collections4.CollectionUtils;

import javax.enterprise.context.Dependent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JpaRepo
@Dependent
public class UserRepository extends AbstractRepository<User> {

    public UserRepository() {
        super(DbConstants.USER_TABLE);
    }

    public List<User> findAllUsers() {
        return namedQuery(DbConstants.USER_FIND_ALL, null);
    }

    public List<User> findAllUsers(String sortString) {
        return jpqlQuery("SELECT u FROM User u " + sortString);
    }

    public User findMemberById(Long id) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", id);
        List<User> users = namedQuery(DbConstants.USER_FIND_BY_ID, parameters);
        if (!CollectionUtils.isEmpty(users)) {
            return users.get(0);
        }
        return null;
    }

    public List<User> findUserByName(String name) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", name);
        return namedQuery(DbConstants.USER_FIND_BY_NAME, parameters);
    }

    public List<User> findUserByLastName(String lastName) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("lastName", lastName);
        return namedQuery(DbConstants.USER_FIND_BY_LAST_NAME, parameters);
    }

    public User findMemberByUsername(String username) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("username", username);
        List<User> users = namedQuery(DbConstants.USER_FIND_BY_USERNAME, parameters);
        if (!CollectionUtils.isEmpty(users)) {
            return users.get(0);
        }
        return null;
    }

    public User findMemberByEmail(String email) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("email", email);
        List<User> users = namedQuery(DbConstants.USER_FIND_BY_EMAIL, parameters);
        if (!CollectionUtils.isEmpty(users)) {
            return users.get(0);
        }
        return null;
    }
}
