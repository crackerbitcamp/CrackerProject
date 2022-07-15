<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
.write-input-password{
   border: none;
   width:210px;
   font-size: 14px;
   height:30px;
   box-shadow: 1px 1px 3px 1px #dadce0;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="../css/member.css">
<script type="text/javascript" src = "../js/member.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body>
<div id = "memberupdateDiv" style="align-items: center;">
      <input type = "hidden" id = "originalPwd" value="${memberDTO.memberpwd }">
      <form id = "memberUpdateForm">
         <input type = "hidden" value="" name = "memberid">
         <input type = "hidden" value="${memberDTO.membername }" name = "membername">
         <input type = "hidden" id = "memberemail" name = "memberemail" value="${memberDTO.memberemail }">
         <input type = "hidden" name = "membertel" id="UpdateTelcheck" value="${memberDTO.membertel1 }${memberDTO.membertel2}${memberDTO.membertel3}">
         <h2>회원정보 수정</h2>
         <hr style="border: 2px solid">
         <div class="updateForm-input" style=" margin: 18px auto">
            <span style="font-size: 24px;" id = "updateemail">아이디</span>
            <input type = "text" id = "memberemail1"class="updateinput" value="${memberDTO.memberemail}" disabled style="background: none;">
         </div>
         
         <div class="updateForm-input" style=" margin: 18px auto">
            <font size="5">이름 &nbsp;&nbsp;${memberDTO.membername }</font>
         </div>

         <div class="updateForm-input" style=" margin: 18px auto">
            <font size="5">닉네임</font>
            <input type = "text" id="updateNickName" name="membernickname" class="write-input-password" style="margin-left: 150px;" value="${memberDTO.membernickname }">
         </div>
         
         <div class="updateForm-input" style=" margin: 18px auto">
            <span style="font-size: 24px;" id = "updatetel">전화번호</span>
            <input type = "text" id = "updateTel"class="updateinput" value="${memberDTO.membertel1 }${memberDTO.membertel2}${memberDTO.membertel3}" disabled>
            <button type="button" class="btn_ct" id = "telUpdateBtn" onclick="telUpdateForm()">변경</button>
         </div>
         
        <div class="updateForm-input" style=" margin: 18px auto"><font size="5">현재 비밀번호</font>
         <input type = "password" id="originalPwdWirte" class="write-input-password" style="margin-left: 70px;">
         </div>
         
         <div class="updateForm-input" style=" margin: 18px auto">
            <font size="5">새 비밀번호</font>
            <input type = "password" id="updatePwd" name = "memberpwd" class="write-input-password" style="margin-left: 94px;">
         </div>
         
        <div class="updateForm-input" style=" margin: 18px auto">
            <font size="5">비밀번호 다시입력</font>
            <input type = "password" id = "reupdatePwd"class="write-input-password" style="margin-left: 20px;">
         </div>
        <div class="updateForm-input" style=" margin: 18px auto">
         <button class="btn_next" id = "memberUpdate_btn" name="memberUpdate_btn" type="button">수정</button>
         <button class="btn_next" id = "membercancle_btn" name="membercancle_btn" type="button">취소</button>
         </div>
      </form>
   </div>
</body>
</html>