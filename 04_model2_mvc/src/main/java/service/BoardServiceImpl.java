package service;

import controller.ActionForward;
import dao.BoardDao;
import dao.BoardDaoImpl;
import model.dto.BoardDTO;
import model.dto.UserDTO;

import javax.servlet.http.HttpServletRequest;
import javax.swing.*;

public class BoardServiceImpl implements BoardService {
    //---- BoardDao 타입의 BoardDaoImpl 객체 생성
    private BoardDao boardDao = BoardDaoImpl.getInstance();

    @Override
    public ActionForward getBoards(HttpServletRequest request) {
        //---- SELECT 결과 저장
        request.setAttribute("boards",boardDao.getBoards());

        //---- list.jsp로 forward(request를 전달하므로 request에 저장한 값도 전달됩니다.)
        return new ActionForward("/board/list.jsp", false); 	//forward
    }

    @Override
    public ActionForward getBoardById(HttpServletRequest request) {
        //---- 요청 파라미터 bid 처리 (bid 파라미터 전달이 없거나 정수가 아닌 값이 전달되면 bid = 0 으로 처리)
        int bid = 0;
        try {
            bid = Integer.parseInt(request.getParameter("bid"));
        } catch (Exception e) {
            bid = 0;
        }
        //---- bid 값을 가진 SELECT 결과를 저장
        request.setAttribute("board", boardDao.getBoardById(bid));

        //---- 요청 파라미터 code
        String code = request.getParameter("code");

        //---- code에 따른 ActionForward 객체 선언 및 생성
        ActionForward af = null;
        switch (code) {
            case "detail" :
                af = new ActionForward("/board/detail.jsp", false);
                break;
            case "modify" :
                af = new ActionForward("/board/modify.jsp", false);
                break;
            default:
                af = new ActionForward(request.getContextPath() + "/main.do", true);  //--- 확인 필요


        }
        //---- ActionForward 객체 변환
        return af;
    }

    @Override
    public ActionForward registBoards(HttpServletRequest request) {

        //---- form에서 전달된 uid ,title, content 받기
        int uid = Integer.parseInt(request.getParameter("uid"));
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        //---- uid, title, content 값으로 BoardDTO 객체 생성
        BoardDTO board = new BoardDTO();
        UserDTO user = new UserDTO();
        user.setUid(uid);
        board.setUser(user);
        board.setTitle(title);
        board.setContent(content);

        //---- 등록
        int count = boardDao.insertBoard(board);

        //---- 등록 결과에 따라 이동할 경로 결정
        String view = null;
        if (count == 1) {
            view = "/board/list.do";
        } else {
            view = "/board/registForm.do";
        }

        return new ActionForward( request.getContextPath() + view, true);
    }

    @Override
    public ActionForward modifyBoard(HttpServletRequest request) {
        return null;
    }

    @Override
    public ActionForward removeBoard(HttpServletRequest request) {
        //---- 요청 파라미터 bid 처리 (bid 파라미터 전달이 없거나 정수가 아닌 값이 전달되면 bid = 0 으로 처리)
        int bid = 0;
        try {
            bid = Integer.parseInt(request.getParameter("bid"));
        } catch (Exception e) {
            bid = 0;
        }
        //----- 삭제
        int count = boardDao.deleteBoard(bid);

        //---- 삭제 결과에 따라 이동할 경로 결정
        String view = null;
        if (count == 1) {
            view = "/board/list.do";
        } else {
            view = "/main.do";
        }
        return new ActionForward(request.getContextPath() + view, true);  //---- 확인 필요;
    }
}
