/*비밀글, 공개글 설정에 따른 비밀번호 input 활성화 여부*/
$(function() {
	$('#publicBtn').click(function() {
		$('#password').attr("disabled", true);
	});
	
	$('#secretBtn').click(function() {
		$('#password').attr("disabled", false);
	});
});

/*Qna 비밀번호 확인*/
$(function() {
	$('#chkPassword').click(function(){
		var pw = $('#password').val();
		$.ajax({
				type: "GET",
				url: "/community/chkPassword",
				data : { password : $('#password').val(), boardNum : $('#boardNum').val(), role : $('#role').val()},
				success: function(res) {
					if(res == 1){
						location.href="/community/qna/"+$('#boardNum').val()+"?chkSecret=public";
					} else {
						alert("비밀번호가 틀립니다.")
					}
				},
				error: function(data) {
					console.log("error 발생"+data)
				}
			})
	});
});