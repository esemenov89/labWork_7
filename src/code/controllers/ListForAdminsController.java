package code.controllers;

import code.model.hibernate.ElcatalogEntity;
import code.model.pojo.NewStorageUnit;
import code.model.pojo.StorageUnit;
import code.model.pojo.User;
import code.services.StorageUnitService;
import code.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;

/**
 *
 */
@Controller
@RequestMapping(value = "/listEntitiesForAdmins")
public class ListForAdminsController {

    private StorageUnitService storageUnitService;
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setStorageUnitService(StorageUnitService storageUnitService) {
        this.storageUnitService = storageUnitService;
    }

    ArrayList<ElcatalogEntity> storageUnits = null;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showList(ModelAndView mav) {
        storageUnits = storageUnitService.getAllStorageUnits();
        mav.addObject("books", storageUnits);
        mav.addObject("users", userService.getAllUsers());
        mav.addObject("currentURL", "/listEntitiesForAdmins");
        mav.setViewName("listEntitiesForAdmins/forAdmins");

        return mav;
    }

    @RequestMapping(value = "/readStorageUnit",method = RequestMethod.GET)
    public ModelAndView readStorageUnit(@RequestParam(value = "isn", required = true) String isn) {

        ModelAndView mav = new ModelAndView();
        if (storageUnits!=null) {
            ElcatalogEntity storageUnit=storageUnits.stream().filter(x->x.getIsn().equals(isn)).findFirst().get();
            mav.addObject("title", storageUnit.getTitle());
            mav.addObject("text", storageUnit.getText());
            mav.addObject("admin", 1);
            mav.setViewName("read/readStorageUnit");
        }
        return mav;
    }

    @RequestMapping(value = "/deleteStorageUnit",method = RequestMethod.GET)
    public ModelAndView deleteStorageUnit(@RequestParam(value = "isn", required = true) String isn) {
        ModelAndView mav = new ModelAndView();
        storageUnitService.delStorageUnitByISN(isn);
        mav.setViewName("redirect:/listEntitiesForAdmins");
        return mav;
    }

    @RequestMapping(value = "/changeStorageUnit",method = RequestMethod.GET)
    public ModelAndView changeStorageUnitGet(@RequestParam(value = "isn", required = true) String isn) {
        ModelAndView mav = new ModelAndView();
        if (storageUnits!=null) {
            ElcatalogEntity storageUnit=storageUnits.stream().filter(x->x.getIsn().equals(isn)).findFirst().get();

            mav.addObject("author", storageUnit.getAuthor());
            mav.addObject("title", storageUnit.getTitle());
            mav.addObject("publishingHouse", storageUnit.getPublishingHouse());
            mav.addObject("city", storageUnit.getCity());
            mav.addObject("year", storageUnit.getYear());
            mav.addObject("pagesCount", storageUnit.getPagesCount());
            mav.addObject("isn", storageUnit.getIsn());
            mav.addObject("text", storageUnit.getText());
            mav.addObject("isnOld", storageUnit.getIsn());

            mav.setViewName("/listEntitiesForAdmins/changeStorageUnit");
        }
        return mav;
    }

    //TODO Model Attribute for RequestParams
    @RequestMapping(value = "/changeStorageUnit",method = RequestMethod.POST)
    public ModelAndView changeStorageUnitPost(@ModelAttribute NewStorageUnit newStorageUnit) {
        return changeOrAddStorageUnit(newStorageUnit,true);
    }

    @RequestMapping(value = "/addStorageUnit",method = RequestMethod.GET)
    public String addStorageUnitGet() {
        return "listEntitiesForAdmins/changeStorageUnit";
    }

    @RequestMapping(value = "/addStorageUnit",method = RequestMethod.POST)
    public ModelAndView addStorageUnitPost(@ModelAttribute NewStorageUnit newStorageUnit) {
        return changeOrAddStorageUnit(newStorageUnit,false);
    }


