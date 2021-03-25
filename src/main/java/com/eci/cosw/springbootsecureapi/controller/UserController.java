package com.eci.cosw.springbootsecureapi.controller;

import com.eci.cosw.springbootsecureapi.model.Task;
import com.eci.cosw.springbootsecureapi.model.User;
import com.eci.cosw.springbootsecureapi.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Santiago Carrillo
 * 8/21/17.
 */
@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Token login(@RequestBody User login)
            throws ServletException {

        String jwtToken = "";

        String username = login.getUsername();
        String password = login.getPassword();
        String email = login.getEmail();

        if (username == null || password == null) {
            throw new ServletException("Please fill in username and password");
        }

        if(userService.findUserByEmailAndPassword(email, password) == null){
            throw new ServletException("Invalid login. Please check your name and password.");
        }

        jwtToken = Jwts.builder().setSubject(username).claim("roles", "user").setIssuedAt(new Date()).signWith(
                SignatureAlgorithm.HS256, "secretkey").compact();

        return new Token(jwtToken);
    }

    @PostMapping("/{id}/tasks")
    public ResponseEntity<?> addTask(@PathVariable String id, @RequestBody Task task) {
        try {
            userService.addTask(id, task);
            return new ResponseEntity<>("Task added succesfully", HttpStatus.CREATED);
        } catch (Exception e) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    public class Token {

        String accessToken;

        public Token(String accessToken) {
            this.accessToken = accessToken;
        }

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String access_token) {
            this.accessToken = access_token;
        }
    }

}
