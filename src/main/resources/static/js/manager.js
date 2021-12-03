$(function() {
	// 관리자 페이지 CSS 변경
	$('.category-bar').addClass('hidden');
	$('.index-header').addClass('hidden');
	$('footer').addClass('hidden');
	$('.header_top').css({ 'border-bottom': '1px solid #5c5c5c', 'background-color': '#5c5c5c' });
	$('.navlog img').css({ 'width': '59%', 'top': '15px', 'left': '540px' })
	$('.guide_nav').css({ 'position': 'relative', 'right': '-304px' })

	/*관리자 메뉴 탭설정*/
	var $menuEle = $('dt');
	$menuEle.click(function() {
		$('dt').addClass('disableTab');
		$(this).removeClass('disableTab');
		$('dd').addClass('hidden');
		$(this).next().removeClass('hidden');
	})

	/*관리자 메뉴 메인이미지 미리보기*/
	$('#input-image').change(function(event) {
		readImage(event.target)
	});

	/*관리자 메뉴 상세이미지 미리보기 설정*/
	$('#input-imgs').change(function(event) {
		handleImgsFilesSelect(event)
	});

	// 상품등록 할인율 계산 -> 판매가 입력
	$('#discountRate').change(function() {
		if ($('#consumerPrice').val() != null) {
			var consumerPrice = $('#consumerPrice').val();
			var discountRate = $('#discountRate').val();
			// (소비자가 / 할인율) / 100 후 소수점 버림 후 곱하기 100 = 100자리 밑으로 버림처림 [ex : 28199 -> 28100]  
			var salePrice = Math.floor((consumerPrice * (1 - (discountRate / 100)) / 100)) * 100;
			$('#salePrice').attr('value', salePrice);
		}
	})
	
	// 상품등록 컬러추가
	var colorList = [];
	$('#add').click(function() {
		colorList.push($('#colorInput').val());
		var html = '<div class="colorList-area"><span class="colorName">' + $('#colorInput').val() + '</span><span id="cancleBtn" class="cancleBtn">X</span></div>';
		$('#colorList').append(html)
	})
	
	// 상품등록 컬러삭제
	$(document).on('click', '#cancleBtn', function(event) {
		event.target.parentElement.remove()
		var text = event.target.parentNode.innerText.replace('X','');
		colorList.pop('text')
	})

});


/*관리자 메뉴 메인이미지 미리보기 설정*/
function readImage(input) {
	// 인풋 태그에 파일이 있는 경우
	if (input.files && input.files[0]) {
		// 이미지 파일인지 검사 (생략)
		// FileReader 인스턴스 생성
		const reader = new FileReader()
		// 이미지가 로드가 된 경우
		reader.onload = e => {
			const previewImage = document.getElementById('preview-image')
			previewImage.src = e.target.result
		}
		// reader가 이미지 읽도록 하기
		reader.readAsDataURL(input.files[0])
	}
}

/*관리자 메뉴 상세이미지 미리보기 설정*/
function handleImgsFilesSelect(e) {
	// 초기값 비움 (재선택시 비우기위함)
	$('#imgs_wrap').empty();
	var files = e.target.files;
	var filesArr = Array.prototype.slice.call(files);

	filesArr.forEach(function(f) {
		// 확장자 확인
		if (!f.type.match('image.*')) {
			alert('확장자는 이미지 확장자만 가능합니다');
			return;
		}
		var reader = new FileReader();
		reader.onload = function(e) {
			var img_html = '<img src=\'' + e.target.result + '\' />';
			$('#imgs_wrap').append(img_html);
		}
		reader.readAsDataURL(f);
	})
}





