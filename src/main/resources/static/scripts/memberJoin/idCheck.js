let div;
let idCheckOk = false;
let idInput = document.querySelector('#id');

idInput.addEventListener('blur',idCheckBlur);  //focus 잃었을 때 이벤트
idInput.addEventListener('focus', idFocus);

function idFocus(){
    if(document.querySelector('.idCheckWord')){
        document.querySelector('#idInput').removeChild(div);
    }
    if(document.querySelector('.idCheckWord-Ok')){
        document.querySelector('#idInput').removeChild(div);
    }
}

function idCheckBlur(){  //focus 잃었을 때 실행되는 함수
    div = document.createElement('div');
    div.className = 'idCheckWord';
    let cssSelectFlag = false;
    let asyncCheck = true;
    if(!/^[a-zA-Z0-9]{5,20}$/.test(idInput.value)){
        div.innerHTML ="<p>아이디는 숫자와 영문자 조합으로 5~ 20자리를 사용해야 합니다.</p>";
        idCheckOk = false;
    }else{
        let checkNumber = idInput.value.search(/[0-9]/g);
        let checkEnglish = idInput.value.search(/[a-z]/ig);

        if(checkNumber <0 || checkEnglish <0){
            div.innerHTML = "<p>숫자와 영문자를 혼용하여야 합니다.</p>";
            idCheckOk = false;
        }else{
            asyncCheck = false;
            $.ajax({
                url : '/idtest',
                type : 'POST',
                data : {id : idInput.value},
                dataType : 'text',  //controller 에서 int형이나 boolean으로 반환해도 string 형태로 그 값을 받음
                success : function(data){
                    idCheckOk = Boolean(data);
                    if(idCheckOk){ //아이디 중복 아닐 시
                        div.innerHTML = "<p>사용가능한 아이디 입니다.</p>";
                        cssSelectFlag = true;
                    }else{
                        div.innerHTML = "<p>이미 존재하는 아이디입니다.</p>";
                    }
                    if(cssSelectFlag){
                        div.className = 'idCheckWord-Ok';
                        document.querySelector('#idInput').appendChild(div).setAttribute('class','idCheckWord-Ok');
                    }else{
                        document.querySelector('#idInput').appendChild(div).setAttribute('class','idCheckWord');
                    }
                },
                error: function(){
                    alert('status 500(Server Error)');
                }
            }); //ajax 비동기
        } //else end
    } //else end
    if(asyncCheck){ //ajax 통신을 하지 않았을 시
        document.querySelector('#idInput').appendChild(div).setAttribute('class','idCheckWord');
    }
}



