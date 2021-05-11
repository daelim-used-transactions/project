//검색된 게시글이 하나도 없을 시
if(false){
    const product =  document.querySelector('.product');
    const alignNoSearch = document.createElement('div');
    const notProduct= document.createElement('div');
    const searchAdvice = document.createElement('div');
    const mainDesign = document.querySelector('main');
    mainDesign.setAttribute('class','main-design')
    alignNoSearch.setAttribute('class', 'align-nosearch');
    searchAdvice.setAttribute('class', 'search-advice');
    notProduct.setAttribute('class', 'not-product');
    notProduct.innerHTML = "조회된 결과가 없습니다.";
    searchAdvice.innerHTML =
    "- 단어의 철자가 정확한지 확인해 보세요 <br>"
    +  "- 게시글 제목으로 검색이 이루어 집니다!  <br>"
    + "- 검색어의 띄어쓰기를 다르게 해보세요 <br>"
    + "- 유해/금지어가 아닌지 확인해주세요"
    alignNoSearch.appendChild(notProduct);
    alignNoSearch.appendChild(searchAdvice);
    product.appendChild(alignNoSearch);
}

let hi =document.querySelector('#hello');
hi.addEventListener('click', idFocus);

function idFocus(){
    console.log("아뇨?")
}