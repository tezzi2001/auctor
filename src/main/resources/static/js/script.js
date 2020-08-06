window.addEventListener('DOMContentLoaded', function(){
    
    // language

    let chooseList = document.querySelector('.lang-change'),
        chooseListItem = document.querySelectorAll('.lang-selector'),
        chooseListTitle = document.querySelector('.lang-title'),
        choose = document.querySelector('.lang');

    choose.addEventListener('mouseover', function(){
        chooseList.classList.remove('hide');
    });
    choose.addEventListener('mouseout', function(){
        chooseList.classList.add('hide');
    });

    function showAll(){
        chooseListItem.forEach(item => {
            item.classList.remove('hide');
        });
    }
    function hideItem(i){
        chooseListItem[i].classList.add('hide');
    }


    chooseList.addEventListener('click', function(event){
        let target = event.target;

        if(target && target.classList.contains('lang-selector')){
            chooseListItem.forEach((item, i) => {
                if(target == item){
                    chooseListTitle.innerHTML = chooseListItem[i].textContent;
                    showAll();
                    hideItem(i);
                }
            });
        }

    });

    // buttons

    let button = document.querySelector('.button');

    button.addEventListener('mouseover', function(){ button.style.background = '#ccf2fc'; });
    button.addEventListener('mousedown', function(){ button.style.background = '#73dcf8'; });
    button.addEventListener('mouseup', function(){ button.style.background = '#ccf2fc'; });
    button.addEventListener('mouseout', function(){ button.style.background = 'transparent'; });
    button.addEventListener('touchstart', function(){ button.style.background = '#73dcf8'; });
    button.addEventListener('touchend', function(){ button.style.background = 'transparent'; });

});