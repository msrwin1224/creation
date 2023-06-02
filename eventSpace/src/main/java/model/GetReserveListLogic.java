package model;

import java.util.List;

import dao.ReserveDAO;

public class GetReserveListLogic {
	public List<Reserve> execute() {
		ReserveDAO dao = new ReserveDAO();
		List<Reserve> reserveList = dao.findAll();

		return reserveList;
	}
}
