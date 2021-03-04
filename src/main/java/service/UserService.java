package service;

import com.mysql.jdbc.StringUtils;
import dao.DaoFactory;
import entity.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

public class UserService {
    private static final DaoFactory factory = DaoFactory.getInstance();

    private String getHashPassword(String pass) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(pass.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    private Boolean isDataValid(List<String> data) {
        for (var str: data) {
            if (StringUtils.isNullOrEmpty(str)) {
                return false;
            }
        }
        return true;
    }

    private User createUser(String login, String password, String weight, String height) {
        try {
            Integer _weight = Integer.valueOf(weight);
            Integer _height = Integer.valueOf(height);
            if (_height < 50 || _height > 220 || _weight < 10 || _weight > 500) {
                return null;
            }
            return new User(login, password, _weight, _height);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Boolean isUserExist(String login) {
        return login != null && factory.createUserDao().findByLogin(login) != null;
    }

    public Long addUser(String login, String password, String weight, String height) {
        List<String> data = Arrays.asList(login, password, weight, height);
        if (!isDataValid(data)) {
            System.out.println("data not valid!");
            return null;
        }
        var user = createUser(login, password, weight, height);
        return user == null ? null : factory.createUserDao().add(user);
    }

    public Long findUser(String login, String password) {
        List<String> data = Arrays.asList(login, password);
        if (!isDataValid(data)) {
            return null;
        }
        User user = factory.createUserDao().findByLogin(login);
        if (user == null ||
                !user.getPasswordHash().equals(getHashPassword(password))) {
            return null;
        }
        return user.getUserId();
    }
}
