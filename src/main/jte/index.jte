@import org.example.hexlet.dto.MainPage
@param MainPage page

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <title>Hello Hexlet!</title>
    </head>
    <body>
        <main>
            <h1>Привет, Hexlet!</h1>
            <p>Javalin + jte</p>
            @if(!page.isVisited())
                Это сообщение показывается только один раз. Если хотите увидеть его снова, сотрите куки
            @endif
        </main>
    </body>
</html>




