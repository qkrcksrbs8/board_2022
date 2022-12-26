<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<style>
    *{
        padding: 0;
        margin: 0;
        border: none;
    }
    body{
        font-size: 14px;
        font-family: 'Roboto', sans-serif;
    }

    .login-wrapper{
        width: 600px;
        height: 350px;
        padding: 40px;
        box-sizing: border-box;
    }

    .login-wrapper > h2{
        font-size: 24px;
        color: #6A24FE;
        margin-bottom: 20px;
    }
    #login-form > input{
        width: 100%;
        height: 48px;
        padding: 0 10px;
        box-sizing: border-box;
        margin-bottom: 16px;
        border-radius: 6px;
        background-color: #F8F8F8;
    }
    #login-form > input::placeholder{
        color: #D2D2D2;
    }
    #login-form > input[type="submit"]{
        color: #fff;
        font-size: 16px;
        background-color: #6A24FE;
        margin-top: 20px;
    }

    #login-form > input[type="checkbox"]{
        display: none;
    }
    #login-form > label{
        color: #999999;
    }
    #login-form input[type="checkbox"] + label{
        cursor: pointer;
        padding-left: 26px;
        background-repeat: no-repeat;
        background-size: contain;
    }
    #login-form input[type="checkbox"]:checked + label{
        background-repeat: no-repeat;
        background-size: contain;
    }
</style>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>박찬균 튜토리얼</title>
        <link rel="icon" type="image/x-icon" href="/assets/favicon.ico" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css" />
        <link href="/css/styles.css" rel="stylesheet" />
    </head>
    <body>
        <nav class="navbar navbar-light bg-light static-top">
            <div class="container">
                <a class="navbar-brand" href="/">메인</a>
            </div>
        </nav>
        <div class="login-wrapper" style="margin-left: auto; margin-right: auto; margin-top: 120px; border: ridge;">
            <h2>Login</h2>
            <form method="post" action="/auth/signIn" id="login-form">
                <input type="text" name="memberId" placeholder="아이디">
                <input type="password" name="password" placeholder="비밀번호">

                <label >
                    <a onclick="signUp();" style="cursor:pointer;">회원 가입</a>
                </label>
                <input type="submit" value="SignIn">
            </form>
        </div>
    </body>
<script>
    const signUp = () => {
        location.href = '/auth/signUp';
    }
</script>
</html>