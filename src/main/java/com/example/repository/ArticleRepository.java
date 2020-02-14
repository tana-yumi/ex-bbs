package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Article;

/**
 * articlesテーブルを操作するリポジトリ.
 * @author tanaami
 *
 */
@Repository
public class ArticleRepository {

	@Autowired
	private NamedParameterJdbcTemplate template; 
	
	private static final RowMapper<Article> Article_Row_MAPPER = (rs, i) -> {
		Article article = new Article();
		article.setId(rs.getInt("id"));
		article.setName(rs.getString("name"));
		article.setContent(rs.getString("content"));
		//article.setCommentList(rs.getString("commentList"));
		return article;
	};
	
	/**
	 * 記事の全件検索を行うメソッド.
	 * @return 記事一覧
	 */
	public List<Article> findAll(){
		String sql = "SELECT id, name, content From articles ORDER BY id DESC";
		List<Article> articleList = template.query(sql, Article_Row_MAPPER);
		return articleList;
	}

	/**
	 * 記事を投稿するメソッド.
	 * 
	 * @param article 記事
	 */
	public void insert(Article article) {
		String sql = "INSERT INTO articles (name, content) VALUES (:name, :content)";
		SqlParameterSource param = new BeanPropertySqlParameterSource(article);
		template.update(sql, param);
	}
	
	/**
	 * 記事削除メソッド.
	 * @param id 記事ID
	 */
	public void deleteById(int id) {
		String sql = "DELETE FROM articles WHERE id = :id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		template.update(sql, param);
	}
	

}
