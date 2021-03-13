<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	pageContext.setAttribute("path", request.getContextPath());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>表单</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/css/bootstrap.min.css">
</head>

<body>
	<div class="container">
		<div class="row">
			<div class="col-xl-12">
				<h1>员工信息管理系统</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-xl-4 offset-xl-8">
				<button class="btn btn-primary">添加</button>
				<button class="btn btn-danger">删除</button>
			</div>
		</div>
		<div class="row">
			<div class="col-xl-12">
				<table class="table table-hover" id="emps_table">
					<thead class="thead-dark">
						<tr>
							<th>id</th>
							<th>员工姓名</th>
							<th>薪资/月</th>
							<th>年龄</th>
							<th>操作</th>
						</tr>
					</thead>
					<c:forEach items="${ pageInfo.list }" var="emps">
						<tbody>
							<tr>
								<th>${ emps.empId }</th>
								<th>${ emps.name }</th>
								<th>${ emps.salary }</th>
								<th>${ emps.age }</th>
								<th>操作</th>
							</tr>
						</tbody>
					</c:forEach>
				</table>
			</div>
		</div>
		<div class="row">
			<div class="col-xl-6">当前记录数：${pageInfo.total}条</div>
			<div class="col-xl-6">
				<nav aria-label="Page navigation example">
					<ul class="pagination">
						<c:if test="${pageInfo.hasPreviousPage }">
							<li class="page-item"><a class="page-link"
								href="${ path }/emps?page=${ pageInfo.pageNum-1 }"
								aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
							</a></li>
						</c:if>
						<c:forEach items="${ pageInfo.navigatepageNums }" var="pageNumber">
							<!-- 判断pageNumber是否是当前页码 -->
							<c:if test="${ pageNumber == pageInfo.pageNum }">
								<li class="page-item active"><a class="page-link" href="#">${ pageNumber }</a></li>
							</c:if>
							<c:if test="${ pageNumber != pageInfo.pageNum }">
								<li class="page-item"><a class="page-link"
									href="${ path }/emps?page=${ pageNumber }">${ pageNumber }</a></li>
							</c:if>
						</c:forEach>
						<c:if test="${pageInfo.hasNextPage }">
							<li class="page-item"><a class="page-link"
								href="${ path }/emps?page=${ pageInfo.pageNum+1 }"
								aria-label="Next"> <span aria-hidden="true">&raquo;</span>
							</a></li>
						</c:if>

					</ul>
				</nav>
			</div>
		</div>
	</div>

	<script
		src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js">
	</script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/uxl/popper.min.js "></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/js/bootstrap.min.js "></script>

	<script type="text/javascript">
		$(function() {
			$.ajax({
				url : "http://localhost:100/staffManage/getemps?page=1",
				type : "GET",
				success : function(result) {
					console.log(result);
				}
			});
			console.log('111');
		});
	</script>
</body>
</html>