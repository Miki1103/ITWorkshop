package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Renji")
public class Renji extends HttpServlet{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
    	String q1 = request.getParameter("q1");
        String q2 = request.getParameter("q2");
        String q3 = request.getParameter("q3");
        String q4 = request.getParameter("q4");
        String q5 = request.getParameter("q5");
    
        String resultPage;
        if ("yes".equals(q1) && "yes".equals(q2) && "no".equals(q3)&& "no".equals(q4)&& "yes".equals(q5)) {
            resultPage = "/html/shindan6.html";
        } else if ("yes".equals(q1) && "yes".equals(q2) && "no".equals(q3)&& "no".equals(q4)&& "no".equals(q5)) {
            resultPage = "/html/shindan6.html";
        } else if ("yes".equals(q1) && "no".equals(q2) && "no".equals(q3)&& "no".equals(q4)&& "yes".equals(q5)) {
            resultPage = "/html/shindan6.html";
        } else if ("yes".equals(q1) && "no".equals(q2) && "no".equals(q3)&& "no".equals(q4)&& "no".equals(q5)) {
            resultPage = "/html/shindan6.html";
        } else if ("no".equals(q1) && "yes".equals(q2) && "yse".equals(q3)&& "no".equals(q4)&& "no".equals(q5)) {
            resultPage = "/html/shindan8.html";
        } else if ("no".equals(q1) && "yes".equals(q2) && "no".equals(q3)&& "no".equals(q4)&& "no".equals(q5)) {
            resultPage = "/html/shindan8.html";
        } else if ("no".equals(q1) && "no".equals(q2) && "yse".equals(q3)&& "no".equals(q4)&& "no".equals(q5)) {
            resultPage = "/html/shindan9.html";
        } else if ("no".equals(q1) && "no".equals(q2) && "no".equals(q3)&& "no".equals(q4)&& "no".equals(q5)) {
            resultPage = "/html/shindan6.html";
        }else {
            resultPage = "/html/shindan7.html";  // デフォルトのページ
        }

        // フォワードでそれぞれのJSP結果ページを表示
        RequestDispatcher dispatcher=
        request.getRequestDispatcher(resultPage);
        dispatcher.forward(request, response);
    }
}
