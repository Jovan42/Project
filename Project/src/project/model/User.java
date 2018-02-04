package project.model;

import java.util.Date;
import java.util.List;

/**
 * Created by jovan on 30-Jan-18.
 */
public class User {
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String eMail;
    private String description;
    private Date created;
    private boolean admin;
    private boolean banned;
    private List<String> followersID;
    private List<Integer> commentRatesID;
    private List<Integer> videoRatesID;
    private  boolean deleted;

    public User(String userName, String password, String firstName, String lastName, String eMail, String description,
                Date created, boolean admin, boolean banned, List<String> followersID, List<Integer> commentRatesID,
                List<Integer> videoRatesID, boolean deleted) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.eMail = eMail;
        this.description = description;
        this.created = created;
        this.admin = admin;
        this.banned = banned;
        this.followersID = followersID;
        this.commentRatesID = commentRatesID;
        this.videoRatesID = videoRatesID;
        this.deleted = deleted;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public List<String> getFollowersID() {
        return followersID;
    }

    public void setFollowersID(List<String> followersID) {
        this.followersID = followersID;
    }

    public List<Integer> getCommentRatesID() {
        return commentRatesID;
    }

    public void setCommentRatesID(List<Integer> commentRatesID) {
        this.commentRatesID = commentRatesID;
    }

    public List<Integer> getVideoRatesID() {
        return videoRatesID;
    }

    public void setVideoRatesID(List<Integer> videoRatesID) {
        this.videoRatesID = videoRatesID;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "userName: " + userName + '\n' + "password: " + password + '\n' + "firstName: " + firstName +
                '\n' + "lastName: " + lastName;
    }
}
