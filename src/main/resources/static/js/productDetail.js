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
	
});

