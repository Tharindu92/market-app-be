package com.microservices.signin;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserAuthController {

    @Autowired
    private UserAuthService userAuthService;
//    private static final Logger LOGGER= LogManager.getLogger(UserAuthController.class);

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, value = "/user/signin")
    public Message getStaffByUserName(@RequestBody User user){
//        LOGGER.info("Hit una");
        return userAuthService.userSignIn(user);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user/signin")
    public List<User> getAllUsers(){
        return userAuthService.getAllUsers();
    }
}
