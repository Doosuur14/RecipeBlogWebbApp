package Models;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@AllArgsConstructor

public class User {
    private long id;
    private UUID uuidOfUser;
    private String firstName;
    private String lastName;
    private String username;
    private String  password;
    private String role;
    private UserProfile userProfile;

    public User() {
        this.userProfile = (userProfile != null) ? userProfile : new UserProfile();
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UUID getUuidOfUser() {
        return uuidOfUser;
    }

    public void setUuidOfUser(UUID uuidOfUser) {
        this.uuidOfUser = uuidOfUser;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
}
