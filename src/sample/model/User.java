package sample.model;

/**This class creates User objects for use within the application*/
public class User {
    private int userId;
    private String userName;
    private String password;

    /**Constructor for User objects
     * @param userId
     * @param userName
     * @param password */
    public User(int userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

    /**This method returns userId
     * @return userId*/
    public int getUserId() {
        return userId;
    }

    /**This method return userName
     * @return userName*/
    public String getUserName() {
        return userName;
    }

    /**This method returns password
     * @return password*/
    public String getPassword() {
        return password;
    }

    /**This method overrides User objects into strings so they can be used in combo boxes
     * @return User as userName string*/
    @Override
    public String toString() {
        return (userName);
    }
}
