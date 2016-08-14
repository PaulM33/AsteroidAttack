/**
 * 
 * Program Name: Menu.java
 * @author Paul Magbor, Daniel Krauskopf
 * Purpose: A menu enumeration for the different menus in the application.
 * Date 14-Aug-2016
 */
public enum Menu {
    GAME, START, WIN, LOSE;
    
    // If the menu is the active menu.
    boolean active;

    private Menu() {
        active = false;
    } // Menu()
    
    /**
     * Checks of the given menu is the active menu.
     * @param menu The menu to check.
     * @return True if the menu is active.
     */
    public static boolean is(Menu menu) {
        return menu.active;
    } // is (Menu);
    
    /**
     * Sets the given menu to the active menu.
     * @param menu The menu to set active.
     */
    public static void setMenu(Menu menu) {
        for (Menu m : Menu.values()) {
            m.active = false;
        }
        menu.active = true;
    } // setMenu (Menu);
} // Menu;
