package com.example.bemyapp.service;

import com.example.bemyapp.model.TbUser;
import com.example.bemyapp.repository.TbUserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    private static final Logger logger = LogManager.getLogger(UserService.class);

    @Autowired
    private TbUserRepository repository;

    public TbUser login(String username, String password){
        logger.info("login");
        String encodedPassword = Base64.getEncoder().encodeToString(password.getBytes());
        TbUser tbUser = repository.findByUsernameAndPassword(username, encodedPassword);
        if(tbUser!=null){
            return tbUser;
        }
        return null;
    }

    public TbUser register(String username, String password, String age){
        logger.debug("Debug message");
        logger.info("Info message");
        logger.warn("Warning message");
        logger.error("Error message");
        String encodedPassword = Base64.getEncoder().encodeToString(password.getBytes());
        TbUser tbUser = new TbUser();
        tbUser.setUsername(username);
        tbUser.setPassword(encodedPassword);
        tbUser.setAge(Integer.parseInt(age));
        if(tbUser!=null){
            repository.save(tbUser);
            return tbUser;
        }
        return null;
    }

    public List<TbUser> viewAll(){
        logger.info("view");
        return repository.findAll();
    }

    public boolean isValidPassword(String password) {
        // Regex pattern for password validation
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,}$";

        // Compile the regex pattern
        Pattern pattern = Pattern.compile(regex);

        // Match the password against the pattern
        Matcher matcher = pattern.matcher(password);

        // Return true if password matches the pattern, otherwise false
        return matcher.matches();
    }
}
