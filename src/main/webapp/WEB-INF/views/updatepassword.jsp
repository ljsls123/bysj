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
        <div class="col-md-3 col-md-offset-4"
             style="padding-top: 0.9375rem;position: relative;top: 50%;transform: translateY(-50%);">
            <div class="col-md-offset-1">
                <h1 style="padding-left: 8px;">修改密码</h1>
            </div>
            <form class="form-horizontal" style="padding: 1.875rem;" name="register" id="register">
                <div class="form-group">
                    <label for="old_password" class="col-md-4 control-label">旧密码</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" id="old_password" placeholder="旧密码" name="old_password">
                    </div>
                </div>
                <div class="form-group">
                    <label for="new_password" class="col-md-4 control-label">新密码</label>
                    <div class="col-md-8">
                        <input type="password" class="form-control" id="new_password" placeholder="新密码"
                               name="new_password">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-5 col-md-offset-5">
                        <button type="button" class="btn btn-default" id="button">修改</button>
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
            const old_password = c_password_md5($("#old_password").val());
            const new_password = c_password_md5($("#new_password").val());
            if ($("#register").valid()) {
                $.ajax({
                    url: "/user/updatePassword",
                    type: "post",
                    datatype: "json",
                    data: {
                        oldPassword: old_password,
                        newPassword: new_password
                    },
                    success: function (data) {
                        if (data.code === 0) {
                            window.location.href = "/user/main"
                            alert("修改密码成功")
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
            }
        })
    })
</script>
<script type="text/javascript">
    $(function () {
        $("#register").validate({
            debug: true,
            focusCleanup: true,
            onkeyup: false,
            onfocusout: function (element) {
                $(element).valid();
            },
            rules: {
                old_password: {
                    required: true,
                    rangelength: [8, 64]
                },
                new_password: {
                    required: true,
                    rangelength: [8, 64]
                },
            },
            messages: {
                old_password: {
                    required: "请输入密码",
                    rangelength: "长度不正确"
                },
                new_password: {
                    required: "请输入密码",
                    rangelength: "长度不正确"
                },
            }
        })
    })
</script>
</body>
</html>