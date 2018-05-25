<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>房屋租赁系统</title>
    <link rel="stylesheet" type="text/css" href="/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="/css/main.css"/>
    <script type="text/javascript" src="/js/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="/js/libs/modernizr.min.js"></script>
    <script type="text/javascript">
        var error = "${param.error}";
        if (error == "zusuccess") {
            alert("该房已租赁，可在租赁列表中查看详情！");
        } else if (error == "checkoutsuccess") {
            alert("该合同已被终止！");
        }
    </script>
</head>
<body>
<div>
    <div class="result-title">
        <h1> 在租列表 </h1>
    </div>
    <form id="houseForm" name="houseForm" action="<%=basePath %>/zulist/findzulist.action" method="post">
        <div class="result-title">
            <div class="result-list">
            </div>
        </div>

        <div class="result-content">
            <table id=grid class="result-tab" width="100%">
                <tbody>
                <tr style="FONT-WEIGHT: bold; FONT-STYLE: normal; BACKGROUND-COLOR: #eeeeee; TEXT-DECORATION: none">
                    <td> 房屋id</td>
                    <td> 地址</td>
                    <td> 价格</td>
                    <td> 租赁人</td>
                    <td> 租赁人身份证号</td>
                    <td> 租赁人联系电话</td>
                    <td> 操作</td>

                </tr>
                <c:forEach items="${zuList}" var="zuList">

                    <tr>style = "FONT-WEIGHT: normal; FONT-STYLE: normal; BACKGROUND-COLOR: white; TEXT-DECORATION:
                        none" >
                        <td> ${zuList.houseId } </td>

                        <td> ${zuList.address} </td>

                        <td> ${zuList.price} </td>
                        <td> ${zuList.userList.name} </td>
                        <td> ${zuList.userList.idCard} </td>
                        <td> ${zuList.userList.phone} </td>
                        <td><a class="link-update" href="/hetong/seehetong.action?houseId=${zuList.houseId }"> 查看合同 </a>&
                            nbsp; & nbsp; a
                            <a class="link-del" href="/hetong/deletehetong.action?houseId=${zuList.houseId }"
                               onclick="return window.confirm('确定要终止合同吗？')"> 终止合同 </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>


        <tr>
        <tr><span id=pagelink>
        <dev style="LINE-HEIGHT: 20px; HEIGHT: 20px; TEXT-ALIGN: right; margin-top:10px">
        共[ < B > ${p.total} < /B>]条记录，共[<B>${p.pages}</B>]页,
        <c:if test="${ p.pageNum > 1 }">
    [ <a href="javascript:to_page(${p.prePage})"> 前一页 </a>]
        </c:if>
        <input type="hidden" name="page" id="page" value="">第 <b> ${p.pageNum} < /b>页
            <c:if test="${ p.pageNum < p.pages }">[ <a href="javascript:to_page(${p.nextPage})"> 后一页 </a>]
        </c:if>

</div>
</span>

</tr>
</tbody>
</tbody>


</form>
</div>
<script language=javascript>
    // 提交分页的查询的表单
    function to_page(page) {
        if (page) {
            $("#page").val(page);
        }
        document.houseForm.submit();
    }
</script>
</body>
</html>