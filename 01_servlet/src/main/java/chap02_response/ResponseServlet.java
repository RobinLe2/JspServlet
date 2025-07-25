package chap02_response;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/ResponseServlet")
public class ResponseServlet extends HttpServlet {


    /**
     * @Param HttpServletResponse response
	 * 1. 서버가 클라이언트에게 HTTP 응답을 보낼 때 사용하는 객체
	 * 2. 응답 메시지의 상태 코드, 응답 헤더, 응답 바디(본문)을 생성하거나 제어
	 * 3. 주요 기능
	 * 	 1) HTTP 상태 코드 : 200 , 404 등
	 * 	 2) 응답 헤더 : Content-Type , Cache-Control 등
	 * 	 3) 응답 바디: HTML, JSON, XML (화면, 데이터, 데이터)
	 * 	 4) 추가 작업 : Cookie, Redirect
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

		System.out.println("doGet()");

        //---- HTTP 상태코드
        response.setStatus(HttpServletResponse.SC_OK);  //200

        //---- 응답 헤더
        //     1. HTML: text/html
        //     2. JSON: application/json
        //     3. XML : application/json
        response.setHeader("Content-Type","text/html; charset=UTF-8");  // HTML 응답

        //----- 응답 데이터 인코딩  (응답 헤더 Content-Type에서 함께 작성하는 것이 일반적)
        // response.setCharacterEncoding("UTF-8");

        //----- 응답 출력 스트림 생성
        PrintWriter out = response.getWriter();

        //----- 응답 바디 생성
        out.println("<script>");
        out.println("alert('응답입니다')");
        out.println("history.back()");
        out.println("</script>");

        //----- 응답 출력 스트림 닫기
        out.close();

    }

    /**
     *
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}
