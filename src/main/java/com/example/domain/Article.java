package com.example.domain;

import java.util.List;

/**
 * 記事を表すドメイン.
 * @author tanaami
 *
 */
public class Article {

	
	/**
	 * ID
	 */
	private Integer id;
	/**
	 * 名前
	 */
	private String name;
	/**
	 * 投稿内容
	 */
	private String content;
	/**
	 * コメントリスト
	 */
	private List<Comment> commentList;
	
}
