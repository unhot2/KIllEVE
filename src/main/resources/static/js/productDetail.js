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
	
	$('#test').click(function(){
		alert($('#salePriceText').text())
	});
	
	// 색상,사이즈 선택 시 최종상품,가격 추가
	$('#select-size').change(function(){
		var productName = $('#productName').text();
		var color = $('#select-color').val();
		var size = $('#select-size').val();
		var salePrice = $('#salePrice').text().replace('￦','').replace('원','').replace(',','');
		if($('#select-color').val() != '*' && $('#select-size').val() != '*'){
			var html = '<table class="total-table">\
							<tr>\
								<td class="totalTd1"><b>'+productName+'</b><br>-'+color+'/'+size+'</td>\
								<td class="totalTd2">\
									<span class="quantityArea">\
										<input type="number" id="quantity" name="" class="quantity" value="1">\
										<a href="#none" class="upBtn quantityBtn">\
											<img src="//img.echosting.cafe24.com/design/skin/default/product/btn_count_up.gif" alt="수량증가">\
										</a>\
										<a href="#none" class="downBtn quantityBtn">\
												<img src="//img.echosting.cafe24.com/design/skin/default/product/btn_count_down.gif" id="option_box1_down" class="option_box_down" alt="수량감소">\
										</a>\
									</span>\
									<a href="#none" class="delete">\
										<img src="//img.echosting.cafe24.com/design/skin/default/product/btn_price_delete.gif" alt="삭제" id="option_box1_del" class="option_box_del">\
									</a>\
								</td>\
								<td class="totalTd3"><b id="salePriceText">￦'+priceToString(salePrice)+'원</b></td>\
							</tr>\
							</table>';
			$('.totalProduct').append(html)
			$('#totalPrice').text(priceToString(salePrice));
			$('#quantityText').text($('#quantity').val());	
			$('#select-color').val('*').prop("selected", true);
			$('#select-size').val('*').prop("selected", true);		
		}
	});
	
	$(document).on('click','.upBtn', function(){
		var quantity = parseInt($('#quantity').val());
		$('#quantity').val(quantity+1)
		var salePrice = $('#salePrice').text().replace('￦','').replace('원','').replace(',','');
		$('#salePriceText').text('￦'+priceToString(salePrice*(quantity+1))+'원');
	});
	
	$(document).on('click','.downBtn', function(){
		var quantity = parseInt($('#quantity').val());
		if(quantity > 1){
			$('#quantity').val(quantity-1)	
			var salePrice = $('#salePrice').text().replace('￦','').replace('원','').replace(',','');
			$('#salePriceText').text('￦'+priceToString(salePrice*(quantity-1))+'원');
		}
		else {
			alert('최소 주문수량은 1개 입니다.');
		}
	});
	
	$('#salePriceText').on('change',function(){
		alert('바귐')
/*		var d = $('#salePriceText').text()
		$('#totalPrice').text($('#salePriceText').text())
*/	})
});

