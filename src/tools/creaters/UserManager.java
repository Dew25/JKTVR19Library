/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.creaters;

import entity.Book;
import entity.Reader;
import entity.User;
import java.util.Scanner;
import security.SecureManager;

/**
 *
 * @author Melnikov
 */
public class UserManager {
    private Scanner scanner = new Scanner(System.in);

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
        return user;
    }

    public void addUserToArray(User user, User[] users) {
        for (int i = 0; i < users.length; i++) {
            if(users[i] == null){
                users[i] = user;
                break;
            }
        }    
    }

    public void printListUsers(User[] users) {
        int j = 0;
        for (User u : users) {
            if(u != null){
                System.out.println(j+1+". "+u.toString());
                j++;
            }
        }
    }

    public User getCheckInUser(User[] users) {
        System.out.println("--- Вход в систему ---");
        System.out.print("Login: ");
        String login = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        for (int i = 0; i < users.length; i++) {
            if(users[i] != null && users[i].getLogin().equals(login)){
                for (int j = 0; j < 2; j++) {
                   if(users[i].getPassword().equals(password)){
                       return users[i];
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
