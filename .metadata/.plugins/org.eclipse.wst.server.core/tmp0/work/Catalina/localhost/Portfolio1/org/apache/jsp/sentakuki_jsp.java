/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/10.1.18
 * Generated at: 2024-12-06 04:35:51 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.jsp.*;

public final class sentakuki_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports,
                 org.apache.jasper.runtime.JspSourceDirectives {

  private static final jakarta.servlet.jsp.JspFactory _jspxFactory =
          jakarta.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("jakarta.servlet");
    _jspx_imports_packages.add("jakarta.servlet.http");
    _jspx_imports_packages.add("jakarta.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile jakarta.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public boolean getErrorOnELNotFound() {
    return false;
  }

  public jakarta.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final jakarta.servlet.http.HttpServletRequest request, final jakarta.servlet.http.HttpServletResponse response)
      throws java.io.IOException, jakarta.servlet.ServletException {

    if (!jakarta.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPではGET、POST、またはHEADのみが許可されます。 JasperはOPTIONSも許可しています。");
        return;
      }
    }

    final jakarta.servlet.jsp.PageContext pageContext;
    jakarta.servlet.http.HttpSession session = null;
    final jakarta.servlet.ServletContext application;
    final jakarta.servlet.ServletConfig config;
    jakarta.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    jakarta.servlet.jsp.JspWriter _jspx_out = null;
    jakarta.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"ja\">\n");
      out.write("<head>\n");
      out.write("    \n");
      out.write("    <meta charset=\"UTF-8\">\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("    <meta name=\"description\" content=\"掃除機や電子レンジなど、あなたにぴったりな家電を診断できるアプリ\">\n");
      out.write("    \n");
      out.write("    <title>洗濯機の診断</title>\n");
      out.write("\n");
      out.write("     <!-- viewportの指定 -->\n");
      out.write("     <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("\n");
      out.write("    <!-- リセットCSS -->\n");
      out.write("    <link rel=\"stylesheet\" href=\"https://unpkg.com/ress/dist/ress.min.css\">\n");
      out.write("\n");
      out.write("    <!-- Googleフォント -->\n");
      out.write("    <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\n");
      out.write("    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\n");
      out.write("    <link href=\"https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding:wght@400;700&family=Zen+Maru+Gothic:wght@300;400;500;700;900&display=swap\" rel=\"stylesheet\">\n");
      out.write("\n");
      out.write("    <!-- CSS-->\n");
      out.write("    <link rel=\"stylesheet\" href=\"http://localhost:8080/Portfolio1/css/style.css\">\n");
      out.write("\n");
      out.write("    <!-- ファビコン -->\n");
      out.write("     <link rel=\"icon\" type=\"image.png\" href=\"http://localhost:8080/Portfolio1/images/logo2.png\">\n");
      out.write("\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("<body>   \n");
      out.write("    <header class=\"page-header\">\n");
      out.write("            <h1 class=\"align-center\">\n");
      out.write("            <a href=\"/Portfolio1/index.html\"><img class=\"logo\" src=\"http://localhost:8080/Portfolio1/images/logo1.png\" alt=\"家電おすすめアプリ\"></a>\n");
      out.write("            </h1>\n");
      out.write("        <nav>\n");
      out.write("        <ul class=\"main-nav font-japanese\">\n");
      out.write("            <li><a href=\"/Portfolio1/html/type.html\">家電の種類</a></li>\n");
      out.write("            <li><a href=\"/Portfolio1/html/menu.html\">家電診断</a></li>\n");
      out.write("            <li><a href=\"/Portfolio1/html/contact.html\">お問い合わせ</a></li>\n");
      out.write("        </ul>    \n");
      out.write("        </nav>\n");
      out.write("    </header>\n");
      out.write("    \n");
      out.write("     <h2 class=\"page-title font-japanese\">診断スタート</h2>\n");
      out.write("\n");
      out.write("     <div class=\"align-center\">\n");
      out.write("     <p>※必ずどちらかを選択してください</p>\n");
      out.write("     </div>\n");
      out.write("     \n");
      out.write("     <main class=\"align-center category\">\n");
      out.write("     \n");
      out.write("     <form action=\"Sentakuki\" method=\"post\">\n");
      out.write("     <p>Q1.乾燥機能は必要？</p>\n");
      out.write("\n");
      out.write("    <input type=\"radio\" name=\"q1\" value=\"yes\">必要<br>\n");
      out.write("    <input type=\"radio\" name=\"q1\" value=\"no\">不要\n");
      out.write("\n");
      out.write("    <p>Q2.洗濯物は泥汚れが多い？</p>\n");
      out.write("\n");
      out.write("    <input type=\"radio\" name=\"q2\" value=\"yes\">多い<br>\n");
      out.write("    <input type=\"radio\" name=\"q2\" value=\"no\">多くない\n");
      out.write("\n");
      out.write("    <p>Q3.洗剤を測らず入れる事はある？</p>\n");
      out.write("\n");
      out.write("    <input type=\"radio\" name=\"q3\" value=\"yes\">毎回適当<br>\n");
      out.write("    <input type=\"radio\" name=\"q3\" value=\"no\">きちんと測る\n");
      out.write("\n");
      out.write("    <p>Q4.節水、節電を一番重要視する？</p>\n");
      out.write("\n");
      out.write("    <input type=\"radio\" name=\"q4\" value=\"yes\">はい<br>\n");
      out.write("    <input type=\"radio\" name=\"q4\" value=\"no\">いいえ\n");
      out.write("\n");
      out.write("    <p>Q5.コンパクトなサイズがいい？</p>\n");
      out.write("\n");
      out.write("    <input type=\"radio\" name=\"q5\" value=\"yes\">はい<br>\n");
      out.write("    <input type=\"radio\" name=\"q5\" value=\"no\">特に気にしない<br>\n");
      out.write("\n");
      out.write("    <div class=\"align-center send-btn\">\n");
      out.write("    <input type=\"submit\" value=\"診断結果を見る\">\n");
      out.write("    </div>\n");
      out.write("    </form>\n");
      out.write("    \n");
      out.write("    </main>\n");
      out.write("\n");
      out.write("    <footer class=\"page-footer\">\n");
      out.write("        <div class=\"copyright\">\n");
      out.write("            <small>&copy;2024 Kaden Shindan</small>\n");
      out.write("        </div>\n");
      out.write("    </footer>\n");
      out.write("\n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof jakarta.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
