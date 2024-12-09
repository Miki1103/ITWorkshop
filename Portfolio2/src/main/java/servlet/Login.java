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
        // パラメータ名を変更
        String username = request.getParameter("name");
        String password = request.getParameter("pass");

        UserAuthenticator authenticator = new UserAuthenticator();

        try {
            if (authenticator.authenticate(username, password)) {
                // ログイン成功時
                request.getRequestDispatcher("/Portfolio2/jsp/LoginSuccess.jsp").forward(request, response);
            } else {
                // ログイン失敗時
                request.getRequestDispatcher("/Portfolio2/jsp/LoginError.jsp").forward(request, response);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
