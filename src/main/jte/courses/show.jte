@import org.example.hexlet.NamedRoutes
@import org.example.hexlet.dto.courses.CoursePage
@param CoursePage page
<!-- src/main/jte/courses/show.jte -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <main>
        <h1>${page.getCourse().getName()}</h1>
        <p>${page.getCourse().getDescription()}</p>
        <a href="${NamedRoutes.coursePathEdit(page.getCourse().getId())}">Редактировать курс</a>
    </main>
</body>
</html>