    public ModelAndView changeOrAddStorageUnit(NewStorageUnit newStorageUnit1,boolean change) {
        NewStorageUnit newStorageUnit = new NewStorageUnit(newStorageUnit1.getAuthor(),newStorageUnit1.getTitle(),
                newStorageUnit1.getPublishingHouse(),newStorageUnit1.getCity(),newStorageUnit1.getYear(),
                newStorageUnit1.getPagesCount(),newStorageUnit1.getIsn().split(",")[0],newStorageUnit1.getText(),newStorageUnit1.getIsnOld());

        ModelAndView mav = new ModelAndView();
        boolean error = false;

        ElcatalogEntity storageUnit = storageUnitService.validateStorageUnit(newStorageUnit);

            if (storageUnit.getAuthor().equals("@Error1")) {
                mav.addObject("changeAuthor",
                        "Field [Author] can be contain digits, latin symbols and cirilic symbols and symbols: [.,_-], and field don't be empty.");
                error = true;
            }
            if (storageUnit.getTitle().equals("@Error1")) {
                mav.addObject("changeTitle",
                        "Field [Title] can be contain digits, latin symbols and cirilic symbols and symbols: [.,_-], and field don't be empty.");
                error = true;
            }
            if (storageUnit.getPublishingHouse().equals("@Error1")) {
                mav.addObject("changePublishingHouse",
                        "Field [Publishing house] can be contain digits, latin symbols and cirilic symbols and symbols: [.,_-], and field don't be empty.");
                error = true;
            }
            if (storageUnit.getCity().equals("@Error1")) {
                mav.addObject("changeCity",
                        "Field [City] can be contain digits, latin symbols and cirilic symbols and symbols: [.,_-], and field don't be empty.");
                error = true;
            }
            if (storageUnit.getYear() == -1) {
                mav.addObject("changeYear",
                        "Field [Year] can be contain only digits, and field don't be empty.");
                error = true;
            }
            if (storageUnit.getPagesCount() == -1) {
                mav.addObject("changePagesCount",
                        "Field [Pages count] can be contain only digits, and field don't be empty.");
                error = true;
            }
            if (storageUnit.getIsn().equals("@Error1")) {
                mav.addObject("changeIsn",
                        "Field [ISN] can be contain digits, latin symbols and cirilic symbols and symbol: [-], and field don't be empty.");
                error = true;
            }
            if (!change) {
                if (storageUnitService.getStorageUnitByISN(newStorageUnit.getIsn()) != null) {
                    mav.addObject("changeIsn",
                            "Field [ISN] is a unique, storage unit with this isn already contain in database!");
                    error = true;
                }
            }
            if (storageUnit.getText().equals("@Error1")) {
                mav.addObject("changeText",
                        "Field [Text] don't be empty.");
                error = true;
            }
            if (change) {
                if (!error) {
                    if (newStorageUnit.getIsnOld().equals(newStorageUnit.getIsn())) {
                        storageUnitService.delStorageUnitByISN(newStorageUnit.getIsnOld());
                    } else {
                        if (storageUnitService.getStorageUnitByISN(newStorageUnit.getIsn()) != null) {
                            mav.addObject("changeIsn",
                                    "Field [ISN] is a unique, storage unit with this isn already contain in database!");
                            error = true;
                        } else {
                            storageUnitService.delStorageUnitByISN(newStorageUnit.getIsnOld());
                        }
                    }
                }
            }
            if (error) {
                mav.addObject("author", newStorageUnit.getAuthor());
                mav.addObject("title", newStorageUnit.getTitle());
                mav.addObject("publishingHouse", newStorageUnit.getPublishingHouse());
                mav.addObject("city", newStorageUnit.getCity());
                mav.addObject("year", newStorageUnit.getYear());
                mav.addObject("pagesCount", newStorageUnit.getPagesCount());
                mav.addObject("isn", newStorageUnit.getIsn());
                mav.addObject("text", newStorageUnit.getText());
                mav.setViewName("/listEntitiesForAdmins/changeStorageUnit");
            } else {
                storageUnitService.addStorageUnit(storageUnit);
                mav.setViewName("redirect:/listEntitiesForAdmins");
            }
        return mav;
    }

    @RequestMapping(value = "/lockOrUnlock",method = RequestMethod.GET)
    public ModelAndView lockOrUnlock(@RequestParam(value = "nick", required = true) String nick,
                               @RequestParam(value = "lock", required = true) String lock) {
        ModelAndView mav = new ModelAndView();
        Long newLock = Long.valueOf(0);
        newLock = lock.equals("0") ? Long.valueOf(1) : Long.valueOf(0);
        userService.lockOrUnlockUser(nick, newLock);
        mav.setViewName("redirect:/listEntitiesForAdmins");
        return mav;
    }
}
