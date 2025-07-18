package chap05_async;

import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/JsonResponse")
public class JsonResponse extends HttpServlet {



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.setContentType("application/json; charset=UTF-8");

        //---- JSON 데이터 응답하기
        Map<String, Object> map = new HashMap<>();
        map.put("title", "파묘");
        map.put("actors", Arrays.asList("김고은", "이도현", "최민식", "유해진"));
        map.put("scores", 9.5);
        Map<String, Object> info = new HashMap<>();
        info.put("director", "장지헌");
        info.put("genre", "스릴러");
        map.put("info", info);

        //---- JSON 문자열
        Gson gson = new Gson();
        String json = gson.toJson(map);

        //---- 응답 만들기(헤더,바디 전송)
        PrintWriter out = response.getWriter();
        out.write(json);
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
