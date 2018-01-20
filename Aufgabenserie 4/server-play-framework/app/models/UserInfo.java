package models;

import io.ebean.Finder;
import io.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class UserInfo extends Model {
    @Id
    public UUID uuid;
    @Constraints.Required
    public String username;
    @Constraints.Required
    public String password;

    public static Finder<Integer, UserInfo> finder = new Finder<>(UserInfo.class);

    /**
     * Finds the first user with matching name
     * @param username The username to find in the DB
     * @return If a user is found, the UserInfo else null
     */
    public static UserInfo findUserByName(String username) {
        for (UserInfo user : finder.all()) {
            if (user.username.equals(username))
                return user;
        }
        return null;
    }

    /**
     * Boolean whether or not the given username is already used
     * @param username The username to find in the DB
     * @return True if someone uses this name already, otherwise false
     */
    public static boolean isUserNameUsed(String username) {
        return finder.all().stream().anyMatch(userInfo -> userInfo.username.equals(username));
    }

    /**
     * Returns true if username and password are valid credentials.
     *
     * @param username The username.
     * @param password The password.
     * @return True if username is a valid user email and password is valid for that username.
     */
    public static boolean isValid(String username, String password) {
        if ((username != null) && (password != null)) {
            UserInfo user = findUserByName(username);
            if (user != null) {
                return user.password.equals(password);
            }
        }
        return false;
    }
}
