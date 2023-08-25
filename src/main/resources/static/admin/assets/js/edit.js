var timer = 0;
var delay = 200;
var prevent = false;
function singleClick(id)
{
    clearTimeout(timer);
    timer = setTimeout(function() {
        if (!prevent) {copy(id);}
        prevent = false;
    },delay);    //“200”单位是毫秒
}

function dbClick(id1,id2)
{
    clearTimeout(timer);
    prevent = true;
    openEditWindow();
}

function copy(id)
{
    var textInComment = document.getElementById(id).title;
    var oInput = document.createElement('input');
    oInput.value = textInComment;
    document.body.appendChild(oInput);
    oInput.select(); // 选择对象
    document.execCommand("Copy"); // 执行浏览器复制命令
    oInput.className = 'oInput';
    oInput.style.display='none';
    alert("Copy success, you can paste it!");
}

function openEditWindow(){
    document.getElementById('light').style.display='block';
    document.getElementById('fade').style.display='block'
}
function closeEditWindow(){
    document.getElementById('light').style.display='none';
    document.getElementById('fade').style.display='none'
}
