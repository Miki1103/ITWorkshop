package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.UserAuthenticator;

@WebServlet("/Login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserAuthenticator authenticator = new UserAuthenticator();

        try {
            if (authenticator.authenticate(username, password)) {
                // ログイン成功時、ユーザー情報をセッションに保存
                request.getSession().setAttribute("user", username);
                request.getRequestDispatcher("/jsp/LoginSuccess.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/jsp/LoginError.jsp").forward(request, response);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}

