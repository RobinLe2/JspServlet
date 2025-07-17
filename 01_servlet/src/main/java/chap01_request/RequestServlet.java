package chap01_request;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

/**
 *
 * Servlet
 *
 * 1. Java 기반 웹서버(WAS)에서 클라이언트의 HTTP 요청을 받아 동적으로 웹 페이지를 생성해 응답하는 서버측 프로그램입니다.
 * 2. 동적 웹 애플리케이션(Dynamic Web Project)의 핵심 기술로 javax.servlet.http.HttpServlet 클래스를 상속 받아 구현합니다. (Tomcat 9.0 기준)
 * 3. 주요 특징
 *   1) 동적 웹 페이지를 생성
 *   2) 클라이언트 요청을 처리
 *   3) MVC 패턴에서 주로 Controller 역할 담당
 *   4) 웹 컨테이너(서블릿 컨테이너, Tomcat) 안에서 동작 (하나의 애플리케이션에서 동일한 이름의 서블릿은 사용불가)
 *   5) 객체는 싱글톤(Singleton)으로 관리
 *   6) 다양한 형식(HTML, JSON, XML 등)으로 응답 가능
 */
//@WebServlet("/RequestServlet")  //----- 현재 서블릿의 요청 주소를 의미합니다. 디폴트는 서블릿 이름(/RequestServlet)입니다.
//@WebServlet({"/RequestServlet", "/hello"})
//@WebServlet("*.do")        //------------------- .do로 끝나는 모든 요청 주소를 처리합니다.
@WebServlet({"/RequestServlet", "/hello", "*.do"})
public class RequestServlet extends HttpServlet {
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * Get 방식의 요청이 발생하면 해당 요청을 받는 메소드입니다.
     *
     * GET 방식
     * 1. <a href="주소">
     * 2. <form method="get"> 또는 <form>
     * 3. location = '주소'
     * 4. open('주소')
     * 5. fetch('주소')
     *
     * @param // HttpServletRequest request
     * 1. 클라이언트 HTTP요청을 Servlet에 전달하는 객체
     * 2. WAS(Web Application Server)가 클라이언트의 요청이 들어올때마다 자동으로 생성해서 전달
     * 3. 주요 기능
     *   1) 요청 메시지 분석(파싱)
     *   2) 요청 파라미터 처리
     *   3) 세션, 쿠키 등 상태 정보 관리
     *   4) 임시 저장소 가능
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 요청 분석하기
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String contextPath = request.getContextPath();
        String requestURI = request.getRequestURI();
        String servletPath = request.getServletPath();
        String ip = request.getRemoteAddr();

        System.out.println("==== 요청 분석하기 =====");
        System.out.println("contextPath = " + contextPath);
        System.out.println("requestURI = " + requestURI);
        System.out.println("servletPath = " + servletPath);
        System.out.println("ip = " + ip);


        //----- 요청 파라미터
        int p1 = Integer.parseInt(request.getParameter("p1"));
        String p2 = request.getParameter("p2");
        String p3 = request.getParameter("p3");
        Optional<String> opt = Optional.ofNullable(request.getParameter("p4"));  //--- 파라미터 p4를 Optional로 감쌉니다. 파라미터 p4는 null일수도 있습니다. (ofNullable)
        String p4 = opt.orElse("디폴트");  //--------------------------------------- 파라미터 p4가 null이라면  "디폴트"를 꺼냅니다.
        System.out.println("p1 = " + (p1+ 10));
        System.out.println("p2 = " + p2);
        System.out.println("p3 = " + p3);
        System.out.println("p4 = " + p4);

        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response); //----- post요청으로 doPost() 메소드가 실행되면 doGet() 메소드를 직접 실행합니다.
    }
}
