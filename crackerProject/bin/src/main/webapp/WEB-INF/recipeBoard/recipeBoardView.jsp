<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/index/css/boardCSS/boardViewCSS.css" rel="stylesheet"
	type="text/css" />
<style type="text/css">
#recipeBoardViewForm #commentContentDiv{
	font-size: 7px;
	color: red;
}
</style>
</head>
<body>
<form id="recipeBoardViewForm">

<input type = "hidden" name="seq" id="seq" value="${seq}"/>
<input type = "hidden" name="pg" id ="pg" value="${pg}"/>
<input type="text" id="category" value="레시피"/>

<table width="800" border="1" bordercolor="black" cellspacing="0" cellpadding="5" frame ="hsides" rules = "rows">


				<tr>
					<td colspan="3">
					
						<h4><span id="subjectSpan"></span></h4>
					</td>
				</tr>
				<tr>
					<td width="150" align="center">글번호 : <span id="seqSpan"></span></td>
					<td width="150" align="center">작성자 : <span id="idSpan"></span></td>
					<td width="150" align="center">조회수 : <span id="hitSpan"></span></td>
				</tr>
				<tr>
					<td colspan=3 height="700" valign="top" >
						<div style="width : 100%; height: 100%; overflow: auto;">
							<pre id = "content" style="white-space:pre-line; word-break: break-all; overflow-wrap: anywhere; ">
							</pre> 
						</div>
					</td>
				</tr>
				
		
</table>
	<!-- 댓글 입력창 -->
	<div class="comment_form_div">
		<div>
			댓글입력 : <input type="text" id="commentContent"> <input
				type="button" id="commentBtn" value="댓글입력">
		</div>
		<ul  id="commentInside">
		</ul>
	</div>

	
	
	<!-- 댓글창 내려오는곳  -->
	<div style="border: 1px blue solid;" id="commentInside">
	</div>
</form>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js">
</script>
<script type="text/javascript" src="../js/recipeBoard/recipeBoardView.js"></script>
<script type="text/javascript">
$(function(){
	$.ajax({
		type : 'post',
		url : '/index/recipeBoard/getRecipeBoardView',
		data : 'seq=' + $('input[name=seq]').val(),
		dataType:'json',
		success : function(data){
			//alert(JSON.stringify(data));
			$('#subjectSpan').html(data.subject);
			$('#seqSpan').html(data.seq);
			$('#idSpan').html(data.nickName);
			$('#hitSpan').html(data.hit);
			$('#content').html(data.content); 
			/* if(data.memId == data.boardDTO.id){
				$('#boardViewSpan').show();
			}
			else{
				$('#boardViewSpan').hide();
			} */
		},
		
		error:function(e){
			console.log(e);
		}
	});//ajax
});


</script>
</body>
</html>