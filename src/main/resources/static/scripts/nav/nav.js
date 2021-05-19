searchValue = document.querySelector('.search_input');
searchButton = document.querySelector('.search_button');

searchButton.addEventListener('click', searchClick);
searchValue.addEventListener('keydown', test);

// 검색버튼 클릭 이벤트
function searchClick(){
        location.href="http://localhost:8080/search?searchKeyword=" + searchValue.value.trim();
}

//엔터키 이벤트.
function test(e){
    if(e.keyCode === 13){
            location.href="http://localhost:8080/search?searchKeyword=" + searchValue.value.trim();
    }
}
    const notice = document.querySelector('.alert');
    const bell = document.querySelector('.btnBell');

    bell.addEventListener('click',divClick);
    function divClick(){
        console.log('클릭 되고 있냐 ?')
        if(notice.style.display =='none'){
            notice.style.display = "block";
        }else{
            notice.style.display = "none";
        }
    }