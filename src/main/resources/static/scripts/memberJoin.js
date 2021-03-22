document.querySelector('.idCheck').addEventListener('click',idCheck);

function idCheck(){
    if(document.querySelector('.idCheckWord')){
        alert("이미 div 추가함");
    }else{
        let div = document.createElement('div');
        div.className = 'idCheckWord';
        div.innerHTML = "<h3>이미 존재하는 id 입니다.</h3>";
        document.querySelector('#idInput').appendChild(div).setAttribute('class','idCheckWord');
    }
}