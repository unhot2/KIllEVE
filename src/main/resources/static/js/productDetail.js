$(function() {
	/*SALE 버튼 추가*/
	if($('#discountRate').text().replace('%','') > 0) {
		$('.product-head-info').prepend('<div class="saleIcon">SALE</div>');
	}
	
	/*가격 천단위 구분기호 (정규식 사용)*/
	var consumerPrice = priceToString($('#consumerPrice').html());
	var salePrice = priceToString($('#salePrice').html());
	$('#consumerPrice').html(consumerPrice);
	$('#salePrice').html(salePrice);
	
	// 색상변경시 사이즈 초기화 
	$('#select-color').change(function(){
		$('#select-size').val('*').prop("selected", true);
	});
	
	$('#select-size').change(function(){
		if($('#select-color').val() != '*' && $('#select-size').val() != '*'){
			var html = '<div>둘다 들어감</div>';
			$('.totalProduct').append(html)	
			$('#select-color').val('*').prop("selected", true);
			$('#select-size').val('*').prop("selected", true);		
		}
	});
	
});

