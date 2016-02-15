package com.aishang.app.data.model;

public class JMyVacationApplyListResult {

	// result:string,
	// totalItem:int,
	// itemList:[{idx:int, reservID:int,
	// hotelName:string, startDate:string, endDate:string,
	// rooms:int, guestCount:int, guestName:string, guestPhone:string,
	// creditByCard:int, cashBySelf:decimal(10,2), status:int,
	// hotelVerifyCode:string, creditTotal:int
	// usedCardList:[ {cardID:string, cardValidateYear:string,
	// creditUsed:int, creditLimit2Use :int }, бн]
	// }

	private String result;
	private int totalItem;
	private JItem[] itemList;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getTotalItem() {
		return totalItem;
	}

	public void setTotalItem(int totalItem) {
		this.totalItem = totalItem;
	}

	public JItem[] getItemList() {
		return itemList;
	}

	public void setItemList(JItem[] itemList) {
		this.itemList = itemList;
	}

	public class JItem {

		private int idx;
		private int reservID;

		private String hotelName;
		private String startDate;
		private String createTime;
		private int rooms;
		private int guestCount;
		private String endDate;
		private String guestName;
		private String guestPhone;
		private int creditByCard;
		private float cashBySelf;
		private int status;
		private String hotelVerifyCode;
		private int creditTotal;
		private JUsedCard[] usedCardList;

		public String getCreateTime() {
			return createTime;
		}

		public void setCreateTime(String createTime) {
			this.createTime = createTime;
		}

		public int getIdx() {
			return idx;
		}

		public void setIdx(int idx) {
			this.idx = idx;
		}

		public int getReservID() {
			return reservID;
		}

		public void setReservID(int reservID) {
			this.reservID = reservID;
		}

		public String getHotelName() {
			return hotelName;
		}

		public void setHotelName(String hotelName) {
			this.hotelName = hotelName;
		}

		public String getStartDate() {
			return startDate;
		}

		public void setStartDate(String startDate) {
			this.startDate = startDate;
		}

		public int getRooms() {
			return rooms;
		}

		public void setRooms(int rooms) {
			this.rooms = rooms;
		}

		public int getGuestCount() {
			return guestCount;
		}

		public void setGuestCount(int guestCount) {
			this.guestCount = guestCount;
		}

		public String getEndDate() {
			return endDate;
		}

		public void setEndDate(String endDate) {
			this.endDate = endDate;
		}

		public String getGuestName() {
			return guestName;
		}

		public void setGuestName(String guestName) {
			this.guestName = guestName;
		}

		public String getGuestPhone() {
			return guestPhone;
		}

		public void setGuestPhone(String guestPhone) {
			this.guestPhone = guestPhone;
		}

		public int getCreditByCard() {
			return creditByCard;
		}

		public void setCreditByCard(int creditByCard) {
			this.creditByCard = creditByCard;
		}

		public float getCashBySelf() {
			return cashBySelf;
		}

		public void setCashBySelf(float cashBySelf) {
			this.cashBySelf = cashBySelf;
		}

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		public String getHotelVerifyCode() {
			return hotelVerifyCode;
		}

		public void setHotelVerifyCode(String hotelVerifyCode) {
			this.hotelVerifyCode = hotelVerifyCode;
		}

		public int getCreditTotal() {
			return creditTotal;
		}

		public void setCreditTotal(int creditTotal) {
			this.creditTotal = creditTotal;
		}

		public JUsedCard[] getUsedCardList() {
			return usedCardList;
		}

		public void setUsedCardList(JUsedCard[] usedCardList) {
			this.usedCardList = usedCardList;
		}

		public class JUsedCard {
			private String cardID;
			private String cardValidateYear;
			private int creditUsed;
			private int creditLimit2Use;

			public String getCardID() {
				return cardID;
			}

			public void setCardID(String cardID) {
				this.cardID = cardID;
			}

			public String getCardValidateYear() {
				return cardValidateYear;
			}

			public void setCardValidateYear(String cardValidateYear) {
				this.cardValidateYear = cardValidateYear;
			}

			public int getCreditUsed() {
				return creditUsed;
			}

			public void setCreditUsed(int creditUsed) {
				this.creditUsed = creditUsed;
			}

			public int getCreditLimit2Use() {
				return creditLimit2Use;
			}

			public void setCreditLimit2Use(int creditLimit2Use) {
				this.creditLimit2Use = creditLimit2Use;
			}

		}
	}

}
