package com.microservices.signin;

//import com.microservices.signin.UserRepo;
//import com.microservices.signin.Message;
//import com.microservices.signin.User;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Service
@Repository
public class UserAuthService {

    @Autowired
    private Message message;

    @Autowired
    private UserRepo userRepo;

//    private static final Logger LOGGER= LogManager.getLogger(UserAuthService.class);

    public Message userSignIn(User user){
        try{
            if(user.getUsername().equals("admin@com") && user.getPassword().equals("admin"))
                message.setSuccess(true);
            else
                message.setSuccess(false);
        }catch (Exception e){

            message.setSuccess(false);
        }
        return message;
    }

    public List<User> getAllUsers(){
//        LOGGER.info("AWA");
//        LOGGER.info(String.valueOf(userRepo.findAll().size()));
        return userRepo.findAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
