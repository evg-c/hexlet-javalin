
@import gg.jte.Content
@import org.example.hexlet.dto.courses.CoursesPage
@import org.example.hexlet.dto.users.UsersPage

@param Content content
@param Content footer
<!-- src/main/jte/layout/page.jte -->
<!-- @param CoursesPage page  -->
<!-- @param UsersPage page    -->
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="view-port" content="width=device-width, initial-scale=1" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
                    rel="stylesheet"
                    integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
                    crossorigin="anonymous">
        <title>Hexlet Javalin Example</title>
        <!--       <h1>${page.getHeader()}</h1>  -->
        <nav>
            <ul>
                <li><a href="/">Home</a></li>
            </ul>
        </nav>
    </head>
    <body>
        <p>
            <a href="${org.example.hexlet.NamedRoutes.coursesPath()}">Courses</a>
            <a href="${org.example.hexlet.NamedRoutes.usersPath()}">Users</a>
        </p>
        <div class="content">
            ${content}
        </div>
        @if(footer != null)
            <div class="footer">
                ${footer}
            </div>
        @endif
    </body>
</html>