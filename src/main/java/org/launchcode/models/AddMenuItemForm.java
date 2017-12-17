package org.launchcode.models;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;

public class AddMenuItemForm {

    private Menu menu;

    private Iterable<Cheese> cheeses;

    @NotNull
    private int menuId;

    @NotNull
    private int cheeseId;

    public AddMenuItemForm() {
    }

    public Menu getMenu() {
        return menu;
    }

    public AddMenuItemForm setMenu(Menu menu) {
        this.menu = menu;
        return this;
    }

    public Iterable<Cheese> getCheeses() {
        return cheeses;
    }

    public AddMenuItemForm setCheeses(Iterable<Cheese> cheeses) {
        this.cheeses = cheeses;
        return this;
    }

    public int getMenuId() {
        return menuId;
    }

    public AddMenuItemForm setMenuId(int menuId) {
        this.menuId = menuId;
        return this;
    }

    public int getCheeseId() {
        return cheeseId;
    }

    public AddMenuItemForm setCheeseId(int cheeseId) {
        this.cheeseId = cheeseId;
        return this;
    }
}
