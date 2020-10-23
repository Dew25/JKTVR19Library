/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import entity.Reader;
import entity.User;
import java.util.List;
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

public static enum role {READER,MANAGER};

    public User checkInLogin(List<User> listUsers, List<Reader> listReaders) {
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
                    userManager.addUserToArray(user, listUsers);
                    readerManager.addReaderToArray(user.getReader(), listReaders);
                    readersStorageManager.saveReadersToFile(listReaders);
                    usersStorageManager.saveUsersToFile(listUsers);
                    break;
                case "2":
                    User checkInUser = userManager.getCheckInUser(listUsers);
                    if(checkInUser == null) break;
                    return checkInUser;
                default:
                    System.out.println("Нет такой задачи.");;
            }
        }while(true);
    }
    
}
