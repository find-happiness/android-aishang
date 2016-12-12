package com.aishang.app.data.model;

import java.util.List;

/**
 * Created by song on 2016/9/14.
 */
public class JMyVacationApplyParam {

  private String memberAccount;

  private String cookie;
  private String hotelName;
  private String startDate;
  private String endDate;
  private String guestPhone;
  private String guestName;
  private String guestComment;
  private String memberGUID;
  int hotelID;
  int priceID;
  int rooms;
  int roomCatID;
  int creditByCard;
  int cashBySelf;
  int guestCount;
  int payType;
  String giftcardGUID;
  int source;

  List<Card> useCardList;

  public String getMemberAccount() {
    return memberAccount;
  }

  public void setMemberAccount(String memberAccount) {
    this.memberAccount = memberAccount;
  }

  public String getCookie() {
    return cookie;
  }

  public void setCookie(String cookie) {
    this.cookie = cookie;
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

  public String getEndDate() {
    return endDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  public String getGuestPhone() {
    return guestPhone;
  }

  public void setGuestPhone(String guestPhone) {
    this.guestPhone = guestPhone;
  }

  public String getGusetName() {
    return guestName;
  }

  public void setGusetName(String gusetName) {
    this.guestName = gusetName;
  }

  public String getGuestComment() {
    return guestComment;
  }

  public void setGuestComment(String guestComment) {
    this.guestComment = guestComment;
  }

  public String getMemberGUID() {
    return memberGUID;
  }

  public void setMemberGUID(String memberGUID) {
    this.memberGUID = memberGUID;
  }

  public int getHotelID() {
    return hotelID;
  }

  public void setHotelID(int hotelID) {
    this.hotelID = hotelID;
  }

  public int getPriceID() {
    return priceID;
  }

  public void setPriceID(int priceID) {
    this.priceID = priceID;
  }

  public int getRooms() {
    return rooms;
  }

  public void setRooms(int rooms) {
    this.rooms = rooms;
  }

  public int getRoomCatID() {
    return roomCatID;
  }

  public void setRoomCatID(int roomCatID) {
    this.roomCatID = roomCatID;
  }

  public int getCreditByCard() {
    return creditByCard;
  }

  public void setCreditByCard(int creditByCard) {
    this.creditByCard = creditByCard;
  }

  public int getCashBySelf() {
    return cashBySelf;
  }

  public void setCashBySelf(int cashBySelf) {
    this.cashBySelf = cashBySelf;
  }

  public int getGuestCount() {
    return guestCount;
  }

  public void setGuestCount(int guestCount) {
    this.guestCount = guestCount;
  }

  public int getPayType() {
    return payType;
  }

  public void setPayType(int payType) {
    this.payType = payType;
  }

  public String getGiftcardGUID() {
    return giftcardGUID;
  }

  public void setGiftcardGUID(String giftcardGUID) {
    this.giftcardGUID = giftcardGUID;
  }

  public int getSource() {
    return source;
  }

  public void setSource(int source) {
    this.source = source;
  }

  public List<Card> getUseCardList() {
    return useCardList;
  }

  public void setUseCardList(List<Card> useCardList) {
    this.useCardList = useCardList;
  }

  public class Card {
    int cardID;
    int credit2Use;
    private String cardValidateYear;

    public int getCardID() {
      return cardID;
    }

    public void setCardID(int cardID) {
      this.cardID = cardID;
    }

    public int getCredit2Use() {
      return credit2Use;
    }

    public void setCredit2Use(int credit2Use) {
      this.credit2Use = credit2Use;
    }

    public String getCardValidateYear() {
      return cardValidateYear;
    }

    public void setCardValidateYear(String cardValidateYear) {
      this.cardValidateYear = cardValidateYear;
    }
  }
}
