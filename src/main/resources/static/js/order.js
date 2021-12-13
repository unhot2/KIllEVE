
var totalPriceArray = [];
var totalQuantityArray = [];
var deliveryPrice = 2500;

$(function() {
	// 초기세팅
	var cnt = $('.orderList-tr:last').index();
	for (var i = 0; i < cnt; i++) {
		var salePrice = $('.salePrice').eq(i).text(); // 판매가
		$('.salePrice').eq(i).text('￦' + priceToString(salePrice) + '원') // 판매가 천단위 구분 추가
		var totalPrice = $('.totalPrice').eq(i).text(); // 합계가격
		$('.totalPrice').eq(i).text('￦' + priceToString(totalPrice) + '원') // 합계가격 천단위 구분 추가
		totalQuantityArray.push(parseInt($('.quantity').eq(i).text())); // 총수량 배열 추가
		totalPriceArray.push(Number(salePrice * totalQuantityArray[i])) // 총가격 배열 추가
		$('.deliveryPrice').eq(i).text('￦' + priceToString(deliveryPrice) + '원') // 배송비 초기값 세팅
	};
	totalPriceSet(totalPriceArray); // 최종가격 세팅

	// 이메일 제공자 설정
	$('#emailProviderSelect').change(function(){
		var provider = $('#emailProviderSelect').val();
		console.log("provider값 : "+provider);
		if(provider != '*'){
			$('#emailProvider').val(provider);
			$('#emailProvider').attr('readOnly',true);
		}
		else {
			$('#emailProvider').val('');
			$('#emailProvider').attr('readOnly',false);
		}
	});
	
	// 주문자 정보와 동일 클릭
	$('#deliveryChoice1').click(function(){
		var name = $('#name').val();
		$('#deliveryName').val(name);
		var phone = $('#phone').val();
		$('#deliveryPhone').val(phone);
		var zipCode = $('#zipCode').val();
		$('#deliveryZipCode').val(zipCode);
		var address = $('#address').val();
		$('#deliveryAddress').val(address);
		var detailAddress = $('#detailAddress').val();
		$('#deliveryDetailAddress').val(detailAddress);
	});
	
	// 새로운 배송지 클릭 
	$('#deliveryChoice2').click(function(){
		$('#deliveryName').val('');
		$('#deliveryPhone').val('');
		$('#deliveryZipCode').val('');
		$('#deliveryAddress').val('');
		$('#deliveryDetailAddress').val('');
	});
	
})

// 최종가격 입력 함수
function totalPriceSet(totalPriceArray) {
	// 인덱스 (현재 저장되있는 상품수)
	var index = $('.orderList-tr:last').index();
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
		$('#deliveryPrice').text(' ￦' + deliveryPrice + '원');
	}
	$('#totalPrice').text('￦' + priceToString(sum) + '원');
	$('#totalPay').text('￦' + priceToString(sum + deliveryPrice) + '원');
}

function findAddr() {
	new daum.Postcode({
		oncomplete: function(data) {
		var addr = '';
		if (data.userSelectedType === 'R') {
			addr = data.roadAddress
		}
		else {
			addr = data.jibunAddress;
		}
		// 우편번호와 주소 정보를 해당 필드에 넣는다.
		document.getElementById('zipCode').value = data.zonecode;
		document.getElementById('address' ).value = addr;
		}
	}).open();
};

function deliveryfindAddr() {
	new daum.Postcode({
		oncomplete: function(data) {
			var addr = '';
			if (data.userSelectedType === 'R') {
				addr = data.roadAddress
			}
			else {
				addr = data.jibunAddress;
			}
			// 우편번호와 주소 정보를 해당 필드에 넣는다.
			document.getElementById('deliveryZipCode').value = data.zonecode;
			document.getElementById("deliveryAddress").value = addr;
		}
	}).open();
};

function kakaoPay(){
	if(!$('input[name="deliveryUserName"]').val()){
		alert('주문자명을 입력해주세요');
		$('input[name="deliveryUserName"]').focus();
		return;
	}
	if(!$('input[name="deliveryPhone"]').val()){
		alert('휴대폰 번호를 입력해주세요');
		$('input[name="deliveryPhone"]').focus();
		return;
	}
	if(!$('input[name="deliveryZipCode"]').val()){
		alert('우편번호를 입력해주세요');
		return;
	}
	if(!$('input[name="deliveryAddress"]').val()){
		alert('기본주소를 입력해주세요');
		return;
	}
	if(!$('input[name="deliveryDetailAddress"]').val()){
		alert('상세주소를 입력해주세요');
		$('input[name="deliveryDetailAddress"]').focus();
		return;
	}
	var count = $('.orderList-tr:last').index();
	var name;
	if(count > 1) {
		name = $('.productName').eq(0).text()+' 외 '+(count-1)+'건';
	}
	else {
		name = $('.productName').eq(0).text();	
	}
	var userId	= $('input[name="userId"]').val();
	var amount = trimPrice($('#totalPay').text());
	if(trimPrice($('#deliveryPrice').text()) == '2500'){
		var delivery_price = 1;
	}
	else {
		var delivery_price = 0;
	}
	var buyer_name = $('input[name="userName"]').val();
	var buyer_tel = $('input[name="phone"]').val();
	var buyer_postcode = $('input[name="zipCode"]').val();
	var buyer_addr = $('input[name="address"]').val() + $('input[name="detailAddress"]').val();
	var buyer_email = $('input[name="email"]').val() +'@'+$('input[name="emailProvider"]').val();
	var delivery_name = $('input[name="deliveryUserName"]').val();
	var delivery_tel = $('input[name="deliveryPhone"]').val();
	var delivery_postcode = $('input[name="deliveryZipCode"]').val();
	var delivery_addr = $('input[name="deliveryAddress"]').val() + $('input[name="deliveryDetailAddress"]').val();
	var delivery_message = $('.deliveryMessage').val();
	var cnt = $('.orderList-tr:last').index();
	var cartList = [];
	for(var i=0; i < cnt; i++){
		cartList.push($('.cartNo').eq(i).val());
	}
	
IMP.init('imp85558424'); 
   IMP.request_pay({
       pg : 'kakaopay',
       pay_method : 'card',
       merchant_uid : 'merchant_' + new Date().getTime(),
       name : name,
       amount : amount,
       buyer_email : buyer_email,
       buyer_name : buyer_name,
       buyer_tel : buyer_tel,
       buyer_addr : buyer_addr,
       buyer_postcode : buyer_postcode
      }, function(rsp) {
			console.log(rsp)
    	  if(rsp.success){
    		 $.ajax({
    	            url : "/order/kakaoPayment",
    	            method : 'POST',
    	            traditional : true,
    	            data : {
						userId : userId,
						name : name,
						amount : amount,
						delivery_price :delivery_price, 
					    delivery_name : delivery_name,
					    delivery_tel : delivery_tel,
					    delivery_postcode : delivery_postcode,
					    delivery_addr : delivery_addr,
					    delivery_message : delivery_message,
					    imp_uid : rsp.imp_uid,
					    merchant_uid : rsp.merchant_uid,
					    paid_at : rsp.paid_at,
					    receipt_url : rsp.receipt_url,
					    test : cartList
					},
    	            success : function(data) {
						console.log(data)
						location.href="/order/orderList"
    				}
  				});
    		}
    	  else{
    		  alert('결제에 실패하였습니다.')
    	  }
	});
};


