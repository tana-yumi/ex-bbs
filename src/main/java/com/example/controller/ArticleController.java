package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.form.ArticleForm;
import com.example.form.CommentForm;
import com.example.repository.ArticleRepository;
import com.example.repository.CommentRepository;

/**
 * 記事投稿関連機能の制御を行うコントローラー.
 * 
 * @author tanaami
 *
 */
@Controller
@RequestMapping("/bbs")
public class ArticleController {

	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private CommentRepository commentRepository;

	@ModelAttribute
	public ArticleForm setUpArticleForm() {
		return new ArticleForm();
	}

	@ModelAttribute
	public CommentForm setUpCommentForm() {
		return new CommentForm();

	}

	
	/**
	 * 記事を投稿するメソッド.
	 * 
	 * @param form  リクエストパラメータを受け取るフォーム
	 * @param model リクエストスコープ
	 * @return 投稿した記事を表示
	 */
	@RequestMapping("/insertArticle")
	public String insertArticle(ArticleForm form, Model model) {
		Article article = new Article();
		if (form.getName().equals("") && form.getContent().equals("")) {

		} else {
			article.setName(form.getName());
			article.setContent(form.getContent());
			System.out.println(article);
			articleRepository.insert(article);
//			model.addAttribute("article", article);
		}
		return "redirect:/bbs";
	}
	
//	@RequestMapping("/index2")
//	public String index2(Model model) {
//		return index(model);
//	}
	
	/**
	 * 記事一覧を表示するメソッド.
	 * 
	 * @param model リクエストスコープ
	 * @return 記事一覧画面へ遷移.
	 */
	@RequestMapping("")
	public String index(Model model) {
		List<Article> articleList = articleRepository.findAll();
		
		for (Article article : articleList) {
			List<Comment> commentList = null;
			commentList = commentRepository.findByArticleID(article.getId());
			article.setCommentList(commentList);
		}
		model.addAttribute("articleList", articleList);
		return "bbs";
	}

	/**
	 * 記事投稿IDに寄せられたコメントを表示するメソッド.
	 * 
	 * @param form  リクエストパラメータを受け取るフォーム
	 * @param model リクエストスコープ
	 * @return コメントを表示する画面へ遷移
	 */
	@RequestMapping("/findByArticleID")
	public String findByArticleID(CommentForm form, Model model) {
		List<Comment> commentList = null;
		commentList = commentRepository.findByArticleID(Integer.parseInt(form.getArticleId()));
		System.out.println(commentList);
		model.addAttribute("commentList", commentList);
		return "bbs";
	}

	/**
	 * コメントを投稿するメソッド.
	 * 
	 * @param form  リクエストパラメータを受け取るフォーム
	 * @param model リクエストスコープ
	 * @return 投稿したコメントを表示する画面へ遷移
	 */
	@RequestMapping("/insertComment")
	public String insertComment(CommentForm form, Model model) {
		Comment comment = new Comment();
		comment.setName(form.getName());
		comment.setContent(form.getContent());
		comment.setArticleId(Integer.parseInt(form.getArticleId()));
		commentRepository.insert(comment);
//		model.addAttribute("comment",comment);
		return "redirect:/bbs";
	}

//	@RequestMapping("/indexComment")
//	public String indexComment(Model model) {
//		return index(model);
//	}

	
	/**
	 * 記事と削除する記事に紐づいたコメントの両方を削除するメソッド.
	 * 
	 * @param articleId 記事ID
	 * @return 削除された画面へ遷移
	 */
	@RequestMapping("/deleteByArticle")
	public String deleteByArticle(Integer articleId) {
		System.out.println(articleId);
		commentRepository.deleteByArticleID(articleId);
		articleRepository.deleteById(articleId);
//		return index(model);
		return "redirect:/bbs";
	}
}
