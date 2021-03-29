let emailCheckdiv;
let certificationCheckOk = false;
let certificationInput = document.querySelector('#certification');
let emailInput = document.querySelector('#email');
let NumberInput = document.querySelector('#emailCheck');
let AuthenticationNumber ='';
let sendFlag = false;

certificationInput.addEventListener('click', certificationClick);
NumberInput.addEventListener('blur', NumberInputBlur);
NumberInput.addEventListener('focus', NumberInputFocus);

function certificationClick(){
    if(certificationCheckOk){
       let retry = confirm('이미 인증을 하셨는데 재요청을 하실껀가요?');
       if(retry){
        certificationCheckOk = false; //다시 false로 변환
        NumberInputFocus();
        }else{
            return false;
        }
    }
    sendFlag = false;
    $.ajax({
        url : '/emailCheck',
        type : 'POST',
        data : {userEmail : emailInput.value},
        dataType : 'text',
        success : function(data){
            if(data !== ''){
            alert(`인증번호를 송신하였습니다.\n메일이 가지 않았다면 입력한 이메일을 다시 한번 확인해 주세요.`);
            AuthenticationNumber = data;
            sendFlag =  true;
            }else{
            alert('이메일을 찾을 수 없습니다.');
            }
        },
        error : function(){
            alert('status 500(Server Error)');
        }
    }); //ajax
}

function NumberInputBlur(){
    emailCheckdiv = document.createElement('div');
    if(sendFlag){
        if(NumberInput.value === AuthenticationNumber){
            emailCheckdiv.innerHTML ="<p>인증이 완료되었습니다.</p>";
            certificationCheckOk = true;
        }else{
            emailCheckdiv.innerHTML = "<p>다시 입력해 주세요!</p>";
            certificationCheckOk = false;
        }
    }else{
            emailCheckdiv.innerHTML = "<p>먼저 인증번호를 요청하세요!</p>";
            certificationCheckOk = false;
    }

    if(certificationCheckOk){
        document.querySelector('#emailCheckSpan').appendChild(emailCheckdiv).setAttribute('class','emailCheck-Ok');
    }else{
        document.querySelector('#emailCheckSpan').appendChild(emailCheckdiv).setAttribute('class','emailCheck');
    }
}

function NumberInputFocus(){
    certificationCheckOk = false;
    if(document.querySelector('.emailCheck')){
        document.querySelector('#emailCheckSpan').removeChild(emailCheckdiv);
    }
    if(document.querySelector('.emailCheck-Ok')){
        document.querySelector('#emailCheckSpan').removeChild(emailCheckdiv);
    }
}