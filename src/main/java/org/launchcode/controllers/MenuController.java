package org.launchcode.controllers;

import org.launchcode.models.AddMenuItemForm;
import org.launchcode.models.Cheese;
import org.launchcode.models.Menu;
import org.launchcode.models.data.CheeseDao;
import org.launchcode.models.data.MenuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping(value = "/menu")
public class MenuController {

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private CheeseDao cheeseDao;

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("title", "Menus");
        model.addAttribute("menus", menuDao.findAll());

        return "menu/index";
    }

    @RequestMapping(value = "/add", method = GET)
    public String displayAddForm(Model model) {
        model.addAttribute("menu", new Menu());
        model.addAttribute("title", "Add New Menu");

        return "menu/add";
    }

    @RequestMapping(value = "/add", method = POST)
    public String processAddForm(Model model, @ModelAttribute @Valid Menu menu, Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add New Menu");
        }

        menuDao.save(menu);
        return "redirect:view/" + menu.getId();

    }
    @RequestMapping(value = "/view/{id}")
    public String viewMenu(Model model, @PathVariable int id) {
        Menu menu = menuDao.findOne(id);

        model.addAttribute("title", menu.getName());
        model.addAttribute("menu", menu);

        return "menu/view";
    }


    @RequestMapping(value = "/add-item/{menuId}")
    public String displayAddItemForm(Model model, @PathVariable int menuId) {
        Menu menu = menuDao.findOne(menuId);
        Iterable<Cheese> cheeses = cheeseDao.findAll();
        AddMenuItemForm form = new AddMenuItemForm()
                .setCheeses(cheeses)
                .setMenu(menu);


        model.addAttribute("form", form);
        model.addAttribute("title", "Add Item to Menu: " + menu.getName());

        return "menu/add-item";
    }

    @RequestMapping(value = "/add-item/{menuId}", method = POST)
    public String processAddItemForm(Model model, @ModelAttribute @Valid AddMenuItemForm form, Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("form", form);
            model.addAttribute("title", "Add Item to Menu: " +form.getMenu().getName());
            return "menu/add-item/";
        }

        Cheese cheese = cheeseDao.findOne(form.getCheeseId());
        Menu menu = menuDao.findOne(form.getMenuId());
        List<Cheese> cheeses = menu.getCheeses();
        cheeses.add(cheese);
        menuDao.save(menu);

        return "redirect:/menu/view/" + menu.getId();

    }
}
