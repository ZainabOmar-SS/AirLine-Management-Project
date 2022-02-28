package userInterface.menus.account;

import DAO.DAOHandler;
import DAO.UserDAO;
import entity.user.User;
import entity.user.UserRole;
import userInterface.menus.Menu;
import userInterface.menus.admin.AdministratorMenu;
import userInterface.menus.employee.EmployeeMenu;
import userInterface.menus.traveler.TravelerMenu;

import java.sql.SQLException;

public class LoginMenu extends Menu {
    private final UserRole userRole;
    private final Menu invokingMenu;

    public LoginMenu(Menu invokingMenu, UserRole userRole) {
        super(invokingMenu);
        this.invokingMenu = invokingMenu;
        this.userRole = userRole;
    }

    @Override
    public void start() {
        System.out.println("Login as " + userRole.getName());
        System.out.println("----------------------------------\n");

        String username = getHelper().promptForString("Username: ").toLowerCase().trim();
        String password = getHelper().promptForString("Password: ").trim();

        System.out.println("\n----------------------------------\n");

        UserDAO userDAO = DAOHandler.getInstance().getUserDAO();
        User user = null;

        boolean successLogin = false;

        try {
            successLogin = userDAO.login(username, password, userRole.getId());
            user = userDAO.getUserByLogin(username, password);
        } catch (SQLException throwables) {
            System.out.println("Unable to login...");
        }

        if(successLogin) {

            System.out.println("Login successful!\n\n");

            switch (userRole.getId()) {

                case 1:
                    EmployeeMenu employeeMenu = new EmployeeMenu(invokingMenu);
                    employeeMenu.start();
                    break;

                case 2:
                    AdministratorMenu administratorMenu = new AdministratorMenu(invokingMenu);
                    if(user!=null) administratorMenu.setUser(getUser());
                    administratorMenu.start();
                    break;

                case 3:
                    TravelerMenu travelerMenu = new TravelerMenu(invokingMenu);
                    if(user!=null) travelerMenu.setUser(user);
                    travelerMenu.start();
                    break;

            }

        } else System.out.println("Login unsuccessful.\n\n");

        back();
    }

}
