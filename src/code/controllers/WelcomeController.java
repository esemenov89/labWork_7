package code.controllers;

import code.model.pojo.Role;
import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 *
 */
@Controller
@SessionAttributes({"username","accountType"})
public class WelcomeController {
    private static final Logger LOGGER = Logger.getLogger(WelcomeController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView sayHello() throws IOException{
        ModelAndView mav = new ModelAndView();
        mav.addObject("username",SecurityContextHolder.getContext().getAuthentication().getName());
        mav.addObject("accountType",((Role)SecurityContextHolder.getContext().getAuthentication().getAuthorities().toArray()[0]).getName());
        mav.setViewName("welcome");
        return mav;
    }
}
