let ajax=new XMLHttpRequest();
ajax.onreadystatechange=function(){
    if(ajax.readyState==4 && ajax.status==200){
        //做什么
        document.getElementById("username").innerText=ajax.responseText;
        document.cookie="username="+ajax.responseText;
    }
};

//发给谁
ajax.open("get","getUsername",true);
ajax.send();



function logout() {

    //找到id=username 的标签 ，获取他的内部文本
    let username= document.getElementById("username").innerText;
    let ajax2=new XMLHttpRequest();

    ajax2.onreadystatechange=function(){
        if(ajax2.readyState==4 && ajax2.status==200){
        }
    };
    //发给谁
    ajax2.open("get","delUsername?username="+username,true);
    ajax2.send();
    window.location.href="user/login.html"
}