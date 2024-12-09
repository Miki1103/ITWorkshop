package model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import dao.UserDAO;

public class UserAuthenticator {
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean authenticate(String username, String password) throws Exception {
        UserDAO userDAO = new UserDAO();
        User user = userDAO.findUserByUsername(username);
        if (user == null) {
            return false;
        }
        String hashedPassword = hashPassword(password);
        return hashedPassword.equals(user.getPasswordHash());
    }
}
