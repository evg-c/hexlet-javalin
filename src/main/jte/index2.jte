@import org.example.hexlet.NamedRoutes
@import org.example.hexlet.dto.MainPage2
@param MainPage2 page

<!DOCTYPE html>
<html lang="ru">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <title>Hello Hexlet!</title>
    </head>
    <body>
        <main>
            <h1>Привет, Hexlet!</h1>
            @if(page.getCurrentUser() != null)
                Добро пожаловать, ${page.getCurrentUser()}.
                Чтобы разлогиниться, удалите куку JSESSIONID из браузера
            @else
                <a href="${NamedRoutes.buildSessionPath()}">Войти</a>
            @endif
        </main>
    </body>
</html>
