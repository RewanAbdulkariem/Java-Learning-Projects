//DefaultUserManagementService.java
package service.impl;

import model.User;
import service.UserManagementService;

import java.util.Arrays;

public class DefaultUserManagementService implements UserManagementService {

    private static final String NOT_UNIQUE_EMAIL_ERROR_MESSAGE = "This email is already used by another user. Please, use another email";
    private static final String EMPTY_EMAIL_ERROR_MESSAGE = "You have to input email to register. Please, try one more time";
    private static final String NO_ERROR_MESSAGE = "";

    private static final int DEFAULT_USERS_CAPACITY = 10;

    private static DefaultUserManagementService instance;

    private User[] users;
    private int userIndex;


    private DefaultUserManagementService() {
        users = new User[DEFAULT_USERS_CAPACITY * 2];
        userIndex = 0;
    }

    @Override
    public String registerUser(User user) {
        if (user == null ) {
            return NO_ERROR_MESSAGE;
        }
        String email = user.getEmail();
        if (email == null || email.isEmpty()) {
            return EMPTY_EMAIL_ERROR_MESSAGE;
        }
        for (User existingUser : users) {
            if (existingUser != null &&
                    existingUser.getEmail() != null &&
                    existingUser.getEmail().equalsIgnoreCase(email)) {
                return NOT_UNIQUE_EMAIL_ERROR_MESSAGE;
            }
        }
        if (userIndex >= users.length){
            users = Arrays.copyOf(users, users.length << 1);
        }
        users[userIndex++] = user;
        return NO_ERROR_MESSAGE;
    }

    public static UserManagementService getInstance() {
        if (instance == null) {
            instance = new DefaultUserManagementService();
        }
        return instance;
    }


    @Override
    public User[] getUsers() {
        User[] currentUsers = new User[userIndex];
        for (int i = 0; i < userIndex; i++) {
            currentUsers[i] = users[i];
        }
        return currentUsers;
    }

    @Override
    public User getUserByEmail(String userEmail) {
        if (userEmail == null)
            return null;
        for (int i = 0; i < userIndex; i++) {
            if(users[i].getEmail().equalsIgnoreCase(userEmail))
                return users[i];
        }
        return null;
    }

    void clearServiceState() {
        users = new User[DEFAULT_USERS_CAPACITY * 2];
        userIndex = 0;
    }
}
