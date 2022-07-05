	/*아이디 찾기*/
function idinfo(){
		url = "/index/member/memberFindIdForm";
		name = '';
		specs = "width=550,height=128,top=200,left=100";
		window.open(url,name,specs);
}
	
$(function(){
		$('#memberLoginId').focusout(function(){
			if(!$('#memberLoginId').val()){
				$('#memberLoginIdDiv').html('아이디를 입력해 주세요');
			}else{
				$('#memberLoginIdDiv').empty();
			}
		});
		
		
		
	$('#memberLoginpwd').focusout(function(){
		if(!$('#memberLoginpwd').val()){
			$('#memberLoginpwdDiv').html('비밀번호를 입력해 주세요');
		}else{
			$('#memberLoginpwdDiv').empty();
		}
	});
	
	$('#memberloginsubmitBtn').click(function(){
		$.ajax({
			data : $('#memberLoginForm').serialize(),
			type : 'post',
			url : '/index/member/memberLoginCheck',
			success:function(data){
				if(!data){
					swal('로그인 실패','아이디 또는 비밀번호가 틀렸습니다. 다시로그인해주세요.','warning')
				}else{
					swal('로그인 완료',""+data.membername+"님 환영 합니다.",'success').then(function(){
						location.href='/index'
					})
				}
			},error:function(e){
				console.log(e);
			}
		});
	});
	
	

	/*회원가입 script*/

    var getCheck= RegExp(/^[a-zA-Z0-9]{4,12}$/);
    var getCheckpwd= RegExp(/^[a-zA-Z0-9]{8,12}$/);
	var getCheckphone = RegExp(/[0-9]{3,4}/);
	
	$('#memberWriteId').focusout(function(){
		if(!$('#memberWriteId').val()){
			$('#memberWriteIdDiv').html('아이디를 입력해 주세요');
			$('#checkId').attr('value','fail');
		}else if(!getCheck.test($('#memberWriteId').val())){
			$('#memberWriteIdDiv').html('4~12자의 영문 대소문자, 숫자만 사용 가능합니다.');
			$('#checkId').attr('value','fail');
		}else{
			$('#checkId').attr('value','ok');
			$.ajax({
				data :{'memberid' : $('#memberWriteId').val()},
				type : 'post',
				url : '/index/member/memberIdCheck',
				success:function(data){
					if(data == 'fail'){
						$('#memberWriteIdDiv').html('아이디가 중복되었습니다. 새로운 아이디를 입력해주세요.');
						$('#checkId').attr('value','fail');
					}else{
						$('#memberWriteIdDiv').empty();
						$('#checkId').attr('value','ok');
					}
				},error:function(e){
					console.log(e);
				}
			});
		}
	});
	
	
	$('#memberWritePwd').focusout(function(){
		if(!$('#memberWritePwd').val()){
			$('#memberWritePwdDiv').html('비밀번호를 입력해 주세요');
			$('#checkPwd').attr('value','fail');
		}else if(!getCheckpwd.test($('#memberWritePwd').val())){
			$('#memberWritePwdDiv').html('8~12자의 영문 대소문자, 숫자만 사용 가능합니다.')
			$('#checkPwd').attr('value','fail');
		}else{
			$('#memberWritePwdDiv').empty();
			$('#checkPwd').attr('value','ok');
		}
	});
	
	$('#memberWriterePwd').focusout(function(){
		if(!$('#memberWriterePwd').val()){
			$('#memberWritePwdDiv').html('재확인 비밀번호를 입력해 주세요');
			$('#checkPwd').attr('value','fail');
		}else if($('#memberWriterePwd').val() != $('#memberWritePwd').val()){
			$('#memberWritePwdDiv').html('비밀번호가 맞지않습니다.');
			$('#checkPwd').attr('value','fail');
		}else if($('#memberWriterePwd').val() == $('#memberWritePwd').val()
				&& !getCheckpwd.test($('#memberWritePwd').val())){
				$('#checkPwd').attr('value','fail');
			$('#memberWritePwdDiv').html('8~12자	의 영문 대소문자, 숫자만 사용 가능합니다.');
		}else{
			$('#memberWritePwdDiv').empty();
			$('#checkPwd').attr('value','ok');
		}
	});
	
	$('#memberWriteName').focusout(function(){
		if(!$('#memberWriteName').val()){
			$('#memberWriteNameDiv').html('이름을 입력해 주세요');
		}else{
			$('#memberWriteNameDiv').empty();
		}
	});
	$('#memberWriteEmail1').focusout(function(){
		if(!$('#memberWriteEmail1').val()){
			$('#memberWriteEmailDiv').html('이메일을 입력하여 주세요.');
		}else{
			$('#memberWriteEmailDiv').empty();
		}
	});
	
	$('#memberWriteEmail2').focusout(function(){
		if(!$('#memberWriteEmail2').val()){
			$('#memberWriteEmailDiv').html('이메일을 입력하여 주세요.');
			$('#emailselect').focus();
		}else{
			$('#memberWriteEmailDiv').empty();
		}
	});
	var code;
	$('#mail-Check-Btn').click(function(){
		var email = $('#memberWriteEmail1').val() + '@'+$('#memberWriteEmail2').val();
		console.log(email);
		$('#checkemail').attr('value',email);
		$.ajax({
			type : 'get',
			url : '/index/member/emailcheck?email='+email,
			success:function(data){
				alert(data);
				$('#mail-Check-Num').attr('disabled',false);
				code =data;
			},error:function(e){
				console.log(e);
			}
		});
	});
	
	// blur -> focus가 벗어나는 경우 발생
	$('#mail-Check-Num').blur(function () {
		const inputCode = $(this).val();
		if(inputCode === code){
			$('#memberWriteEmailDiv').html('인증번호가 일치합니다.');
			$('#memberWriteEmailDiv').css('color','green');
			$('#mail-Check-Num').attr('disabled',true);
			$('#memberWriteEmail1').attr('readonly',true);
			$('#memberWriteEmail2').attr('readonly',true);
			$('#memberWriteEmail2').attr('onFocus', 'this.initialSelect = this.selectedIndex');
	         $('#memberWriteEmail2').attr('onChange', 'this.selectedIndex = this.initialSelect');
		}else{
			$('#memberWriteEmailDiv').html('인증번호가 불일치 합니다. 다시 확인해주세요!.');
			$('#memberWriteEmailDiv').css('color','red');
		}
	});
	
	$('#memberWritephone1').focusout(function(){
		if(!$('#memberWritephone1').val()){
			$('#memberWritephoneDiv').html('전화번호를 입력하여 주세요.');
			$('#checkPhone').attr('value','fail');
		}else if($('#memberWritephone1').val() && !getCheckphone.test($('#memberWritephone1').val())){
			$('#memberWritephoneDiv').html('3~4자의 숫자만 사용 가능합니다.');
			$('#checkPhone').attr('value','fail');
		}else if(!$('#memberWritephone1').val() || !$('#memberWritephone2').val()){
			$('#checkPhone').attr('value','fail');
		}else{
			$('#memberWritephoneDiv').empty();
			$('#checkPhone').attr('value','ok');
		}
	});
	
	$('#memberWritephone2').focusout(function(){
		if(!$('#memberWritephone2').val()){
			$('#memberWritephoneDiv').html('전화번호를 입력하여 주세요.');
			$('#checkPhone').attr('value','fail');
		}else if($('#memberWritephone2').val() && !getCheckphone.test($('#memberWritephone2').val()) || !getCheckphone.test($('#memberWritephone1').val())){
			$('#memberWritephoneDiv').html('3~4자의 숫자만 사용 가능합니다.');
			$('#checkPhone').attr('value','fail');
		}else{
			$('#memberWritephoneDiv').empty();
			$('#checkPhone').attr('value','ok');
		}
	});
	
	$('#phoneselect').focusout(function(){
		if($('#phoneselect').val() == '-----------선택-----------'){
			$('#memberWritephoneDiv').html('전화번호를 선택하여 주세요.');
		}else{
			$('#memberWritephoneDiv').empty();
		}
	});
	
	
	$('#memberWriteBtn').click(function(){
		if(!$('#memberLoginId').val() && !$('#memberWritePwd').val()
			|| !$('#memberWriterePwd').val() || !$('#memberWriteName').val()
			|| !$('#memberWriteEmail1').val() || !$('#memberWriteEmail2').val()
			|| !$('#memberWritephone1').val() || !$('#memberWritephone2').val()
			|| !$('#phoneselect').val() == '-----------선택-----------'
			|| $('#checkId').val() == 'fail' || $('#checkPwd').val() == 'fail'
			|| $('#checkPhone').val() == 'fail'){
			swal('생성 실패',"내용을 입력해주세요",'warning');
			//location.reload();
		}else{
			$.ajax({
				type : 'post',
				data : $('#memberWriteForm').serialize(),
				url : '/index/member/memberWrite',
				success:function(data){
					swal('생성 완료',""+data+"님 환영 합니다.",'success').then(function(){
						location.href='/index'
					})
				},error:function(e){
					console.log(e);
				}
			});	
		}

	});
	$('#emailselect').on('change',function(){
		var check = $('#emailselect option:selected').val();
		$('#memberWriteEmail2').attr('value',check);
		$('#memberWriteEmailDiv').empty();
	});
	
	//Id 찾기 핸드폰 인증번호 
	var code2;
	$('#findIdBtn').click(function(){
		swal('인증번호전송 완료\n 인증번호를 확인해주세요',"",'success')
		$.ajax({
	        type:"get",
	        url:"/index/member/phoneCheck",
	        data : {'findtel' : $('#findtel').val()},
	        success:function(data){
	        		console.log(data);
	        		$("#phonecheck").attr("disabled",false);
	        		$(".successPhoneChk").text("인증번호를 입력한 뒤 본인인증을 눌러주십시오.");
	        		$(".successPhoneChk").css("color","green");
	        		code2 = data;
	        	}
	    });
	});
	
	// blur -> focus가 벗어나는 경우 발생
	$('#phonecheck').blur(function () {
		const inputCode = $(this).val();
		if(inputCode === code2){
			$('#findIdCheckDiv').attr('value','ok');
			$('#phonecheckDiv').html('인증번호가 일치합니다.');
			$('#phonecheckDiv').css('color','green');
			$('#phonecheck').attr('disabled',true);
		}else{
			$('#phonecheckDiv').html('인증번호가 불일치 합니다. 다시 확인해주세요!.');
			$('#phonecheckDiv').css('color','red');
			$('#findIdCheckDiv').attr('value','fail');
		}
	});
	
	$('#btn_next').click(function(){
		if($('#findIdCheckDiv').val() == 'ok'){
		$.ajax({
			  	type:"post",
		        url:"/index/member/memberfindIdcheck",
		        data : $('#findidForm').serialize(),
		        success:function(data){
		        	alert(data)
		        	location.href='/index/member/memberFindId?memberid='+data;
		        },error:function(e){
		        	console.log(e);
		        }
		});
		}else{
			swal('휴대폰 인증을 먼저해주세요.',"",'warning')
		}
	});
}); 