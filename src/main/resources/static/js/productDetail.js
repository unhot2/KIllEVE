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
			/*var html = '<table class="total-table">\
							<tr>\
								<td class="totalTd1"><b>'+productName+'</b><br>-'+color+'/'+size+'</td>\
								<td class="totalTd2">\
									<span class="quantityArea">\
										<input type="number" id="quantity" name="" class="quantity" value="1" readOnly>\
										<a href="#none" class="upBtn quantityBtn">\
											<img src="//img.echosting.cafe24.com/design/skin/default/product/btn_count_up.gif" alt="수량증가">\
										</a>\
										<a href="#none" class="downBtn quantityBtn">\
												<img src="//img.echosting.cafe24.com/design/skin/default/product/btn_count_down.gif" id="option_box1_down" class="option_box_down" alt="수량감소">\
										</a>\
									</span>\
									<a href="#none" class="delete">\
										<img src="//img.echosting.cafe24.com/design/skin/default/product/btn_price_delete.gif" alt="삭제" id="deleteBtn" class="option_box_del">\
									</a>\
								</td>\
								<td class="totalTd3"><b class="salePriceText">￦'+priceToString(salePrice)+'원</b></td>\
							</tr>\
							</table>';*/
			var html = '<tr class="total-product-tr">\
							<td class="totalTd1"><b>'+productName+'</b><br>-'+color+'/'+size+'</td>\
							<td class="totalTd2">\
								<span class="quantityArea">\
									<input type="number" id="quantity" name="" class="quantity" value="1" readOnly>\
									<a href="#none" class="upBtn quantityBtn">\
										<img src="//img.echosting.cafe24.com/design/skin/default/product/btn_count_up.gif" alt="수량증가">\
									</a>\
									<a href="#none" class="downBtn quantityBtn">\
											<img src="//img.echosting.cafe24.com/design/skin/default/product/btn_count_down.gif" id="option_box1_down" class="option_box_down" alt="수량감소">\
									</a>\
								</span>\
								<a href="#none" class="delete">\
									<img src="//img.echosting.cafe24.com/design/skin/default/product/btn_price_delete.gif" alt="삭제" class="deleteBtn">\
								</a>\
							</td>\
							<td class="totalTd3"><b class="salePriceText">￦'+priceToString(salePrice)+'원</b></td>\
						</tr>';
			$('.total-table').append(html)
			/*$('.totalProduct').append(html)*/
			$('#totalPrice').text(priceToString(salePrice));
			$('#quantityText').text($('#quantity').val());	
			$('#select-color').val('*').prop("selected", true);
			$('#select-size').val('*').prop("selected", true);		
		}
	});
	
	// totalProduct 수량증가
	$(document).on('click','.upBtn', function(){
		var index = $('.upBtn').index(this);
		var quantity = parseInt($('.quantity').eq(index).val());
		console.log("증가버튼 index :"+index)
		console.log("증가버튼 : "+quantity)
		$('.quantity').eq(index).val(quantity+1)
		var salePrice = $('#salePrice').text().replace('￦','').replace('원','').replace(',','');
		$('.salePriceText').eq(index).text('￦'+priceToString(salePrice*(quantity+1))+'원');
		/*$('#totalPrice').text(priceToString(salePrice*(quantity+1)))*/
	});
	
	// totalProduct 수량감소
	$(document).on('click','.downBtn', function(){
		var index = $('.downBtn').index(this);
		var quantity = parseInt($('.quantity').eq(index).val());
		console.log("감소버튼 index :"+index)
		console.log("감소버튼 : "+quantity)
		if(quantity > 1){
			$('.quantity').eq(index).val(quantity-1)	
			var salePrice = $('#salePrice').text().replace('￦','').replace('원','').replace(',','');
			$('.salePriceText').eq(index).text('￦'+priceToString(salePrice*(quantity-1))+'원');
			/*$('#totalPrice').text(priceToString(salePrice*(quantity-1)))*/
		}
		else {
			alert('최소 주문수량은 1개 입니다.');
		}
	});
	
	// totalProduct 삭제버튼
	$(document).on('click','.deleteBtn', function(){
		var index = $('.deleteBtn').index(this)
		$('.total-product-tr').eq(index).remove();
	});
	
	
	$(document).on('change','#quantity',function(){
		alert('바뀜')
/*		var d = $('#salePriceText').text()
		$('#totalPrice').text($('#salePriceText').text())
*/	})
});

