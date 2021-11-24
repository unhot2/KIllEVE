/*일정이상 스크롤이 내려가면 클래스를 추가하거나 제거해주는 FUNCTION*/
$(function() {
	$('#publicBtn').click(function() {
		$('#password').attr("disabled", true);
	});
	
	$('#secretBtn').click(function() {
		$('#password').attr("disabled", false);
	});
});





