package controller;

import data.User;
import service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/create-account")

public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value="/save")
    private String saveUser(@RequestBody User user){
        userService.save(user);
        return user.getUserID();
    }

    @GetMapping(value="/getAll")
    public Iterable<User> getUsers(){
        return userService.getAllUsers();
    }

    @DeleteMapping("/delete/{ID}")
    private void deleteUser(@PathVariable("ID") String userID){
        userService.deleteUser(userID);
    }

    @RequestMapping("/search/{id}")
    private User getUser(@PathVariable(name="ID") String userID){
        return userService.getUserByID(userID);
    }


}