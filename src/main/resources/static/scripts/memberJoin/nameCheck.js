let namediv;
let nameCheckOk = false;
let nameInput = document.querySelector('#name');

nameInput.addEventListener('blur', nameBlur);
nameInput.addEventListener('focus', nameFocus);

function nameBlur(){
    namediv = document.createElement('div');
    if(!/^[가-힣]{2,4}$/.test(nameInput.value)){
        namediv.innerHTML ="<p>정말 이름을 입력하셨나요?</p>"
    }else{
        namediv.innerHTML ="<p>입력 완료!</p>"
        nameCheckOk = true;
    }

    if(nameCheckOk){
        document.querySelector('#nameCheckSpan').appendChild(namediv).setAttribute('class','nameWordCheck-Ok');
    }else{
        document.querySelector('#nameCheckSpan').appendChild(namediv).setAttribute('class','nameWordCheck');
    }
}

function nameFocus(){
    nameCheckOk = false;
    if(document.querySelector('.nameWordCheck')){
        document.querySelector('#nameCheckSpan').removeChild(namediv);
    }
    if(document.querySelector('.nameWordCheck-Ok')){
        document.querySelector('#nameCheckSpan').removeChild(namediv);
    }
}