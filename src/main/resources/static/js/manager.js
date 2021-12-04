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
	$('#colorAdd').click(function() {
		if($('#colorInput').val() != ''){
			var color = $('#colorInput').val()
			colorList.push(color);
			var html = '<div class="listArea"><span class="nameArea">' + color + '</span><span id="colorRemoveBtn" class="cancelBtn">X</span></div>';
			$('#addColorList').append(html)
		}
		else {
			alert("공백을 입력할 수 없습니다.")
		}
	})
	
	// 상품등록 컬러삭제
	$(document).on('click', '#colorRemoveBtn', function(event) {
		event.target.parentElement.remove()
		var text = event.target.parentNode.innerText.replace('X','');
		colorList.pop('text')
	})
	
	// 상품등록 사이즈추가
	var sizeList = [];
	$('#sizeAdd').click(function() {
		if($('#sizeInput').val() != ''){
			var size = $('#sizeInput').val()
			sizeList.push(size);
			var html = '<div class="listArea"><span class="nameArea">' + size + '</span><span id="sizeRemoveBtn" class="cancelBtn">X</span></div>';
			$('#addSizeList').append(html)
		}
		else {
			alert("공백을 입력할 수 없습니다.")
		}
	})
	
	// 상품등록 사이즈삭제
	$(document).on('click', '#sizeRemoveBtn', function(event) {
		event.target.parentElement.remove()
		var text = event.target.parentNode.innerText.replace('X','');
		sizeList.pop('text')
	})
	
	// 상품등록 AJAX
	$('#productSubmit').click(function(){
		/*validation*/
		// 상품명
		if(!$('#productName').val()){
			alert("상품명을 입력하셔야 합니다.")
			$('#productName').focus();
			return;
		}
		// 소비자가
		if(!Number($('#consumerPrice').val())){
			alert("소비자가를 입력하셔야 합니다.")
			$('#consumerPrice').focus();
			return;
		}
		// 색상정보
		if(colorList.length == 0){
			alert("색상정보를 등록하셔야 합니다.")
			$('#colorInput').focus();
			return;
		}
		// 사이즈정보
		if(sizeList.length == 0){
			alert("사이즈 정보를 등록하셔야 합니다.")
			$('#sizeInput').focus();
			return;
		}
		// 메인이미지
		if(!$('#input-image')[0].files.length){
			alert("메인이미지를 등록하셔야 합니다.")
			$('#input-image').focus();
			return;
		}
		// 상세이미지
		if(!$('#input-imgs')[0].files.length){
			alert("상세이미지를 등록하셔야 합니다.")
			$('#input-imgs').focus();
			return;
		}
		var productName = $('#productName').val()
		var category = $('input[name="category"]:checked').val();
		var consumerPrice = Number($('#consumerPrice').val())
		var discountRate = Number($('#discountRate').val())
		var salePrice = Number($('#salePrice').val())
		var stock = Number($('#stock').val())
		var mainImage = document.getElementById('input-image').files[0].name;
		var files = [];
		var fileList = document.getElementById('input-imgs').files;
		for(var i = 0; i < fileList.length; i++){
			files.push(fileList[i].name);
		}
		
		$.ajax({             
    	type: "POST",          
        url: "/manager/productSave",        
        data: {	
			'category'		: category,
			'productName'	: productName,
			'discountRate'	: discountRate,
			'consumerPrice'	: consumerPrice,
			'salePrice'		: salePrice,
			'stock'			: stock,
			'mainImage'		: mainImage,
			'files'			: files,
			'colorList'		: colorList,
			'sizeList'		: sizeList
		},          
        success: function (data) { 
        	console.log("상품등록 성공")       
        	location.href="/";   
        },          
        error: function (e) {  
            console.log("상품등록 실패")      
         }     
		});  
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





