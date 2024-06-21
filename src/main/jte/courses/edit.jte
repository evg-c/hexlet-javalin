@import org.example.hexlet.NamedRoutes
@import org.example.hexlet.dto.courses.BuildCoursePage
@param BuildCoursePage page

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
        <title>Course edit</title>
    </head>
    <body>
        <form action="${NamedRoutes.coursesPath()}/${page.getId()}" method="post">
            <input type="hidden" name="_method" value="PATCH" />
            <div>
                <label>
                    Название курса
                    <input type="text" name="name" value="${page.getName()}" />
                </label>
            </div>
            <div>
                <label>
                    Описание курса
                    <input type="text" name="description" value="${page.getDescription()}" />
                </label>
            </div>
            <input type="submit" value="Изменить курс" />
        </form>
    </body>
</html>