package model;

import dao.ReserveDAO;

public class DeleteReserveLogic {

	public void delete(String strId) {
		ReserveDAO mDAO = new ReserveDAO();
		mDAO.delete(strId);
	}

	public int timePassedDelete() {
		ReserveDAO mDAO = new ReserveDAO();
		int result = mDAO.timePassedDelete();
		return result;
	}
}
