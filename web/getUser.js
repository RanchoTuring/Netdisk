let ajax=new XMLHttpRequest();
ajax.onreadystatechange=function(){
    if(ajax.readyState==4 && ajax.status==200){
        //做什么
        document.getElementById("username").innerText=ajax.responseText;
        document.cookie="username="+ajax.responseText;

        //拉取文件列表
        let ajaxfl=new XMLHttpRequest();
        ajaxfl.onreadystatechange=function(){
            if(ajaxfl.readyState==4 && ajaxfl.status==200){
                //做什么
                let data=JSON.parse(ajaxfl.responseText);
                let len=data.files.length;
                let table=document.getElementById("fileList");
                for(let i=0;i<len;i++){

                    let selectorTd=document.createElement("td");
                    let selector=document.createElement("input");
                    selector.setAttribute("type","radio");
                    selector.setAttribute("name","filename");
                    selector.setAttribute("value",data.files[i].filename);
                    selectorTd.appendChild(selector);

                    let name=document.createElement("td");
                    name.appendChild(document.createTextNode(data.files[i].filename));

                    let size=document.createElement("td");
                    size.appendChild(document.createTextNode(data.files[i].filesize));

                    let time=document.createElement("td");
                    time.appendChild(document.createTextNode(data.files[i].time));

                    let row=document.createElement("tr");

                    row.appendChild(selectorTd);
                    row.appendChild(name);
                    row.appendChild(size);
                    row.appendChild(time);
                    table.appendChild(row);
                }
            }
        };

//发给谁
        ajaxfl.open("post","getFileList",true);
        ajaxfl.send();

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