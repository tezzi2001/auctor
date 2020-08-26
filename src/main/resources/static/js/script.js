window.addEventListener('DOMContentLoaded', function(){
    let lang = document.querySelector('.lang'),
        langList = document.querySelector('.lang-list'),
        langTitleText = document.querySelector('.lang-title-text'),
        langListItem = document.querySelectorAll('.lang-list-item'),
        langTr = document.querySelector('.lang-tr'),
        btn = document.querySelector('.btn');

    lang.addEventListener('mouseover', function(){
        langList.classList.remove('hide');
        langTr.classList.remove('lang-tr');
        langTr.classList.add('tr-active');
    });
    lang.addEventListener('mouseout', function(){
        langList.classList.add('hide');
        langTr.classList.add('lang-tr');
        langTr.classList.remove('tr-active');
    });

    function setLang(i){
        langTitleText.innerHTML = langListItem[i].textContent;
        langListItem[i].classList.add('hide');
    }
    // function showAllLang(){
    //     langListItem.forEach(item => {
    //         if(item.classList.contains('hide')){
    //             item.classList.remove('hide');
    //         }
    //     });
    // }
    // showAllLang(0);
    setLang(0);

    langList.addEventListener('click', function(event){
        let target = event.target;
        if(target.classList.contains('lang-list-item')){
            langListItem.forEach(item => {
                if(target == item){
                    // setLang();
                    langListItem.forEach(elem => {
                        if(elem.classList.contains('hide')){
                            elem.classList.remove('hide');
                        }
                    });
                    langTitleText.innerHTML = item.textContent;
                    item.classList.add('hide');
                    langList.classList.add('hide');
                }
            });
        }
    });

    

    btn.addEventListener('mousedown', function(e){
        btn.classList.add('btn-clck');
    });
    btn.addEventListener('mouseup', function(e){
        btn.classList.remove('btn-clck');
    });

});