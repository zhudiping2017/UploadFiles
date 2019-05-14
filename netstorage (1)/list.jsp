<%-- 
  Created by IntelliJ IDEA. 
  2018/12/20 
  To change this template use File | Settings | File Templates. 
--%> 
<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<%@include file="/common/common.jsp"%>
<html> 
<head> 
    <script type="text/javascript"> 
        $(function () { 
            $("#addBtn").click(function () { 
                location.href="${path}/jsp/netstorage/add.jsp";
             })
            $(".delete").click(function () { 
                var fileid = $(this).attr("name");
                location.href="${path}/netstorage/delete.action?fileid="+fileid;
             })
            $("#search").click(function () { 
                var filename = $("#filename").val();
                location.href = "${path}/netstorage/search.action?filename="+filename;
             })
            $("#num").change(function () {
                var $div = $(".left");
                $div.empty();
                for(var i=1;i<=$(this).val();i++){
                    var $input = $('<input type="text" name="filename" id="file'+i+'" value=""><br/>');
                    $input.appendTo($div);
                }

            })
            $(".left").delegate(":text","click",function () {
                window.open("${path}/files/upload.jsp?nameProp="+$(this).attr("id"),"文件上传","height=200,width=300,top=240,left=500");
            })
        })  
    </script> 
</head> 
<body class="main"> 
    <div class="search"> 
        <span> 
            文件名称：<input type="text" id="filename" value="${searchObject.filename}"> 
        </span> 
        <span> 
            <button id="search">查询</button> 
        </span> 
        <span> 
            <button id="addBtn">增加</button> 
        </span> 
    </div> 
    <table> 
        <thead> 
            <td>序号</td> 
            <td>文件名称</td> 
            <td>文件大小</td> 
            <td>上传时间</td> 
            <td>用户</td> 
            <td>编辑</td> 
            <td>删除</td> 
        </thead>
        <c:if test="${list==null||list.size()==0}">
            <tr><td>没有找到匹配的记录</td></tr>
        </c:if>
        <c:forEach items="${list}" var="netstorage" varStatus="status">
            <tr>
                <td>${status.index+1}</td>
                <td>${netstorage.filename}</td>
                <td>${netstorage.filesize}</td>
                <td>${netstorage.uploaddate}</td>
                <td>${netstorage.uid}</td>
                <td><img src="${path}/images/edit.gif" class="update" name="${netstorage.fileid}"></td>
                <td><img src="${path}/images/del.gif" class="delete" name="${netstorage.fileid}"></td>
            </tr>
        </c:forEach>
    </table>
    <form action="${path}/netstorage/insert.action" method="post" enctype="multipart/form-data">
        <h1>上传文件模块</h1>
        上传文件的数量
        <select id="num">
            <option>1</option>
            <option>2</option>
            <option>3</option>
        </select>
        <div class="update">
            <div class="left">

                <input type="file" name="filename2"><br/>

            </div>

            <div id="error"></div>
            <div class="buttons">
                <input type="submit" value="提交">
            </div>
        </div>
    </form>
</body> 
</html> 
