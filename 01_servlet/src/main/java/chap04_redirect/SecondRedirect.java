package chap04_redirect;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/SecondRedirect")
public class SecondRedirect extends HttpServlet {
    /**
     * @see HttpServlet#HttpServlet()
     */


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        request.setCharacterEncoding("UTF-8");

        //---- 요청 파라미터 도착 여부 확인
        String p = request.getParameter("p");
        System.out.println(p);

        //----- 응답 만들기
        response.setHeader("Content-Type","text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script>");
        out.println("alert('"+ p +" 전달 완료')");
        out.println("history.back()");
        out.println("</script>");
        out.close();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}
