package com.example.form;

/**
 * 	コメント関連で使用されるフォーム.
 * @author tanaami
 *
 */
public class CommentForm {

	/**
	 * 記事ID
	 */
	private String articleId;
	/**
	 *名前 
	 */
	private String name;
	/**
	 * コンテント
	 */
	private String content;
	
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "CommentForm [articleId=" + articleId + ", name=" + name + ", content=" + content + "]";
	}
	
	
}
