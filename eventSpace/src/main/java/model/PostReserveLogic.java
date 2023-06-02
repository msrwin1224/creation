package model;

import java.text.SimpleDateFormat;
import java.util.Date;

import dao.ReserveDAO;

public class PostReserveLogic {

	public void execute(Reserve reserve) {
		ReserveDAO rDao = new ReserveDAO();
		rDao.create(reserve);
	}

	//時間ダブりチェック
	public boolean timeCheckDoubling(String date, String timeStart, String timeEnd) {
		ReserveDAO rDao = new ReserveDAO();

		return rDao.timeCheckDoubling(date, timeStart, timeEnd);
	}

	//日付経過チェック
	public boolean dayCk(String day) {
		int date = Integer.parseInt(day.replaceAll("-", ""));
		Date today = new Date();
		SimpleDateFormat DateSetFormat = new SimpleDateFormat("yyyyMMdd");
		int todaySdf = Integer.parseInt(DateSetFormat.format(today));

		if (date >= todaySdf) {
			return true;
		}
		return false;
	}

	//登録時間未来チェック
	public boolean timeCheck(String time, String timeStart, String timeEnd) { //登録日,開始時間,終了時間
		Date date = new Date(); //今の日時の取得
		SimpleDateFormat dateSetFormat = new SimpleDateFormat("yyyyMMddHHmm"); //日時フォーマットの指定
		long nowTime = Long.parseLong(dateSetFormat.format(date)); //今の日時にフォーマットを適用してLong型に変換
		String StrTimeStart = time.replace("-", "") + timeStart.replace(":", ""); //整数に変換する為に記号を省く
		String StrTimeEnd = time.replace("-", "") + timeEnd.replace(":", "");
		long LongTimeStart = Long.parseLong(StrTimeStart); //登録する日時を日付+日時にしてLong型に変換
		long LongTimeEnd = Long.parseLong(StrTimeEnd);

		if (nowTime <= LongTimeStart && nowTime <= LongTimeEnd && LongTimeStart < LongTimeEnd) { //整数にした日時で真偽判定
			return true; //今の日付より登録する日が未来&開始より終了が後の場合は真を返す
		}
		return false;
	}
}