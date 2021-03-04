<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8"/>
    <title>登录</title>
    <link rel="stylesheet" href="../../css/bootstrap.css"/>
    <script type="text/javascript" src="../../js/jquery-3.4.1.js"></script>
    <script type="text/javascript" src="../../js/bootstrap.js"></script>
    <script type="text/javascript" src="../../js/jquery.validate.min.js"></script>
    <script type="text/javascript" src="../../js/messages_zh.js"></script>
    <script type="text/javascript" src="../../js/md5.min.js"></script>
    <script type="text/javascript" src="../../js/additional-methods.js"></script>
    <script type="text/javascript" src="../../js/common.js"></script>
    <style type="text/css">
        .login-style {
            position: absolute;
            top: 50%;
            transform: translateY(-50%);
        }

        .login-back {
            background-image: linear-gradient(to top, #d9afd9 0%, #97d9e1 100%);
            border-radius: 1.875rem;
        }

        body {
            background: url(../../img/110404-1521083044b19d.jpg) no-repeat center;
            margin: 0;
            padding: 0;
            font-family: sans-serif;
            background-size: cover;
            background-position: center 0;
            background-repeat: no-repeat;
            background-attachment: fixed;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="login-style col-md-3 col-md-offset-2 login-back" style="padding-top: 0.9375rem;">
        <div class="col-md-offset-5">
            <h1 style="padding-left: 8px;">登录</h1>
        </div>
        <form class="form-horizontal" style="padding: 1.875rem;" name="login" id="login">
            <div class="form-group" style="margin-bottom: 30px; margin-top: 10px;">
                <label for="telephone" class="col-md-3 control-label">电话</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" id="telephone" placeholder="电话" name="telephone">
                </div>
            </div>
            <div class="form-group" style="margin-bottom: 30px;">
                <label for="password" class="col-md-3 control-label">密码</label>
                <div class="col-md-8">
                    <input type="password" class="form-control" id="password" placeholder="密码" name="password">
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-10 col-md-offset-5">
                    <button type="button" class="btn btn-default" id="button">登录</button>
                </div>
            </div>
        </form>
    </div>
</div>
<%--//ajax提交--%>
<script type="application/javascript">
    $(function () {
        $("#button").click(
            function () {
                //判断身份
                var passwordstr = $("#password").val();
                var password = c_password_md5($("#password").val());
                // ajax提交
                if ($("#login").valid()) {
                    $.ajax({
                        url: "/user/login",
                        type: "post",
                        dataType: "json",
                        data: {
                            telephone: $("#telephone").val(),
                            password: password,
                        },
                        success: function (data) {
                            if (data.code === 0) {
                                window.location.href = "/user/main";
                            } else {
                                alert(data.msg)
                            }

                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                            console.log(jqXHR.responseText);
                            console.log(textStatus);
                            console.log(errorThrown);
                        },
                    });
                }

            }
        );
    });
</script>
<%--//jquery validate参数校验--%>
<script type="text/javascript">
    $(function () {
        $("#login").validate({
            debug: true,
            focusCleanup: true,
            onkeyup: false,
            onfocusout: function (element) {
                $(element).valid();
            },
            rules: {
                telephone: {
                    required: true,
                },
                password: {
                    required: true,
                    rangelength: [8, 64]
                },
            },
            messages: {
                telephone: {
                    required: "请输入账号",
                },
                password: {
                    required: "请输入密码",
                    rangelength: "长度不正确"
                },
            }
        })
    })
</script>
</body>
</html>