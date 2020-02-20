<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<script type="text/javascript" src="/resource/bootstrap/js/bootstrap.js"></script>

<!-- <div class="table-responsive"> -->
  <table class="table table-striped">
    <caption>友情链接</caption>
    <thead>
      <tr >
        <th>ID</th>
        <th>名称</th>
        <th>地址</th>
        <th>创建时间</th>
        <th>操作    <input type="button" value="添加" onclick="add()">
        </th>
       </tr>
    </thead>
    <tbody>
    	<c:forEach items="${info.list}"  var="link">
    	  <tr>
	        <td>${link.id}</td>
	        <td>${link.name}</td>
	        <td>${link.url}</td>
	        <td><fmt:formatDate pattern="YYYY年MM月dd号 HH:mm:ss" value="${link.created}"></fmt:formatDate></td>
	         <td> <%-- <input type="button" value="修改" onclick="update(${link.id})"> --%>
	             &nbsp;
	              <input type="button" value="删除" onclick="del(${link.id})">
	         </td>
	      </tr>
      </c:forEach>
  </table>

<ul class="pagination">
    <li><a href="javascript:goPage(${info.prePage})">&laquo;</a></li>
    <c:forEach begin="${info.pageNum-2 > 1 ? info.pageNum-2:1}" end="${info.pageNum+2 > info.pages ? info.pages:info.pageNum+2}" varStatus="index">    		
    	<c:if test="${info.pageNum!=index.index}">
    		<li><a href="javascript:goPage(${index.index})">${index.index}</a></li>
    	</c:if>
    	<c:if test="${info.pageNum==index.index}">
    		<li><a href="javascript:void"><strong> ${index.index} </strong> </a></li>
    	</c:if>
    	
    </c:forEach>
    <li><a href="javascript:goPage(${info.nextPage})">&raquo;</a></li>
</ul>

<script type="text/javascript">
	//列表展示
	function goPage(page){
		var url="/link/list?page="+page;
		$("#content").load(url);
	}
	//添加
	function add(){
		$("#content").load("/link/add")
	}
	
	//刷新当前页
	function refresh(){
		
		var url="/link/list?page=${info.pageNum}";
		$("#content").load(url);
	}
	//删除
	function del(id) {
		$.post(
			"/link/delete",
			{id:id},
			function(flag){
				if(flag>0) {
					alert("删除成功");
					refresh();
				}else{
					alert(msg.errorMsg);
				}
			},
			"json"
		)
	}
	//跳转到修改链接页面
	function update(id){
		$("#content").load("/link/update?id="+id)
	}
	
</script>
