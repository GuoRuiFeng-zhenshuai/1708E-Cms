/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: ArticleMapper.java 
 * @Prject: zhaojian-cms
 * @Package: com.guoruifeng.dao 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月14日
 * @version: V1.0   
 */
package com.guoruifeng.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.guoruifeng.beans.Article;
import com.guoruifeng.beans.Comment;

/** 
 * @ClassName: ArticleMapper 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月14日 
 */
public interface ArticleMapper {
	/** 
	 * @Title: newList 
	 * @Description: 获取最新文章
	 * @param i 获取的个数
	 * @return
	 * @return: List<Article>
	 */
	List<Article> newList(int i);
	
	
	/** 
	 * @Title: hostList 
	 * @Description: 获取热门文章
	 * @return
	 * @return: List<Article>
	 */
	List<Article> hostList();


	/** 
	 * @Title: listByCat 
	 * @Description: 根据频道获取文章
	 * @param chnId
	 * @param categoryId
	 * @return
	 * @return: List<Article>
	 */
	List<Article> listByCat(@Param("chnId")int chnId, @Param("categoryId")int categoryId);


	/** 
	 * @Title: getById 
	 * @Description: 根据id获取文章
	 * @param id
	 * @return
	 * @return: Article
	 */
	Article getById(Integer id);


	/** 
	 * @Title: listByUser 
	 * @Description: 获取文章列表
	 * @param userId
	 * @return
	 * @return: List<Article>
	 */
	List<Article> listByUser(Integer userId);


	/** 
	 * @Title: delete 
	 * @Description: 管理员后台页面，管理员审核后逻辑删除需要删除的文章(不是在数据库真的删除，只是在前台做一个假的删除)
	 * @param id
	 * @return
	 * @return: int
	 */
	@Update(" UPDATE cms_article SET  deleted=1 WHERE id=#{value} ")
	int delete(int id);


	/** 
	 * @Title: listByStatus 
	 * @Description: 管理员根据状态查询文章
	 * @param status
	 * @return
	 * @return: List<Article>
	 */
	List<Article> listByStatus(int status);


	/** 
	 * @Title: getDetailById 
	 * @Description: 获取文章详情，不考虑状态
	 * @param id
	 * @return
	 * @return: Article
	 */
	Article getDetailById(int id);


	/** 
	 * @Title: checkExist 
	 * @Description: 判断文章是否存在
	 * @param id
	 * @return
	 * @return: Article
	 */
	@Select("SELECT id, title,user_id AS userId FROM cms_article WHERE id = #{value}")
	@ResultType(Article.class)
	Article checkExist(int id);


	/** 
	 * @Title: apply 
	 * @Description: 审核文章
	 * @param id
	 * @param status
	 * @return
	 * @return: int
	 */
	@Update(" UPDATE cms_article SET  status=#{status} "
			+ " WHERE id=#{id} ")
	int apply(@Param("id") int id, @Param("status") int status);


	/** 
	 * @Title: setHot 
	 * @Description: 设置热门
	 * @param id
	 * @param status
	 * @return
	 * @return: int
	 */
	@Update(" UPDATE cms_article SET  hot=#{status} "
			+ " WHERE id=#{id} ")
	int setHot(@Param("id")int id, @Param("status")int status);


	/** 
	 * @Title: add 
	 * @Description: 添加文章
	 * @param article
	 * @return
	 * @return: int
	 */
	@Insert("INSERT INTO cms_article("
			+ " title,content,picture,channel_id,category_id,"
			+ " user_id,hits,hot,status,deleted,"
			+ " created,updated,commentCnt,articleType) "
			+ " values("
			+ " #{title},#{content},#{picture},#{channelId},#{categoryId},"
			+ "#{userId},#{hits},#{hot},#{status},#{deleted},"
			+ "now(),now(),#{commentCnt},"
			+ "#{articleType,typeHandler=org.apache.ibatis.type.EnumOrdinalTypeHandler,"
			+ "jdbcType=INTEGER,javaType=com.guoruifeng.beans.TypeEnum})")
	@Options(useGeneratedKeys=true,keyProperty="id",keyColumn="id")
	int add(Article article);


	/** 
	 * @Title: update 
	 * @Description: 修改文章
	 * @param article
	 * @return
	 * @return: int
	 */
	@Update("UPDATE cms_article SET title=#{title},content=#{content},"
			+ "picture=#{picture},channel_id=#{channelId},"
			+ "category_id=#{categoryId},status=0,updated=now() WHERE id=#{id}")
	int update(Article article);

	@Update("UPDATE cms_article SET hits=#{hits} WHERE id=#{id}")
	int updates(Article article);
	/** 
	 * @Title: favorite 
	 * @Description: 收藏文章
	 * @param userId
	 * @param articleId
	 * @return
	 * @return: int
	 */
	@Insert("REPLACE cms_favorite(user_id,article_id,created)"
			+ "VALUES(#{userId},#{articleId},now())")
	int favorite(@Param("userId")Integer userId, @Param("articleId")int articleId);


	/** 
	 * @Title: getImgArticles 
	 * @Description: 获取10篇图片文章
	 * @param num
	 * @return
	 * @return: List<Article>
	 */
	List<Article> getImgArticles(int num);


	/** 
	 * @Title: myfavoriteById 
	 * @Description: 我的收藏列表
	 * @param id
	 * @return
	 * @return: List<Article>
	 */
	List<Article> myfavoriteById(@Param("id")Integer id);


	/** 
	 * @Title: delFavorite 
	 * @Description: TODO
	 * @param id
	 * @return
	 * @return: int
	 */
	@Delete("DELETE FROM cms_favorite where article_id=${id}")
	int delFavorite(@Param("id")int id);


	/** 
	 * @Title: addComment 
	 * @Description: 添加评论
	 * @param userId
	 * @param articleId
	 * @param content
	 * @return
	 * @return: int
	 */
	@Insert("INSERT INTO cms_comment (articleId,userId,content,created)"
			+ " VALUES(#{articleId},#{userId},#{content},now())")
	int addComment(@Param("userId")Integer userId, 
			@Param("articleId")int articleId, @Param("content")String content);


	/** 
	 * @Title: increaseCommentCnt 
	 * @Description: 评论数目自增一个
	 * @param articleId
	 * @return: void
	 */
	@Update("UPDATE cms_article set commentCnt=commentCnt+1 WHERE id=#{value} ")
	void increaseCommentCnt(int articleId);


	/** 
	 * @Title: commentlist 
	 * @Description: 查看所有评论
	 * @param articleId
	 * @return
	 * @return: List<Comment>
	 */
	@Select("SELECT * FROM cms_comment WHERE articleId=#{value}")
	List<Comment> commentlist(int articleId);


	List<Article> findAll();

	

}
