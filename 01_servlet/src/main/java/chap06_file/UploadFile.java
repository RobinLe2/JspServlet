package chap06_file;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/UploadFile")
@MultipartConfig(
        location = "D:/storage",        //---- 업로드 파일을 저장할 디렉터리 경로
        maxFileSize = 1024 * 1024 * 10  //---- 크기  10MB 제한
)
public class UploadFile extends HttpServlet {

    /**
     *
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        request.setCharacterEncoding("UTF-8");

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //----- 업로드 한 파일을 저장할 디렉터리 만들기
        File dir = new File("D:/storage");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        Part part = request.getPart("file");
        String filename = System.currentTimeMillis()+ "_" +part.getSubmittedFileName();  //----- 타임스탬프 + 밑줄 + 첨부된 파일명
        part.write(filename);


        //---- 응답
        response.setHeader("Content-Type", "text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<a href='/servlet_war_exploded/chap06_file/NewFile.html'>뒤로 가기</a>");
        out.println("<div>첨부된 파일 정보</div>");
        out.println("<div>원래파일명: " + part.getSubmittedFileName() + "</div>");
        out.println("<div>저장파일명: " + filename + "</div>");
        out.println("<hr>");

        //---- 업로드 디렉터리에 저장된 모든 파일  가져와서 응답
        out.print("<div>저장된 파일 목록</div>");
        File[] files = dir.listFiles();
        for (File file : files) {
            String storedFilename = file.getName(); //---- 타임스탬프 + 밑줄 + 첨부된 파일명
            String originFilename = storedFilename.substring(storedFilename.indexOf("_") + 1);  //---- 첨부된 파일명
            out.println("<div><a href=\"/servlet_war_exploded/DownloadFile?filename="+ URLEncoder.encode(storedFilename,"UTF-8")+ "\">"+ originFilename + "</a></div>");
        }

        out.close();
    }


}
