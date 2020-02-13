package com.example.domain;

/**
 * コメントを表すドメイン.
 * @author tanaami
 *
 */
public class Comment {

	
	/**
	 * ID
	 */
	private Integer id;
	/**
	 * 名前
	 */
	private String name;
	/**
	 * コメント内容
	 */
	private String content;
	/**
	 * 記事ID
	 */
	private Integer artcleId;
	
	public Comment() {
		
	}
	
	public Comment(Integer id, String name, String content, Integer artcleId) {
		super();
		this.id = id;
		this.name = name;
		this.content = content;
		this.artcleId = artcleId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getArtcleId() {
		return artcleId;
	}

	public void setArtcleId(Integer artcleId) {
		this.artcleId = artcleId;
	}

	@Override
	public String toString() {
		return "CommentDomain [id=" + id + ", name=" + name + ", content=" + content + ", artcleId=" + artcleId + "]";
	}
	
	
	
}
