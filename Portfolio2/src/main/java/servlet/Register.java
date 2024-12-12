package servlet;

import java.io.IOException;

import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import model.UserAuthenticator;

@WebServlet("/Register")
public class Register extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            response.sendRedirect("/Portfolio2/jsp/InputError.jsp");
            return;
        }

        String hashedPassword = UserAuthenticator.hashPassword(password);

        User user = new User(0, username, hashedPassword, null, null);
        UserDAO userDAO = new UserDAO();

        try {
            userDAO.createUser(user);
         // 新規登録成功時、ユーザー情報をセッションに保存 
            request.getSession().setAttribute("user",username);
            response.sendRedirect("/Portfolio2/jsp/AccountCreated.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/Portfolio2/jsp/Error.jsp");
        }
    }
}
