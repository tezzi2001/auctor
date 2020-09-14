window.addEventListener('DOMContentLoaded', function (){
    // header lists
    let tr = document.querySelectorAll('.c_tr'),
        headLists = document.querySelectorAll('.head-list'),
        list = document.querySelectorAll('.c_change');

    headLists.forEach((elem, i) => {
        elem.addEventListener('mouseover', function(){
            tr[i].classList.remove('c_tr');
            tr[i].classList.add('c_tr-active');
            list[i].classList.remove('hide');
            list[i].classList.add('show');
        });
        elem.addEventListener('mouseout', function(){
            tr[i].classList.remove('c_tr-active');
            tr[i].classList.add('c_tr');
            list[i].classList.remove('show');
            list[i].classList.add('hide');
        });
    });

    // choose lists

    let chooseLists = document.querySelectorAll('.choose-list'),
        chooseListItems = document.querySelectorAll('.choose-list-item'),
        chooseTitles = document.querySelectorAll('.choose-title'),
        chooseBoxes = document.querySelectorAll('.box'),
        choose = document.querySelectorAll('.choose');

    function toggleList(i){
        chooseLists[i].classList.toggle('hide');
    }
    chooseBoxes.forEach((elem, i) => {
        elem.addEventListener('click', function(event){
            event.stopPropagation();
            toggleList(i);
        });
    });
    document.addEventListener('click', function(event){
        let target = event.target;
        chooseLists.forEach((menu, i) => {
            let its_menu = target == menu || menu.contains(target);
            let its_box = target == chooseBoxes[i];
            let menu_is_active = !menu.classList.contains('hide');

            

            if(!its_menu && !its_box && menu_is_active){
                toggleList(i);
            }
        });
    });
    chooseLists.forEach((menu, i) => {
        menu.addEventListener('click', function(e){
            let target = e.target;
            if(target && target.tagName == "LI"){
                chooseListItems.forEach(item => {
                    if(target == item){
                        // console.log('ok');
                        chooseTitles[i].innerHTML = item.textContent;
                        if(!chooseTitles[i].classList.contains('choose-title_active')){
                            chooseTitles[i].classList.add('choose-title_active');
                        }
                        toggleList(i);
                    }
                });
            }
        });
    });

    // buttons

    let btns = document.querySelectorAll('.create-btns-item');

    btns.forEach(button => {
        button.addEventListener('mouseover', function(){ button.style.background = '#d5d5db'; });
        button.addEventListener('mousedown', function(){ button.style.background = '#8b8b9c'; });
        button.addEventListener('mouseup', function(){ button.style.background = '#d5d5db'; });
        button.addEventListener('mouseout', function(){ button.style.background = 'transparent'; });
    });

    let bold = document.querySelector('.bold');

    


});