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
        height: 500px;
        padding: 40px;
        box-sizing: border-box;
    }

    .login-wrapper > h2{
        font-size: 24px;
        color: #6A24FE;
        margin-bottom: 20px;
    }
    #signForm > input{
        width: 100%;
        height: 48px;
        padding: 0 10px;
        box-sizing: border-box;
        margin-top: 16px;
        border-radius: 6px;
        background-color: #F8F8F8;
    }
    #signForm > input::placeholder{
        color: #D2D2D2;
    }
    #signForm > input[type="button"]{
        color: #fff;
        font-size: 16px;
        background-color: #6A24FE;
        margin-top: 20px;
    }

    #signForm > input[type="checkbox"]{
        display: none;
    }
    #signForm > label{
        color: #999999;
    }
    #signForm input[type="checkbox"] + label{
        cursor: pointer;
        padding-left: 26px;
        background-repeat: no-repeat;
        background-size: contain;
    }
    #signForm input[type="checkbox"]:checked + label{
        background-repeat: no-repeat;
        background-size: contain;
    }
</style>
<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
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
            <h2>SignUp</h2>
            <form method="post" action="" id="signForm">
                <input type="text"          id="memberId"       name="memberId"     onkeyup="lengthCheck(this, 20);" placeholder="아이디">
                <input type="password"      id="password"       name="password"     onkeyup="lengthCheck(this, 20); passwordCheck(this);" placeholder="비밀번호">
                <label id="password_label"      style="color: red; display: none;">비밀번호는 8자 이상 20자 이하 대소문자 포함</label>
                <input type="password"      id="checkPassword"  name="checkPassword"onkeyup="lengthCheck(this, 30);" placeholder="비밀번호 확인">
                <label id="checkPassword_label" style="color: red; display: none;">비밀번호와 일치하지 않습니다.</label>
                <input type="text"          id="name"           name="name"         onkeyup="lengthCheck(this, 25); nameCheck(this);" placeholder="이름">
                <label id="name_label" style="color: red; display: none;">한글만 입력 가능합니다.</label>

                <label >
                    <a onclick="signIn();" style="cursor:pointer;">로그인</a>
                </label>
                <input type="button" onclick="signUp();" value="회원 가입">
            </form>
        </div>
    </body>
<script>

    const lengthCheck = (v, length) => {
        if (v.value == '') return;
        let id = '#'+v.id;
        if (v.value.length > parseInt(length)) {
            $(id).val(v.value.substring(0, parseInt(length)));
            return false;
        }
    }

    const labelDisplay = (v, display) => {
        let label_id = '#' + v + '_label';
        $(label_id).css('display', display);
    }

    const passwordCheck = (v) => {
        if (v.value == '') {
            labelDisplay(v.id, false);
            return false;
        }
        let reg = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,20}$/;
        if(!reg.test(v.value)) {
            labelDisplay(v.id, 'block');
            return false;
        }
        labelDisplay(v.id, 'none');
    }

    const nameCheck = (v) => {
        if (v.value == '') {
            labelDisplay(v.id, 'none');
            return false;
        }
        let reg = /^[ㄱ-ㅎ가-힣]+$/; // 한글만
        if(!reg.test(v.value)) {
            labelDisplay(v.id, 'block');
            return false;
        }
        labelDisplay(v.id, 'none');
    }

    const signUp = () => {

        let memberId = $('#memberId').val();
        let password = $('#password').val();
        let name = $('name').val();

        let param = {
            memberId, password, name
        }
        $.post("/auth/signUp", param,
            function(data) {
                alert(data.message);
                location.href = '/auth/signIn';
            }
        );

        return false;

    }
    const signIn = () => {
        location.href = '/auth/signIn';
    }
</script>
</html>