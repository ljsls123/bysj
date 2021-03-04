<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8"/>
    <title></title>
    <script type="text/javascript" src="../../js/jquery-3.4.1.js"></script>
    <script type="text/javascript" src="../../js/bootstrap.js"></script>
    <script type="text/javascript" src="../../js/jquery.validate.min.js"></script>
    <script type="text/javascript" src="../../js/messages_zh.js"></script>
    <script type="text/javascript" src="../../js/md5.min.js"></script>
    <script type="text/javascript" src="../../js/additional-methods.js"></script>
    <script type="text/javascript" src="../../js/common.js"></script>
    <script type="text/javascript" src="../../js/menudata.js"></script>
    <script type="text/javascript" src="../../js/sidemenu.js"></script>
    <link href="../../css/font-awesome.css" rel="stylesheet"/>
    <link href="../../css/skin.css" rel="stylesheet"/>
    <link href="../../css/sidemenu.css" rel="stylesheet"/>
    <link rel="stylesheet" href="../../css/bootstrap.css"/>
    <style>
        .main {
            background: #fff;
            position: absolute;
            float: left;
            right: 0;
            top: 100px;
            bottom: 0;
            width: 85%;
            transition-duration: 0.3s;
            transition-timing-function: ease;
            -webkit-transition-duration: 0.3s;
            -webkit-transition-timing-function: ease;
        }

        .collapse .main {
            left: 50px;
            transition-duration: 0.3s;
            transition-timing-function: ease;
            -webkit-transition-duration: 0.3s;
            -webkit-transition-timing-function: ease;
        }

        .mymenu .mini-menu-float {
            display: inline-block;
            float: right;
        }
    </style>
</head>
<body>

<div class="header">

    <div class="logo">
        <a href="${pageContext.request.contextPath}/user/main"><span>hello, ${sessionScope.user.name}</span></a>
    </div>

    <div class="header-menu">
        <span style="font-size:19px">XX</span>
    </div>
</div>

<div id="menu" class="main-sidebar"></div>

<div class="main">
    <div id="mainframe" style="width:100%;height:100%;text-align: center">
        <div class="login-style col-md-3 col-md-offset-2 login-back" style="padding-top: 0.9375rem;">
            <div class="col-md-offset-5">
                <h1 style="padding-left: 8px;">项目申报</h1>
            </div>
            <form class="form-horizontal" style="padding: 1.875rem;" name="register" id="register">
                <div class="form-group">
                    <label for="title" class="col-md-3 control-label">标题</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" id="title" placeholder="标题" name="title">
                    </div>
                </div>
                <div class="form-group">
                    <label for="type" class="col-md-3 control-label">分类</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" id="type" placeholder="分类" name="type">
                    </div>
                </div>
                <div class="form-group">
                    <label for="key_words" class="col-md-3 control-label">关键词</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" id="key_words" placeholder="关键词" name="key_words">
                    </div>
                </div>
                <div class="form-group">
                    <label for="detail" class="col-md-3 control-label">详细介绍</label>
                    <div class="col-md-8">
                        <textarea rows="20" cols="30" id="detail" name="detail" placeholder="详细介绍"></textarea>
                    </div>
                </div>
                <div class="form-group">
                    <label for="money" class="col-md-3 control-label">申请经费</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" id="money" placeholder="申请经费" name="money">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-5 col-md-offset-5">
                        <button type="button" class="btn btn-default" id="button">申报</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    $("#menu").sidemenu({
        data: eval('${sessionScope.menuList}'),
    });

    $(function () {
        $("#button").click(function () {
            $.ajax({
                url: "/project/create",
                type: "post",
                datatype: "json",
                data: {
                    title: $("#title").val(),
                    detail: $("#detail").val(),
                    type: $("#type").val(),
                    key_words: $("#key_words").val(),
                    money: $("#money").val(),
                },
                success: function (data) {
                    if (data.code === 0) {
                        alert("success");
                        window.location.href = "/user/main"
                    } else {
                        alert(data.msg)
                    }
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log(jqXHR.responseText);
                    console.log(textStatus);
                    console.log(errorThrown);
                },
            })
        })
    })
</script>
</body>
</html>