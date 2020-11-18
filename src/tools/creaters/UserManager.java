/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.creaters;

import entity.Reader;
import entity.User;
import entity.facade.ReaderFacade;
import entity.facade.UserFacade;
import factory.FacadeFactory;
import java.util.List;
import java.util.Scanner;
import security.SecureManager;

/**
 *
 * @author Melnikov
 */
public class UserManager {
    private Scanner scanner = new Scanner(System.in);
    private ReaderFacade readerFacade = FacadeFactory.getReaderFacade();
    private UserFacade userFacade = FacadeFactory.getUserFacade();

    public User createUser() {
        ReaderManager readerManager = new ReaderManager();
        Reader reader = readerManager.createReader();
        User user = new User();
        System.out.println("--- Добавить пользователя ---");
        System.out.println("Логин пользователя:");
        user.setLogin(scanner.nextLine());
        System.out.println("Введите пароль:");
        user.setPassword(scanner.nextLine());
        int numRole;
        do{
            System.out.println("Список ролей:");
            for (int i = 0; i < SecureManager.role.values().length; i++) {
                System.out.printf("%d. %s%n"
                        ,i+1
                        ,SecureManager.role.values()[i].toString()
                );
            }
            System.out.println("Введите номер роли:");
            String numRoleStr = scanner.nextLine();
            try {
                numRole = Integer.parseInt(numRoleStr);
                break;
            } catch (Exception e) {
                System.out.println("Введите цифру.");
            }
        }while(true);
        user.setRole(SecureManager.role.values()[numRole-1].toString());
        user.setReader(reader);
        userFacade.create(user);
        return user;
    }
  
    public User getCheckInUser() {
        System.out.println("--- Вход в систему ---");
        System.out.print("Login: ");
        String login = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        List<User> listUsers = userFacade.findAll();
        if(listUsers == null){
            System.out.println("У вас нет права входа. Зарегистрируйтесь.");
            return null;
        }
        for (int i = 0; i < listUsers.size(); i++) {
            if(listUsers.get(i) != null && listUsers.get(i).getLogin().equals(login)){
                for (int j = 0; j < 2; j++) {
                   if(listUsers.get(i).getPassword().equals(password)){
                       return listUsers.get(i);
                   }else{
                       System.out.println("Попробуй еще раз.");
                       password = scanner.nextLine();
                   }
                }
                System.out.println("У вас нет права входа.");
                return null;
            }
        }
        System.out.println("У вас нет права входа. Зарегистрируйтесь.");
        return null;
    }
    
}
