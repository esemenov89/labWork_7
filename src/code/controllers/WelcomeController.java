package code.controllers;

import code.model.hibernate.ElcatalogEntity;
import code.model.hibernate.HibernateSessionFactory;
import code.model.pojo.Role;
import org.apache.log4j.Logger;
import org.hibernate.Session;
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

        /////////////////////////////////////////////////////////////
/*        System.out.println("Hibernate tutorial");
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        ElcatalogEntity ee = new ElcatalogEntity();
        ee.setIsn("566-789-10");
        ee.setAuthor("new");
        ee.setCity("new");
        ee.setText("new");
        session.save(ee);
        session.getTransaction().commit();

        session.close();*/
        /////////////////////////////////////////////////////////////




        return mav;
    }
}
