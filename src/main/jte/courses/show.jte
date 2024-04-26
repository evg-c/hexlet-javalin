<!-- src/main/jte/courses/show.jte -->
@import org.example.hexlet.dto.courses.CoursePage
@param CoursePage page
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
    </main>
</body>
</html>