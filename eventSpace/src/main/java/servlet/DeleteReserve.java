package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DeleteReserveLogic;

@WebServlet("/DeleteReserve")
public class DeleteReserve extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String delId = request.getParameter("id");
		DeleteReserveLogic delML = new DeleteReserveLogic();
		delML.delete(delId);
		HttpSession session = request.getSession();
		session.setAttribute("delMsg", "※予約を削除しました");
		response.sendRedirect("/eventSpace/Main");
	}
}
