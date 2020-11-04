/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.creaters;

import entity.Reader;
import entity.User;
import java.util.List;
import java.util.Scanner;
import jktvr19library.App;
import security.SecureManager;
import tools.savers.StorageManagerInterface;

/**
 *
 * @author Melnikov
 */
public class UserManager {
    private Scanner scanner = new Scanner(System.in);

    public void createUser(List<User> listUsers,List<Reader>listReaders,StorageManagerInterface storageManager) {
        ReaderManager readerManager = new ReaderManager();
        Reader reader = readerManager.createReader();
        listReaders.add(reader);
        storageManager.save(listReaders, App.storageFile.READERS.toString());
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
        user.setRoleName(SecureManager.role.values()[numRole-1].toString());
        user.setReader(reader);
        listUsers.add(user);
        storageManager.save(listUsers, App.storageFile.USERS.toString());
       
    }

    public void addUserToArray(User user, List<User> listUsers) {
        listUsers.add(user);
    }

    public void printListUsers(List<User> listUsers) {
        int j = 0;
        for (User u : listUsers) {
            if(u != null){
                System.out.println(j+1+". "+u.toString());
                j++;
            }
        }
    }

    public User getCheckInUser(List<User> listUsers) {
        System.out.println("--- Вход в систему ---");
        System.out.print("Login: ");
        String login = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
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
