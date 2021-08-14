<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
    <title>상품 등록 페이지</title>
</head>
<body>
    
    <form name="frm" action="productRegist" method="post" encType="multipart/form-data">
        <p>
            상 품 이 름 : <input type="text" class="name" name="product_name">
        </p>
        <p>
            <select id="select1" onchange="itemChange()" class="category1" name="category1">
                <option value="케이크">케이크</option>
                <option value="베이커리">베이커리</option>
                <option value="생지">생지</option>
                <option value="디저트">디저트</option>
                <option value="샌드위치">샌드위치</option>
                <option value="원두">원두</option>
                <option value="기타">기타</option>
            </select>

            <select id="select2" class="category2" name="category2">
               
            </select>
        </p>
        <p>
            상 품 가 격 : <input type="text" class="price" name="product_price">
        </p>
        <p>
            상 품 내 용 : <input type="text" class="value" name="product_value">
        </p>
        <p>
        	<input type="file" name="upload" multiple>
        </p>
        <input type="submit" value="상품등록" onclick="nullCheck()">
    </form>
    

    <script>
        function itemChange() {
            var cake = ["초코케이크","파운드케이크","버터케이크","스펀지케이크","롤케이크","컵케이크","티라미수","카스텔라"
        ,"크레이프","타르트"]
        var bakery = ["크로플","에그타르트","마들렌","머핀","도넛","쿠키","플레인","베이글","크로칸슈","몽블랑","브라우니","식빵","샌드위치","크로아상","와플","스콘"]
        var sangji = ["크로아상","타르트/파이","스콘/볼","식빵"]
        var dessert = ["마카롱","뚱카롱","다쿠아즈","오믈렛","초콜릿","슈크림","아이스크림"]
        var sandwich = ["샌드위치","치아바타","크로크무슈"]
        var beans = ["원두"]
        var etc = ["기타"]

        var selectItem = $("#select1").val();
        console.log(selectItem);
        var selectItem2;

        if ( selectItem == "케이크") {
            selectItem2 = cake;
        } else if(selectItem == "베이커리") {
            selectItem2 = bakery;
        } else if (selectItem == "생지") {
            selectItem2 = sangji;
        } else if (selectItem == "디저트") {
            selectItem2 = dessert;
        } else if (selectItem == "샌드위치") {
            selectItem2 = sandwich;
        } else if (selectItem == "원두") {
            selectItem2 = beans;
        } else if (selectItem == "기타") {
            selectItem2 = etc;
        }
        console.log(selectItem2.length);

        $('#select2').empty();
        for (var i=0; i<selectItem2.length; i++) {
            var option = $("<option>"+selectItem2[i]+"</option>")
            $("#select2").append(option);
        }

        };
   
        function nullCheck() {
            if ($('.name').val() == "") {
                alert("상품 이름을 입력하세요")
            } else if ($('.category1').val() == "" || $('.category1').val() == null) {
                alert("상품 카테고리를 선택하세요")
            } else if ($('.category2').val() == null || $('.category2').val() == "" ) {
                alert("상품 카테고리를 선택하세요")
            } else if ($('.price').val() == "") {
                alert("상품 가격을 입력하세요")
            } else if ($('.value').val() == "") {
                alert("상품 내용을 입력하세요")
            }
        }
   </script>
</body>
</html>