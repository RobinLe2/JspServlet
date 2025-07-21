package chap07_cookie;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/ReadCookie")
public class ReadCookie extends HttpServlet {
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReadCookie() {
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

        Cookie[] cookies = request.getCookies();

        //쿠키 확인하기
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                System.out.println("Cookie Name: " + cookie.getName());
                System.out.println("Cookie Value: " + URLDecoder.decode(cookie.getValue(), "UTF-8"));

                if (cookie.getName().equals("email")) {
                    // 같은 이름(email)을 가진 쿠키를 새로 생성 후 쿠키 유효시간을 0으로 설정 후 쿠키 저장하기
                    Cookie ck = new Cookie("email", ""); // 삭제할 쿠키의 값("")은 의미가 없습니다.
                    ck.setMaxAge(0);
                    response.addCookie(ck);
                    response.sendRedirect(request.getContextPath() + "/ReadCookie");
                }
            }

        }

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}
