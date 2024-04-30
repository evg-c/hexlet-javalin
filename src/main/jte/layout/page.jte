<!-- src/main/jte/layout/page.jte -->
@import gg.jte.Content

@param Content content

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="view-port" content="width=device-width, initial-scale=1" />
        <title>Hexlet Javalin Example</title>
    </head>
    <body>
        <p>
            <a href="/courses">Courses</a>
        </p>
        <div class="content">
            ${content}
        </div>
    </body>
</html>