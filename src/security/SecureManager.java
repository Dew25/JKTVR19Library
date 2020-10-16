/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import entity.Reader;
import entity.User;
import java.util.Scanner;
import tools.creaters.ReaderManager;
import tools.creaters.UserManager;
import tools.savers.ReadersStorageManager;
import tools.savers.UsersStorageManager;

/**
 *
 * @author Melnikov
 */
public class SecureManager {
private Scanner scanner = new Scanner(System.in);
private UserManager userManager = new UserManager();
private ReaderManager readerManager = new ReaderManager();
private UsersStorageManager usersStorageManager = new UsersStorageManager();
private ReadersStorageManager readersStorageManager = new ReadersStorageManager();

    public User checkInLogin(User[] users, Reader[] readers) {
        do{
            System.out.println("Ваш выбор: ");
            System.out.println("0. Закрыть программу");
            System.out.println("1. Регистрация");
            System.out.println("2. Вход в систему");
            System.out.print("Введите номер задачи: ");
            String task = scanner.nextLine();
            switch (task) {
                case "0":
                    System.out.println("Пока! :)");
                    System.exit(0);
                    break;
                case "1":
                    User user = userManager.createUser();
                    userManager.addUserToArray(user, users);
                    readerManager.addReaderToArray(user.getReader(), readers);
                    readersStorageManager.saveReadersToFile(readers);
                    usersStorageManager.saveUsersToFile(users);
                    break;
                case "2":
                    User checkInUser = userManager.getCheckInUser(users);
                    if(checkInUser == null) break;
                    return checkInUser;
                default:
                    System.out.println("Нет такой задачи.");;
            }
        }while(true);
    }
    
}
