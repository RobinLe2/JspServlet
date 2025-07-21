package chap08_servlet_container.c_request;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/HttpServletRequestScope")
public class HttpServletRequestScope extends HttpServlet {


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

		/**
		 *
		 * 2. 현재 요청이 처리되는 동안 제이터에 접근할 수 있고, 요청이 끝나면(응답 시) 데이터는 소멸됩니다.
		 * 3. 주로 RequestDispatcher를 이용한 forward 시 데이터를 전달하기 위해서 사용합니다.
		 * 4. 데이터 처리 예시
		 *   1) 저장: request.setAttribute(String key, Object value);
		 *   2) 조희 : request.getAttribute(String key);
		 *   3) 삭제 : request.removeAttribute(String key);
		 */

		//------ 데이터 저장
		List<Map<String, Object>> products = new ArrayList<>();

		Map<String, Object> product1 = new HashMap<>();

		product1.put("model", "가가가");
		product1.put("price", 1000);
		products.add(product1);

		Map<String, Object> product2 = new HashMap<>();
		product2.put("model", "나나나");
		product2.put("price", 2000);
		products.add(product2);

		Map<String, Object> product3 = new HashMap<>();
		product3.put("model", "다다다");
		product3.put("price", 3000);
		products.add(product3);

		request.setAttribute("products",products);

		//---- forward
		request.getRequestDispatcher("/RequestDataView").forward(request,response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
