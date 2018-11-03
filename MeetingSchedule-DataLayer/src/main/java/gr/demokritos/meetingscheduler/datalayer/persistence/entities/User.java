package gr.demokritos.meetingscheduler.datalayer.persistence.entities;

import gr.demokritos.meetingscheduler.datalayer.utils.DbConstants;

import javax.enterprise.context.SessionScoped;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@SessionScoped
@Entity
@Table(name = DbConstants.USER_TABLE)
@NamedQueries({
        @NamedQuery(name = DbConstants.USER_FIND_ALL, query = "SELECT u FROM User u"),
        @NamedQuery(name = DbConstants.USER_FIND_BY_ID, query = "SELECT u FROM User u WHERE u.id = :id"),
        @NamedQuery(name = DbConstants.USER_FIND_BY_NAME, query = "SELECT u FROM User u WHERE u.name = :name"),
        @NamedQuery(name = DbConstants.USER_FIND_BY_LAST_NAME, query = "SELECT u FROM User u WHERE u.lastName = :lastName"),
        @NamedQuery(name = DbConstants.USER_FIND_BY_USERNAME, query = "SELECT u FROM User u WHERE u.username = :username"),
        @NamedQuery(name = DbConstants.USER_FIND_BY_EMAIL, query = "SELECT u FROM User u WHERE u.email = :email")
})
public class User extends DBEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Size(max = 255)
    @Column(name = "name")
    private String name;
    @Size(max = 255)
    @Column(name = "last_name")
    private String lastName;
    @Size(max = 255)
    @Column(name = "username")
    private String username;
    @Size(max = 255)
    @Column(name = "password")
    private String password;
    @Size(max = 255)
    @Column(name = "email")
    private String email;

    public User() {

    }

    public User(@Size(max = 255) String name, @Size(max = 255) String lastName, @Size(max = 255) String username, @Size(max = 255) String password, @Size(max = 255) String email) {
        this.name = name;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
