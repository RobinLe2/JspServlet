package chap01_request;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/RequestExServlet")
public class RequestExServlet extends HttpServlet {
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestExServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String[] hobbies = request.getParameterValues("hobbies");
        String mobile = request.getParameter("mobile");
        String mobileNum = request.getParameter("mobileNum");


        response.getWriter().append("요청 결과");
        response.getWriter().append("이름: " + name +"<br>");
        if (name != null) {
            response.getWriter().append(name +"<br>");
        } else {
            response.getWriter().append("이름은 필수 입력 항목입니다." +"<br>");
        }
        response.getWriter().append("이메일: " + email +"<br>" );
        response.getWriter().append("성별: " + gender +"<br>" );

        response.getWriter().append("취미: ");
        if (hobbies != null) {
            for (String hobby : hobbies) {
                response.getWriter().append(hobby + " "+"<br>");
            }
        } else {
            response.getWriter().append("선택 없음"+"<br>");
        }
        response.getWriter().append("<br>");

        response.getWriter().append("통신사 코드: " + mobile +"<br>");
        response.getWriter().append("전화번호: " + mobileNum );


        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}
