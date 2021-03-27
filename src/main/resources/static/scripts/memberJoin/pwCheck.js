let pwdiv;
let passCheckdiv;
let pwRegOk = false;  //비밀번호 레거시(정규식) 확인
let pwEqualOk = false;  //비밀번호와 비밀번호 확인 값 비교
let pwInput = document.querySelector('#pass');
let passCheckInput = document.querySelector('#passCheck');

pwInput.addEventListener('blur', pwBlur);
pwInput.addEventListener('focus', pwFocus);
passCheckInput.addEventListener('blur', passCheckBlur);
passCheckInput.addEventListener('focus', passCheckFocus);

function pwBlur(){
    pwdiv = document.createElement('div');
    if(!/^.*(?=^.{8,20}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=*()]).*$/.test(pwInput.value)){
        pwdiv.innerHTML ="<p>비밀번호는 숫자,영문,특수문자 조합으로 8~20자를 사용해야 합니다.</p>"
    }else{
        pwdiv.innerHTML ="<p>사용가능한 비밀번호 입니다.</p>"
        pwRegOk = true;
    }
    if(pwRegOk){
        document.querySelector('#pwInputSpan').appendChild(pwdiv).setAttribute('class','pwCheckReg-Ok');
    }else{
        document.querySelector('#pwInputSpan').appendChild(pwdiv).setAttribute('class','pwCheckReg');
    }

    /*
    * 에외처리
    * 비밀번호와 비밀번호 확인까지 true인 상태에서 비밀번호를 재변경시에 재변경한 비밀번호와 비밀번호 확인 값이 다를 경우
    * 비밀번호 확인을 false로 변경, 비밀번호 확인 아래 문구 제거, 비밀번호 확인에 입력한 값 초기화
    */
    if(pwEqualOk && (passCheckInput.value !== pwInput.value)){
        pwEqualOk = false;
        passCheckFocus();
        passCheckInput.value='';
    }
}

function pwFocus(){
    pwRegOk = false;
    if(document.querySelector('.pwCheckReg')){
        document.querySelector('#pwInputSpan').removeChild(pwdiv);
    }
    if(document.querySelector('.pwCheckReg-Ok')){
        document.querySelector('#pwInputSpan').removeChild(pwdiv);
    }
}
/***************************밑은 passCheckInput function***************************/
function passCheckBlur(){
    passCheckdiv = document.createElement('div');
    if(pwRegOk){ //비밀번호 유효성 검사 통과시에만 값을 비교
        if(passCheckInput.value === pwInput.value){ // 값이 같을 경우
            passCheckdiv.innerHTML ="<p>확인이 완료되었습니다.</p>";
            pwEqualOk = true;
        }else{
            passCheckdiv.innerHTML ="<p>비밀번호를 제대로 확인해 주세요.</p>";
        }
    }else{
        passCheckdiv.innerHTML ="<p>먼저 비밀번호를 조건에 맞춰 입력해 주세요!</p>";
    }
    if(pwEqualOk){
        document.querySelector('#pwCheckSpan').appendChild(passCheckdiv).setAttribute('class', 'passCheckEqual-Ok');
    }else{
        document.querySelector('#pwCheckSpan').appendChild(passCheckdiv).setAttribute('class', 'passCheckEqual');
    }
}

function passCheckFocus(){
    pwEqualOk = false;
    if(document.querySelector('.passCheckEqual')){
        document.querySelector('#pwCheckSpan').removeChild(passCheckdiv);
    }
    if(document.querySelector('.passCheckEqual-Ok')){
        document.querySelector('#pwCheckSpan').removeChild(passCheckdiv);
    }
}