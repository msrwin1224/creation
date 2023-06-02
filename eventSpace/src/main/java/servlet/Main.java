package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GetReserveListLogic;
import model.PostReserveLogic;
import model.Reserve;
import model.User;

@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		GetReserveListLogic gRLL = new GetReserveListLogic();
		List<Reserve> reserveList = gRLL.execute();
		ServletContext application = this.getServletContext();
		application.setAttribute("reserveList", reserveList);
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");

		if (loginUser == null) {
			response.sendRedirect("/eventSpace/");
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		PostReserveLogic pRL = new PostReserveLogic();
		String date = request.getParameter("date");
		String timeStart = request.getParameter("timeStart");
		String timeEnd = request.getParameter("timeEnd");
		String purpose = request.getParameter("purpose");
		purpose = purpose.replaceAll("　", ""); //全角スペースを取り除く
		purpose = purpose.replaceAll(" ", ""); //半角スペースを取り除く

		boolean dayCk = pRL.dayCk(date);
		boolean timeCheck = pRL.timeCheck(date, timeStart, timeEnd);

		if (dayCk && timeCheck) {
			if (purpose != null && purpose.length() != 0) { //未入力は弾く
				HttpSession session = request.getSession();
				User loginUser = (User) session.getAttribute("loginUser");
				Reserve reserve = new Reserve(loginUser.getId(), 0, loginUser.getName(), date, timeStart, timeEnd,
						purpose);

				if (pRL.timeCheckDoubling(date, timeStart, timeEnd)) {
					pRL.execute(reserve);
					request.setAttribute("okMsg", "※登録が完了しました。");
				} else {
					request.setAttribute("errorMsg", "※登録時間が重複しています。注意してください。");
					pRL.execute(reserve); //ここの行を削除すると時間重複を無効化する
				}
			} else {
				request.setAttribute("errorMsg", "※使用理由が入力されていません。");
			}
		} else {
			request.setAttribute("errorMsg", "※過去の日時が入力されました。");
		}
		GetReserveListLogic getReserveListLogic = new GetReserveListLogic();
		List<Reserve> reserveList = getReserveListLogic.execute();
		ServletContext application = this.getServletContext();
		application.setAttribute("reserveList", reserveList);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);
	}
}