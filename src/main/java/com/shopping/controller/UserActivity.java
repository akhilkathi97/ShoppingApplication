package com.shopping.controller;

import com.shopping.model.UserDetails;
import com.shopping.repository.UserDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user")
public class UserActivity {

    @Autowired
    private UserDetailsRepo userDetailsRepo;

    @GetMapping("/all")
    public ResponseEntity<UserDetails> getAllUsers(){
        List<UserDetails> userDetails = userDetailsRepo.findAll();
        return new ResponseEntity(userDetails,HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity getByUserId(@PathVariable Long userId){
        Optional<UserDetails> userDetails = userDetailsRepo.findByUserId(userId);
        if(userDetails.isPresent()){
            return new ResponseEntity(userDetails,HttpStatus.OK);
        }
        else{
            return new ResponseEntity("No User with ID: "+userId.toString(),HttpStatus.NO_CONTENT);
        }

    }

    @PostMapping("/addUser")
    public ResponseEntity addNewuser(@RequestParam UserDetails newUser){
        Optional<UserDetails> userDetails = userDetailsRepo.findByFirstNameAndLastName(newUser.getFirstName(), newUser.getLastName());
        if(userDetails.isPresent()){
            return new ResponseEntity("User Already Exists",HttpStatus.NOT_ACCEPTABLE);
        }
        else{
            UserDetails nUser = userDetailsRepo.save(newUser);
            return new ResponseEntity("New User created with ID: "+nUser.getUserId(),HttpStatus.OK);
        }

    }

}
