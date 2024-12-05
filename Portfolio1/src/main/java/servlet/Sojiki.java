package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Sojiki")
public class Sojiki extends HttpServlet{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
    	String q1 = request.getParameter("q1");
        String q2 = request.getParameter("q2");
        String q3 = request.getParameter("q3");
        String q4 = request.getParameter("q4");
    
        String resultPage;
        if ("no".equals(q1) && "yes".equals(q2) && "cost".equals(q3)&& "yes".equals(q4)) {
            resultPage = "/html/shindan19.html";
        } else if ("no".equals(q1) && "yes".equals(q2) && "cost".equals(q3)&& "no".equals(q4)) {
            resultPage = "/html/shindan3.html";
        } else if ("no".equals(q1) && "yes".equals(q2) && "easy".equals(q3)&& "yes".equals(q4)) {
            resultPage = "/html/shindan18.html";
        } else if ("no".equals(q1) && "yes".equals(q2) && "easy".equals(q3)&& "no".equals(q4)) {
            resultPage = "/html/shindan2.html";
        } else if ("no".equals(q1) && "no".equals(q2) && "cost".equals(q3)&& "yes".equals(q4)) {
            resultPage = "/html/shindan5.html";
        } else if ("no".equals(q1) && "no".equals(q2) && "cost".equals(q3)&& "no".equals(q4)) {
            resultPage = "/html/shindan5.html";
        }else if ("no".equals(q1) && "no".equals(q2) && "easy".equals(q3)&& "yes".equals(q4)) {
            resultPage = "/html/shindan4.html";
        }else if ("no".equals(q1) && "no".equals(q2) && "easy".equals(q3)&& "no".equals(q4)) {
            resultPage = "/html/shindan4.html";
        }else {
            resultPage = "/html/shindan1.html";  // デフォルトのページ
        }

        // フォワードでそれぞれのJSP結果ページを表示
        RequestDispatcher dispatcher=
        request.getRequestDispatcher(resultPage);
        dispatcher.forward(request, response);
    }
}
