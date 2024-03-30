package data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.Builder;

@Document(collection = "users")
@Data
@Builder
public class User {

    @Id
    private String userID;

    private String email;
    private String password;
    private String username;
    private String name;

    public User (String userID, String password, String email, String username, String name){
        this.userID = userID;
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
}