    <!DOCTYPE html>
    <html>
    <head>
        <title>BUYSELL</title>
    </head>
    <body>
    <h1>BUYSELL</h1><hr>
    <h4>Авторизация</h4>
    <form action="/login" method="post">
        Имя пользователя: <input type="text" name="name"><br><br>
        Пароль: <input type="password" name="password"><br><br>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Вход"/>
    </form>

    <a href="/registration">Зарегистрироваться</a>
    </body>
    </html>