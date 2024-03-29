package service;

import data.User;
import data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    public void save(User user){
        userRepo.save(user);
    }


    public void deleteUser(String ID){
        userRepo.deleteById(ID);
    }

    public User getUserByID(String userID){
        return userRepo.findById(userID).get();
    }

    public Iterable<User> getAllUsers(){
        return this.userRepo.findAll();
    }

}