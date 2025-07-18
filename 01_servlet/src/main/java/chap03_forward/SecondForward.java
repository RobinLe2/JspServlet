package chap03_forward;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/SecondForward")
public class SecondForward extends HttpServlet {
    /**
     * @see HttpServlet#HttpServlet()
     */


    //------ FirstForward로부터 전달된 request와 response는 doGet() 메소드가 받습니다.
    //------ 최초 요청 방식이 forward 할 때도 그대로 유지되기 때문에 doGet() 메소드가 받습니다.
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        //----- 요청 파라미터의 전달확인하기
        String p = request.getParameter("p");

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
