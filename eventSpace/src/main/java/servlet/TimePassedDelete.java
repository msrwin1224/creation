package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DeleteReserveLogic;

@WebServlet("/TimePassedDelete")
public class TimePassedDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DeleteReserveLogic delML = new DeleteReserveLogic();
		int tPD = delML.timePassedDelete();
		if (tPD > 0) {
			HttpSession session = request.getSession();
			session.setAttribute("tPDMsg", "終了したイベント" + tPD + "件を削除しました");
			response.sendRedirect("/eventSpace/Main");
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("tPDMsg", "最適化は必要ありません");
			response.sendRedirect("/eventSpace/Main");
		}
	}
}
