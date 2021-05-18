const deleteBtn = document.querySelector('#delete-board');
const checkBoxs = document.querySelectorAll('[name=remove]');

deleteBtn.addEventListener('click',(e)=>{
    let removeIdxArray =[];

    checkBoxs.forEach((checkBox)=>{
         if(checkBox.checked){
            removeIdxArray.push(Number(checkBox.value));
         }
    })
    console.log(removeIdxArray, removeIdxArray.length);
    if(removeIdxArray.length > 0){
        let userCheck = confirm(`${removeIdxArray.length} 개의 게시글을 삭제하시겠습니까?`);
        console.log(userCheck);
        if(userCheck === true){
            let objParams = {
                removeIdx : removeIdxArray,
            }
            $.ajax({
                    url : '/removeboard',
                    type : 'POST',
                    data : {removeIdxArray : removeIdxArray},
                    dataType : 'text',
                    success : function(data){
                        console.log(data);
                        location.reload();
                    },
                    error: function(request,status,error){
                        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
                        }
                }); //ajax
        }
    }

})