package by.it.selvanovich.news.bean;

import java.util.HashMap;

public class UserList {
    private final HashMap<String, User> userList;

    public UserList() {
        this.userList = new HashMap<>();
        this.userList.put("admin", new User("admin", "admin", "admin", "admin", "admin"));
        this.userList.put("user", new User("user", "user", "user", "user", "uaer"));
    }

    public String getRole(String username) {
        return userList.get(username).getRole();
    }

    public String getPassword(String username) {
        return this.userList.get(username).getPassword();
    }


    public boolean containsKey(String username) {
        return this.userList.containsKey(username);
    }

    public void put(String username, User user) {
        this.userList.put(username, user);
    }
}
