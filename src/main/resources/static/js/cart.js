
var totalPriceArray = [];
var totalQuantityArray = [];
var deliveryPrice = 2500;

$(function() {
	// 초기세팅
	var cnt = $('.cartList-tr:last').index();
	for (var i = 0; i < cnt; i++) {
		var salePrice = $('.salePrice').eq(i).text(); // 판매가
		$('.salePrice').eq(i).text('￦' + priceToString(salePrice) + '원') // 판매가 천단위 구분 추가
		var totalPrice = $('.totalPrice').eq(i).text(); // 합계가격
		$('.totalPrice').eq(i).text('￦' + priceToString(totalPrice) + '원') // 합계가격 천단위 구분 추가
		totalQuantityArray.push(parseInt($('.quantity').eq(i).val())); // 총수량 배열 추가
		totalPriceArray.push(Number(salePrice * totalQuantityArray[i])) // 총가격 배열 추가
		$('.deliveryPrice').eq(i).text('￦' + priceToString(deliveryPrice) + '원') // 배송비 초기값 세팅
	}
	totalPriceSet(totalPriceArray); // 최종가격 세팅


	// totalProduct 수량증가
	$(document).on('click', '.upBtn', function() {
		var index = $('.upBtn').index(this); // 클릭위치 index
		var cartNo = $('.cartNo').eq(index).val(); // 장바구니 번호
		console.log("cartNo : " + cartNo);
		var salePrice = trimPrice($('.salePrice').eq(index).text()); // 상품가격
		var quantity = parseInt($('.quantity').eq(index).val()); // 상품수량
		totalPriceArray[index] = salePrice * (quantity + 1); // 총수량 배열 수정
		totalQuantityArray[index] = Number($('.quantity').eq(index).val()) + 1; // 총가격 배열 수정
		$('.quantity').eq(index).val(totalQuantityArray[index]); // 수량증가 세팅
		$('.totalPrice').eq(index).text('￦' + priceToString(totalPriceArray[index]) + '원'); // 가격증가 세팅
		totalPriceSet(totalPriceArray); // 최종가격 세팅
		$.ajax({ // 장바구니 DB에 상품수정
			type: "POST",
			url: "/cart/cartUpdate",
			data: {
				cartNo: cartNo,
				quantity: totalQuantityArray[index]
			},
			success: function(res) {
				console.log("수량수정 성공");
			},
			error: function(request, status, error) {
				console.log("수량수정 실패");
			}
		})
	});

	// totalProduct 수량감소 
	$(document).on('click', '.downBtn', function() {
		var index = $('.downBtn').index(this); // 클릭위치 index
		var cartNo = $('.cartNo').eq(index).val(); // 장바구니 번호
		var salePrice = trimPrice($('.salePrice').eq(index).text()); // 상품가격
		var quantity = parseInt($('.quantity').eq(index).val()); // 상품수량
		if (quantity > 1) { // 수량 1이상일 경우만 수량감소
			totalPriceArray[index] = salePrice * (quantity - 1); // 총가격 배열 수정
			totalQuantityArray[index] = Number($('.quantity').eq(index).val()) - 1; // 총수량 배열 수정
			$('.quantity').eq(index).val(totalQuantityArray[index]) // 수량감소 세팅
			$('.totalPrice').eq(index).text('￦' + priceToString(totalPriceArray[index]) + '원'); // 가격감소 세팅
			totalPriceSet(totalPriceArray) // 최종가격 세팅
			$.ajax({ // 장바구니 DB에 상품수정
				type: "POST",
				url: "/cart/cartUpdate",
				data: {
					cartNo: cartNo,
					quantity: totalQuantityArray[index]
				},
				success: function(res) {
					console.log("수량수정 성공");
				},
				error: function(request, status, error) {
					console.log("수량수정 실패");
				}
			})
		}
		else {
			alert('최소 주문수량은 1개 입니다.');
		}
	});

	// 삭제버튼 
	$(document).on('click', '.deleteBtn', function() {
		var index = $('.deleteBtn').index(this);
		var cartNo = $('.cartNo').eq(index).val(); // 카트번호
		var chk = confirm('삭제하시겠습니까?');
		if (chk) {
			var index = $('.deleteBtn').index(this); // 클릭위치 index
			$('.deleteBtn').parent().parent().eq(index).remove(); //상품정보 delete
			totalPriceArray.splice(index, 1); // 총가격 배열 삭제
			totalQuantityArray.splice(index, 1); // 총수량 배열 삭제
			totalPriceSet(totalPriceArray); // 최종가격 세팅
			if ($('.cartList-tr:last').index() == -1) { // 장바구니 저장된 상품 없을 시 최종가격부분 삭제
				$('.total-table').remove();
				var html = '<tr><td colspan="100" style="height:148px; font-size:15px;"><div>장바구니가 비어있습니다.</div></td></tr>';
				$('.cart-table').append(html);
			}
			$.ajax({ // 장바구니 DB에 상품삭제
				type: "POST",
				url: "/cart/cartDelete",
				data: {
					cartNo: cartNo
				},
				success: function(res) {
					console.log("장바구니 삭제");
				},
				error: function(request, status, error) {
					console.log("장바구니 삭제실패");
				}
			})
		}
		else {
			return;
		}
	});

	// 전체 체크박스
	$(document).on('click', '#checkAll', function() {
		// checkAll 체크시 전체 체크
		if ($('#checkAll').is(":checked")) {
			$('.checkBox').prop('checked', true);
		}
		else {
			$('.checkBox').prop('checked', false);
		}
	});
	
	// 전체상품주문
	$('#orderAll').click(function() {
		$('.checkBox').prop('checked', true);
		$('#cartForm').submit();		
	})
	
	// 선택상품주문
	$('#orderSelect').click(function() {
		var index = $('.cartList-tr:last').index();
		console.log("index값 : "+index)
		var cnt = 0;
		for(var i=0; i< index; i++){
			console.log('i값 :'+i)
			console.log("체크여부 : "+$('.checkBox').eq(i).is(":checked"));
			if($('.checkBox').eq(i).is(":checked")){
				cnt ++;
			}
		}
		if(cnt > 0){
			$('#cartForm').submit();
		}
		else {
			alert("선택된 상품이 없습니다.");
		}
	});
	
})

// 최종가격 입력 함수
function totalPriceSet(totalPriceArray) {
	// 인덱스 (현재 저장되있는 상품수)
	var index = $('.cartList-tr:last').index();
	// 최종가격
	var sum = 0;
	for (var i = 0; i < index; i++) {
		sum += totalPriceArray[i];
	}
	// 10만원이상시 배송료 무료로 세팅
	if (sum > 100000) {
		deliveryPrice = 0;
		for (var j = 0; j < index; j++) {
			$('.deliveryPrice').text('무료');
		}
		$('#deliveryPrice').text('+ ￦0원');
	}
	else {
		deliveryPrice = 2500;
		$('.deliveryPrice').text('￦' + deliveryPrice + '원');
		$('#deliveryPrice').text('+ ￦' + deliveryPrice + '원');
	}
	$('#totalPrice').text('￦' + priceToString(sum) + '원');
	$('#totalPay').text('￦' + priceToString(sum + deliveryPrice) + '원');
}

