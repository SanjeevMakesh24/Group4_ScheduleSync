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

    public boolean checkPassword(String pswd){
        return (pswd.length() >= 6 && pswd.length() <= 12);
    }

    public boolean checkUsername(String user){
        return (user.length() >= 6 && user.length() <= 15);
    }

    public boolean checkEmail(String email){
        //change to check list of valid email endings
        return email.contains("@email.com");
    }
}