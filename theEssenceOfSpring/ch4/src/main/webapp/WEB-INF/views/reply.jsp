<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<script src="https://code.jquery.com/jquery-1.11.3.js"></script>
	<title>Reply</title>
	<style>
	* {
		border : 0;
		padding : 0;
	}
	
	ul {
		border : 1px solid rgb(235, 236, 239);
		border-bottom: 0;
	}
	
	li {
		background-color : #f9f9fa;
		list-style-type : none;
		border-bottom : 1px solid rgb(235, 236, 239);
		padding : 18px 18px 0 18px;
	}
	
	#replyList {
		width : 50%;
		margin : auto;
	}
	
	.reply-content {
		overflow-wrap : break-word;
	}
	.reply-bottom {
		font-size : 9pt;
		color : rgb(97, 97, 97);
		padding : 8px 0 8px 0;
	}
	.reply-bottom > a {
		color : rgb(97, 97, 97);
		text-decoration : none;
		margin : 0 6px 0 0;
	}
	.reply-area {
		padding : 0 0 0 46px;
	}
	.replier {
		font-size : 12pt;
		font-weight : bold;
	}
	.replier-writebox {
		padding : 15px 20px 20px 20px;
	}
	.reply-img {
		font-size : 36px;
		position : absolute;
	}
	.reply-item {
		position : relative;
	}
	.up_date {
		margin : 0 8px 0 0;
	}
	#reply-writebox {
		background-color : white;
		border : 1px solid #e5e5e5;
		border-radius : 5px;
	}
	#reply-writebox-bottom {
		padding : 3px 10px 10px 10px;
		min-height : 35px;
	}
	
	textarea {
		display : block;
		width : 100%;
		min-height : 17px;
		padding : 0 20px;
		border : 0;
		outline : 0;
		font-size : 13px;
		resize : none;
		box-sizing : border-box;
		background : transparent;
		overflow-wrap : break-word;
		overflow-x : hidden;
		overflow-y : auto;
	}
	
	.btn {
		font-size : 10pt;
		color : black;
		background-color : #eff0f2;
		padding : 9px 10px 9px 10px;
		border-radius : 5px;
		float : right;
	}
	
	#btn-write-reply, #btn-write-nestedReply {
		color : #009f47;
		background-color : #e0f8eb;
	}
	#btn-cancel-nestedReply {
		background-color : #eff0f2;
		margin-right : 10px;
	}
	#btn-write-modify {
		color : #009f47;
		background-color : #e0f8eb;
	}
	#btn-cancel-modify {
		margin-right : 10px;
	}
	
	#nestedReply-writebox {
		display : none;
		background-color : white;
		border : 1px solid #e5e5e5;
		border-radius : 5px;
		margin : 10px;
	}
	#nestedReply-writebox-bottom {
		padding : 3px 10px 10px 10px;
		min-height : 35px;
	}
	#modify-writebox {
		background-color : white;
		border : 1px solid #e5e5e5;
		border-radius : 5px;
		margin : 10px;
	}
	#modify-writebox-bottom {
		padding : 3px 10px 10px 10px;
		min-height : 35px;
	}
	
	/* The Modal (background) */
	.modal {
		display : none;	/* Hidden by default */
		posiiton : fixed;	/* Stay in place */
		z-index : 1;	/* Sit on top */
		padding-top : 100px;	/* Location of the box */
		left : 0;
		top : 0;
		width : 100%;	/* Full width */
		height : 100%;	/* Full height */
		overflow : auto;	/* Enable scroll if needed */
		background-color : rgb(0, 0, 0);	/* Fallback color */
		background-color : rgba(0, 0, 0, 0.4);	/* Black w/ opacity */
	}
	
	
	</style>
</head>
<body>

</body>
</html>