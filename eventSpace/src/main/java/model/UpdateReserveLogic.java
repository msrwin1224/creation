package model;

import dao.ReserveDAO;

public class UpdateReserveLogic {
	public void Update(String id, String date, String timeStart, String timeEnd, String purpose) {
		int uId = Integer.parseInt(id);
		ReserveDAO reserve = new ReserveDAO();
		reserve.update(uId, date, timeStart, timeEnd, purpose);
	}
}
