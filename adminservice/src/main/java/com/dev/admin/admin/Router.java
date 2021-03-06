package com.dev.admin.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class Router {
    
    @Autowired
    UserRepository userRepository;
    
    @PostMapping(value="/user")
    public String create(@RequestBody final User rUser){
        try{
            if(rUser.getRoles().isEmpty()){
                return "User Roles Missing";    
            }
            else{
                userRepository.save(rUser);
                return "Added";
            }
        } catch (final Exception e) {
            return "Already Exists or Insufficient Data";
        }
    }

    @GetMapping(value = "/user")
    public List<User> read(){
        try{
            return userRepository.findAll();
        }
        catch(Exception e){
           User exception=new User("this", "is", e.toString(),Long.valueOf(101),true,null);
           List<User> except= List.of(exception);
           return except;
        }
    }

    @PutMapping(value = "/user")
    public String update(@RequestBody final User rUser) {
        try {
            final Optional<User> lUser = userRepository.findByUsername(rUser.getUsername());
            if (lUser.isEmpty()) {
                return "User does not exist";
            } else {
                if(rUser.getRoles().isEmpty()){
                    return "User Roles Missing";
                }
                else{
                    (lUser.get()).setPassword(rUser.getPassword());
                    (lUser.get()).setPhone(rUser.getPhone());
                    (lUser.get()).setName(rUser.getName());
                    (lUser.get()).setRoles(rUser.getRoles());
                    (lUser.get()).setValid(rUser.getValid());
                    userRepository.save(lUser.get());
                    return "Updated";    
                }
            }
        } catch (final Exception e) {
            return e.toString();
        }
    }

    @DeleteMapping(value = "/user/{rUsername}")
    public String delete(@PathVariable("rUsername") String rUsername) {
        try {
            final Optional<User> lUser = userRepository.findByUsername(rUsername);
            if (lUser.isEmpty()) {
                return "Does not exist";
            } else {
                userRepository.delete(lUser.get());
                return "Deleted";
            }
        } catch (final Exception e) {
            return e.toString();
        }
    }
    
}