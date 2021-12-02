$(function() {
	
	/*일정이상 스크롤이 내려가면 클래스를 추가하거나 제거해주는 FUNCTION*/
	var lastScroll = 0;
	$(window).scroll(function(event) {
		var scroll = $(this).scrollTop();
		if (scroll > 150) {
			//이벤트를 적용시킬 스크롤 높이
			$(".category-bar").addClass("fiexd");
			$("section").addClass("sessionFiexd");
		}
		else {
			$(".category-bar").removeClass("fiexd");
			$("section").removeClass("sessionFiexd");
		}
		lastScroll = scroll;
	});
	
	/*커뮤니티 드롭다운*/
	$('#drop-Btn').hover(function() {
		$('#drop-content').css('display', 'block');
	}, function() {
		$('#drop-content').hover(function() {
			$('#drop-content').css('display', 'block');
		}, function() {
			$('#drop-content').css('display', 'none');
		});
	});
 });
 


