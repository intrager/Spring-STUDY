<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page session="true" %>
<c:set var="loginId" value="${sessionScope.id}"/>
<c:set var="loginOutLink" value="${loginId=='' ? '/login/login' : '/login/logout'}"/>
<c:set var="loginOut" value="${loginId=='' ? 'Login' : 'ID='+=loginId}"/>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>스프링의 정석</title>
		<link rel="stylesheet" href="<c:url value='/css/menu.css'/>">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<script src="https://code.jquery.com/jquery-1.11.3.js"></script>
		<style>
			* {
				box-sizing: border-box;
				margin: 0;
				padding: 0;
				font-family: "Noto Sans KR", sans-serif;
			}
			a {
				text-decoration: none;
				color: black;
			}
			button, input {
				border: none;
				outline: none;
			}
			.board-container {
				width: 60%;
				height: 1200px;
				margin: 0 auto;
				/* border: 1px solid black; */
			}
			table {
				border-collapse: collapse;
				width: 100%;
				border-top: 2px solid rgb(39, 39, 39);
			}
			tr:nth-child(even) {
				background-color: #f0f0f070;
			}
			th, td {
				width: 300px;
				text-align: center;
				padding: 10px 12px;
				border-bottom: 1px solid #ddd;
			}
			td {
				color: rgb(53, 53, 53);
			}
			
			.no { width: 150px; }
			.title { width: 50%; }
			
			td.title { text-align: left; }
			td.writer { text-align: left; }
			td.viewcnt { text-align: right; }
			
			td.title:hover {
				text-decoration: underline;
			}
			.paging {
				color: black;
				width: 100%;
				align-items: center;
			}
			.page {
				color: black;
				padding: 6px;
				margin-right: 10px;
			}
			.paging-active {
				background-color: rgb(216, 216, 216);
				border-radius: 5px;
				color: rgb(24, 24, 24);
			}
			.paging-container {
				width: 100%;
				height: 70px;
				display: flex;
				margin-top: 50px;
				margin: auto;
			}
			.btn-write {
				background-color: rgb(236, 236, 236);	/* Blue background */
				border: none;	/* Remove borders */
				color: black;	/* White text */
				padding: 6px 12px;	/* Some padding */
				font-size: 16px;	/* Set a font size */
				cursor: pointer;	/* Mouse pointer on hover */
				border-radius: 5px;
				margin-left: 30px;
			}
			.btn-write:hover {
				text-decoration: underline;
			}			
		</style>
	</head>
	<body>
		<div id="menu">
			<ul>
				<li id="logo">fastCampus</li>
				<li><a href="<c:url value='/'/>">Home</a></li>
				<li><a href="<c:url value='/board/list'/>">Board</a></li>
				<li><a href="<c:url value='${loginOutLink}'/>">${loginOut}</a></li>
				<li><a href="<c:url value='/register/add'/>">Sign in</a></li>
				<li><a href=""><i class="fa fa-search"></i></a></li>
			</ul>
		</div>
		<script>
			let msg = "${msg}";
			if(msg == 'write_success') alert("성공적으로 등록되었습니다.");
			
			if(msg == "list_err") alert("게시물 목록을 가져오는데 실패했습니다. 다시 시도해주세요");
			if(msg == "read_err") alert("삭제되었거나 없는 게시물입니다.");
			
			if(msg == "modify_success") alert("성공적으로 수정되었습니다.");
			
			if(msg == "delete_err") alert("삭제 중 오류가 발생했습니다.");
			if(msg == "delete_success") alert("성공적으로 삭제되었습니다.");
		</script>
		<div style="text-align:center">
			<div class="board-container">
				<button id="writeBtn" class="btn-write" onclick="location.href='<c:url value="/board/write"/>'"><i class="fa fa-pencil"></i> 글쓰기</button>
				<table border="1">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>이름</th>
						<th>등록일</th>
						<th>조회수</th>
					</tr>
					<c:forEach var="boardDto" items="${list}">
						<tr>
							<td class="no">${boardDto.bno}</td>
							<td class="title"><a href="<c:url value='/board/read?bno=${boardDto.bno}&page=${page}&pageSize=${pageSize}'/>">${boardDto.title}</a></td>
							<td class="writer">${boardDto.writer}</td>
							<td class="regdate">${boardDto.reg_date}</td>
							<td class="viewcnt">${boardDto.view_cnt}</td>
						</tr>
					</c:forEach>
				</table>
				<br>
				<div class="paging-container">
					<div class="paging">
						<c:if test="${ph.showPrev}">
							<a href="<c:url value='/board/list?page=${ph.beginPage-1}&pageSize=${ph.pageSize}'/>">&lt;</a>
						</c:if>
						<c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}">
							<a href="<c:url value='/board/list?page=${i}&pageSize=${ph.pageSize}'/>">${i}</a>
						</c:forEach>
						<c:if test="${ph.showNext}">
							<a href="<c:url value='/board/list?page=${ph.endPage+1}&pageSize=${ph.pageSize}'/>">&gt;</a>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>