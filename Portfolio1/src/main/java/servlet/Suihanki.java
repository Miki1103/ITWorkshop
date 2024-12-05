package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Suihanki")
public class Suihanki extends HttpServlet{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
    	String q1 = request.getParameter("q1");
        String q2 = request.getParameter("q2");
        String q3 = request.getParameter("q3");
        String q4 = request.getParameter("q4");
    
        String resultPage;
        if ("yes".equals(q1) && "yes".equals(q2) && "cost".equals(q3)&& "yes".equals(q4)) {
            resultPage = "/html/shindan11.html";
        } else if ("yes".equals(q1) && "yes".equals(q2) && "cost".equals(q3)&& "no".equals(q4)) {
            resultPage = "/html/shindan11.html";
        } else if ("yes".equals(q1) && "yes".equals(q2) && "good".equals(q3)&& "yes".equals(q4)) {
            resultPage = "/html/shindan11.html";
        } else if ("yes".equals(q1) && "yes".equals(q2) && "good".equals(q3)&& "no".equals(q4)) {
            resultPage = "/html/shindan11.html";
        } else if ("yes".equals(q1) && "no".equals(q2) && "cost".equals(q3)&& "yes".equals(q4)) {
            resultPage = "/html/shindan10.html";
        }else if ("yes".equals(q1) && "no".equals(q2) && "cost".equals(q3)&& "no".equals(q4)) {
            resultPage = "/html/shindan10.html";
        } else if ("yes".equals(q1) && "no".equals(q2) && "good".equals(q3)&& "yes".equals(q4)) {
            resultPage = "/html/shindan11.html";
        }else if ("yes".equals(q1) && "no".equals(q2) && "good".equals(q3)&& "no".equals(q4)) {
            resultPage = "/html/shindan11.html";
        }else if ("no".equals(q1) && "yes".equals(q2) && "cost".equals(q3)&& "yes".equals(q4)) {
            resultPage = "/html/shindan11.html";
        }else if ("no".equals(q1) && "yes".equals(q2) && "cost".equals(q3)&& "no".equals(q4)) {
            resultPage = "/html/shindan11.html";
        }else if ("no".equals(q1) && "no".equals(q2) && "cost".equals(q3)&& "yes".equals(q4)) {
            resultPage = "/html/shindan11.html";
        }else if ("no".equals(q1) && "no".equals(q2) && "cost".equals(q3)&& "no".equals(q4)) {
            resultPage = "/html/shindan11.html";
        }else {
            resultPage = "/html/shindan12.html";  // デフォルトのページ
        }

        // フォワードでそれぞれのJSP結果ページを表示
        RequestDispatcher dispatcher=
        request.getRequestDispatcher(resultPage);
        dispatcher.forward(request, response);
    }
}

