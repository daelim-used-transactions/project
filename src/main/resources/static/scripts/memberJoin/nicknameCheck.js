let nicknamediv;
let nicknameCheckOk = false;
let nicknameInput = document.querySelector('#nickName');

nicknameInput.addEventListener('blur', nicknameBlur);
nicknameInput.addEventListener('focus', nicknameFocus);

function nicknameBlur(){
    nicknamediv = document.createElement('div');
    let asyncCheck = true;
    let reg = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/gi;
    if(!/^([a-zA-Z가-힣]).{1,10}$/.test(nicknameInput.value)){
        nicknameCheckOk = false;
        nicknamediv.innerHTML ="<p>닉네임은 한글, 영문, 숫자만 가능하며 2-10자리까지 가능합니다.</p>";
    }else if(reg.test(nicknameInput.value)){
        nicknameCheckOk = false;
        nicknamediv.innerHTML ="<p>닉네임에 특수문자를 포함할 수 없습니다.</p>";
    }else{ //입력값 검사 완료
        asyncCheck = false;
        let jsontest = {
            userNickname : nicknameInput.value,
        };
        $.ajax({
            url : '/nicknameCheck',
            type : 'POST',
            data : JSON.stringify(jsontest),
            dataType : 'json',
            contentType : "application/json; charset=UTF-8",
            success : function(data){
                 nicknameCheckOk = Boolean(data.nickNameCheck);
                  if(nicknameCheckOk){ //닉네임 중복 아닐 시
                      nicknamediv.innerHTML = "<p>사용가능한 닉네임입니다.</p>";
                     document.querySelector('#nicknameCheckSpan').appendChild(nicknamediv).setAttribute('class','nicknameCheckWord-Ok');
                 }else{
                      nicknamediv.innerHTML = "<p>이미 존재하는 닉네임입니다.</p>";
                     document.querySelector('#nicknameCheckSpan').appendChild(nicknamediv).setAttribute('class','nicknameCheckWord');
                 }
            },
            error: function(){
                alert('status 500(Server Error)');
            }
        });
    }
    if(asyncCheck){ //ajax 통신을 하지 않았을 시
         document.querySelector('#nicknameCheckSpan').appendChild(nicknamediv).setAttribute('class','nicknameCheckWord');
    }
}

function nicknameFocus(){
    nicknameCheckOk = false;
        if(document.querySelector('.nicknameCheckWord')){
            document.querySelector('#nicknameCheckSpan').removeChild(nicknamediv);
        }
        if(document.querySelector('.nicknameCheckWord-Ok')){
            document.querySelector('#nicknameCheckSpan').removeChild(nicknamediv);
        }
}