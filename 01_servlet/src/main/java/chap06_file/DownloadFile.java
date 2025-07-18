package chap06_file;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/DownloadFile")
public class   DownloadFile extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        request.setCharacterEncoding("UTF-8");

        //----- 다운로드 해야할 파일명
        String filename = request.getParameter("filename");
        String filepath = "D:/storage/" + filename;

        //---- 다운로드 파일의 정보가 없으면 다운로드 불가 응답
        File file = new File(filepath);
        if (!file.exists() || file.isDirectory()) {
            response.setHeader("Content-Type", "text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('파일이 존재하지 않습니다.')</script>");
            out.close();
            return;
        }

        //----- 다운로드 응답

        //----- 다운로드 응답 헤더 구성
        String originFilename = filename.substring(filename.indexOf("_") + 1);
        response.setHeader("Content-Disposition","attachment; filename=\"" + originFilename + "\"");
        response.setHeader("Content-Type", "application/octet-stream"); //---- 응답 데이터는 binary data
        response.setHeader("Content-Length", file.length()+""); //---- 다운로드 해 줄 파일 크기

        //----- 서버에 저장된 파일을 읽어서 그대로 응답하기
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
            BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());)
        {byte[] b = new byte[1024];
            int readByte = 0;
            while ((readByte = in.read(b)) != -1) {
                out.write(b, 0, readByte);
            }
        }
    }






    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
