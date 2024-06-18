@import org.example.hexlet.dto.users.UserPage
@param UserPage page

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Пользователь</title>
    </head>
    <body>
        <main>
            <h1>${page.getUser().getName()}</h1>
            <p>${page.getUser().getEmail()}</p>
        </main>
    </body>
</html>
