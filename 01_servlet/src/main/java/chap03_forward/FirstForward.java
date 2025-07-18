package chap03_forward;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/FirstForward")
public class FirstForward extends HttpServlet {
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FirstForward() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        forward(전달)

        1. 현재 서블릿이 받은 클라이언트 요청(Request)과 응답(Response) 객체를 서버 내의 다른 리소스(서블릿)로 그대로 전달하는 기능입니다.
        2. 이 과정은 서버 내부에서 이루어지기 때문에 클라이언트는 이 사실을 알 수 없습니다.
        3. 요청에 저장된 데이터(파라미터, 속성)도 함께 전달됩니다.
        4. SELECT 문의 실행 결과를 전달하기 위해서 forward를 활용합니다.
        5. 단순한 다른 페이지로의 이동도 forward를 활용합니다.
         */
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        //------ SecondForward로 요청(request)과 응답(response) 전달(forward)하기
        RequestDispatcher dispatcher = request.getRequestDispatcher("/SecondForward");  //----- Servlet Path 작성
        dispatcher.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }
}
