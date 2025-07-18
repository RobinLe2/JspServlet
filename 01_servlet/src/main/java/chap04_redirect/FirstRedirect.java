package chap04_redirect;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URLEncoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/FirstRedirect")
public class FirstRedirect extends HttpServlet {
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FirstRedirect() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");



        /*
        redirect

        1. 서버가 클라이언트에게 "새로운 주소로 다시 요청하세요" 라고 지시하는 방식입니다.(응답의 일종입니다.)
        2. redirect 지시를 받은 클라리언트는 다시 새로운 주소로 이동하므로 URL 변경을 확인할 수 있습니다.
        3. 최초 요청 시 사용한 request와 response는 전달되지 않습니다.(redirect는 별개의 새로운 요청이기 때문)
        4. redirect 시 다른 서버로 이동할 수 있습니다.
        5. DB의 데이터 변경 작업(UPDATE, INSERT, DELETE) 이후 redirect 이동으로 새로 고침으로 인한 중복 제출(서브밋) 등을 방지할 수 있습니다.
           PRG(Post - Redirect - Get) 패턴에 필수적인 역할을 수행합니다.
         */
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8"); // 이 인코딩(clinet가 보내는 요청 인코딩) 과 아래 인코딩(SecondRedirect가 보내는(의 차이
        //------ redirect (새로운 주소로 다시 요청하라는 응답)
        response.sendRedirect("/servlet_war_exploded/SecondRedirect?p=" + URLEncoder.encode(request.getParameter("p"), "UTF-8"));

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}
