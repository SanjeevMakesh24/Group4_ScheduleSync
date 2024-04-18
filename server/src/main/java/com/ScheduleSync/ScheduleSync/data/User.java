package com.ScheduleSync.ScheduleSync.data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Document(collection = "user")
@Data
//@Builder
public class User {

    @Id
    private String username;
    private String email;
    private String password;
    private String name;

    @Setter
    @Getter
    private List<String> friends; // List of friends
    @Getter
    @Setter
    private Schedule schedule; // User's personal schedule
    @Setter
    @Getter
    private Set<String> groups; // Groups the user is part of


    public User (String email, String username, String name, String password){
        this.name = name;

        if(checkPassword(password)){
            this.password = password;
        } else {this.password = null;}

        if(checkUsername(username)){
            this.username = username;
        } else {this.username = null;}

        if(checkEmail(email)){
            this.email = email;
        } else {this.email= null;}

        this.friends = new ArrayList<>();
        this.schedule = new Schedule();
        this.groups = new HashSet<>();
    }

    //checking the password requirements
    public boolean checkPassword(String pswd) {
        //regex to check for at least one number.
        boolean hasNumber = pswd.matches(".*[0-9].*");
        //regex to check for at least one special character.
        boolean hasSpecial = pswd.matches(".*[^a-zA-Z0-9].*");

        return hasNumber && hasSpecial && (pswd.length() >= 6 && pswd.length() <= 12);
    }


    //checking the username requirements
    public boolean checkUsername(String user){
        if (user == null || user.isEmpty()) {
            return false;
        }
        char firstChar = user.charAt(0);
        boolean isFirstCharValid = Character.isLetter(firstChar);

        return isFirstCharValid && (user.length() >= 5 && user.length() <= 15);
    }


    public boolean checkEmail(String email){
        //change to check list of valid email endings
        return email.contains("@email.com");
    }

    public void addFriend(String friend) {
        this.friends.add(friend);
    }

    public void removeFriend(String friend) {
        this.friends.remove(friend);
    }

    public void addGroup(String group) {
        this.groups.add(group);
    }

    public void removeGroup(Group group) {
        this.groups.remove(group);
    }

}