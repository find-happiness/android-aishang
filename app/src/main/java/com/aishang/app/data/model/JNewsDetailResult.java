package com.aishang.app.data.model;

/**
 * Created by song on 2016/3/29.
 */
public class JNewsDetailResult {

  /**
   * result : success
   * catID : 2
   * catName :
   * nextNewsID : 0
   * nextNewsTitle :
   * preNewsID : 0
   * preNewsTitle :
   * title : 养生资讯测试
   * dateText : 2016-03-24 09:44:05
   * source : 测试数据
   * content : 内容内容内容
   * hits : 0
   * support : 0
   */

  private String result;
  private int catID;
  private String catName;
  private int nextNewsID;
  private String nextNewsTitle;
  private int preNewsID;
  private String preNewsTitle;
  private String title;
  private String dateText;
  private String source;
  private String content;
  private int hits;
  private int support;
  private String AuthorName;

  public String getAuthorName() {
    return AuthorName;
  }

  public void setAuthorName(String authorName) {
    AuthorName = authorName;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public int getCatID() {
    return catID;
  }

  public void setCatID(int catID) {
    this.catID = catID;
  }

  public String getCatName() {
    return catName;
  }

  public void setCatName(String catName) {
    this.catName = catName;
  }

  public int getNextNewsID() {
    return nextNewsID;
  }

  public void setNextNewsID(int nextNewsID) {
    this.nextNewsID = nextNewsID;
  }

  public String getNextNewsTitle() {
    return nextNewsTitle;
  }

  public void setNextNewsTitle(String nextNewsTitle) {
    this.nextNewsTitle = nextNewsTitle;
  }

  public int getPreNewsID() {
    return preNewsID;
  }

  public void setPreNewsID(int preNewsID) {
    this.preNewsID = preNewsID;
  }

  public String getPreNewsTitle() {
    return preNewsTitle;
  }

  public void setPreNewsTitle(String preNewsTitle) {
    this.preNewsTitle = preNewsTitle;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDateText() {
    return dateText;
  }

  public void setDateText(String dateText) {
    this.dateText = dateText;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public int getHits() {
    return hits;
  }

  public void setHits(int hits) {
    this.hits = hits;
  }

  public int getSupport() {
    return support;
  }

  public void setSupport(int support) {
    this.support = support;
  }
}
