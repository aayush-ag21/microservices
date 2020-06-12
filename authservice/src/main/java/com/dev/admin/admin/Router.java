package com.dev.admin.admin;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

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
            /*
            EntityManager em = UserRepository.createEntityManager();
            em.getTransaction().begin();
            
            Author a = new Author();
            a.setFirstName("Thorben");
            a.setLastName("Janssen");
            a.setCategory(Category.NON_FICTION);
            a.setPseudonym("Thorben Janssen");
            em.persist(a);
            
            em.getTransaction().commit();
            em.close();
            */
            userRepository.save(rUser);
            return "Added";
        }catch (final Exception e){
            return e.toString();
            //return "Already Exists or Incorrect Data";
        }
    }
    
    @GetMapping(value = "/user")
    public List<User> read() {
        return userRepository.findAll();
    }
    
    @PutMapping(value = "/user")
    public String update(@RequestBody final User rUser){
        try {
            final Optional<User> lUser = userRepository.findByUsername(rUser.getUsername());
            if (lUser.isEmpty()) {
                return "User does not exist";
            } else {
                lUser.get().setRoles(rUser.getRoles());
                lUser.get().setValid(rUser.getValid());
                return "Updated";
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