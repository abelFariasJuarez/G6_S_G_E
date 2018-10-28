package sge;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class LoginRegistry {

    private static LoginRegistry instance = null;
    private Map<String, String> users;
    private Map<String, LoginEntry> loggedUsers;


    public static LoginRegistry getInstance() {
        if (instance == null) {
            instance = new LoginRegistry();
        }
        return instance;
    }

    private LoginRegistry() {
        loggedUsers = new HashMap<>();
        users = new HashMap<>();
    }

    public void setUsuarios(Properties usuarios) {
        users.clear();
        for (String key : usuarios.stringPropertyNames()) {
            users.put(key, usuarios.getProperty(key));
        }
    }

    public String login(String user, String pass) {
        String passForUser = users.get(user);
        if (passForUser != null && passForUser.equals(pass)) {
            this.logout(user);
            LoginEntry loginEntry = new LoginEntry(user);
            loggedUsers.put(user, loginEntry);
            return loginEntry.getToken();
        }
        return null;
    }

    public void logout(String user) {
        loggedUsers.remove(user);
    }

    public boolean checkToken(String user, String token) {
        LoginEntry loginEntry = loggedUsers.get(user);
        return loginEntry != null && loginEntry.isValid(token);
    }

    private class LoginEntry {
        private String user;
        private String token;
        private Date initDate;

        public LoginEntry(String user) {
            this.user = user;
            this.initDate = new Date();
            this.token = user + initDate.getTime();
        }

        public String getToken() {
            return token;
        }

        public boolean isValid(String token) {
            return this.getToken().equals(token) && (new Date().getTime() < this.initDate.getTime() + 60 * 1000 * 10);
        }

        public void updateDate() {
            this.initDate = new Date();
        }
    }
}