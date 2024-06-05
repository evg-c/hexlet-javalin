@import org.example.hexlet.dto.users.BuildUserPage
@param BuildUserPage page

@if(page.getErrors() != null)
    <ul>
        @for(var validator: page.getErrors().values())
            @for(var error: validator)
                <li>${error.getMessage()}</li>
            @endfor
        @endfor
    </ul>
@endif

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="view-port" content="width=device-width, initial-scale=1" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
                    rel="stylesheet"
                    integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
                    crossorigin="anonymous">
        <title>Users build Example</title>
    </head>
    <body>
        <form action="/users" method="post">
            <div>
                <label>
                    Имя
                    <input type="text" name="name" value="${page.getName()}" />
                </label>
            </div>
            <div>
                <label>
                    Email
                    <input type="email" required name="email" value="${page.getEmail()}" />
                </label>
            </div>
            <div>
                <label>
                    Пароль
                    <input type="password" required name="password" />
                </label>
            </div>
            <div>
                <label>
                    Подтверждение пароля
                    <input type="password" required name="passwordConfirmation" />
                </label>
            </div>
            <input type="submit" value="Зарегистрировать пользователя" />
        </form>
    </body>
</html>
