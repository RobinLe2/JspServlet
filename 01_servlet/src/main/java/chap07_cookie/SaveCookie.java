package chap07_cookie;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URLEncoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/SaveCookie")
public class SaveCookie extends HttpServlet {



    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        /**
         * javax.servlet.http.Cookie
         *
         * 1. 서블릿에서 클라이언트의 브라우저에 저장할 작은 텍스트 데이터(쿠키)를 생성하고 관리하기 위한 클래스입니다.
         * 2. 서버가 클라이언트를 식별하거나 상태 정보를 유지할 때 주로 사용합니다.
         * 3. 쿠키는 클라이언트에 저장되기 때문에 보안이 취약합니다.
         * 4. 주요 메소드
         *  1) setMaxAge(int) : 쿠키 유효시간(초) 설정. 0은 쿠키 삭제를 의미, -1은 브라우저 종료시 쿠키 삭제를 의미
         *  2) setPath(String)  : 쿠키 유효경로 설정. 해당 경로 이하에서만 쿠키가 유효.
         *  3) setSecure(boolean) : true 시 HTTPS 통신에서만 쿠키 전송 허용
         *  4) setHttpOnly(boolean) : true 시 자바스크립트로 접근하지 못하도록 쿠키 보호.
         */

        // 요청 파라미터
        String name = request.getParameter("name");
        String email = request.getParameter("email");

        // 쿠키 생성(key/value 구조, 영문/숫자/일부 ASCII 문자
        Cookie nameCookie = new Cookie("name", URLEncoder.encode(name, "UTF-8"));
        Cookie emailCookie = new Cookie("email", URLEncoder.encode(email, "UTF-8"));

        // 쿠키 유효시간
        nameCookie.setMaxAge(60 * 60 * 24 * 15); //-- 15일
        emailCookie.setMaxAge(-1);  //--------------- 브라우저 종료 시 삭제되는 쿠키(session cookie) .setMaxAge() 생략도 가능

        // 쿠키 유효경로 (디폴트는 context path)
        nameCookie.setPath(request.getContextPath());  //------ /01_servlet 하위 주소는 모두 nameCookie 참조 가능

        // 쿠키 저장(클라이언트의 브라우저로 쿠키를 전송하는 개념)
        response.addCookie(nameCookie);
        response.addCookie(emailCookie);

        //ReadCookie 서블릿으로 이동하기
        response.sendRedirect(request.getContextPath() + "/ReadCookie");
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}
