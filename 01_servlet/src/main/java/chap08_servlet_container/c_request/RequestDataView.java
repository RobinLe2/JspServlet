package chap08_servlet_container.c_request;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/RequestDataView")
public class RequestDataView extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

		//----- request에 저장된 데이터 조회

		List<Map<String, Object>> products = (List<Map<String, Object>>) request.getAttribute("products");

		//----- 조회한 데이터를 응답
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<div class=\"products\">");
		for (Map<String, Object> product : products) {
			out.println("<div class=\"prodcut\">");
			out.println(product.get("model") + "," + product.get("price"));
			out.println("</div>");
		}
		out.println("</div>");
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
