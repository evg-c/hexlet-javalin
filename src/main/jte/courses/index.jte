<!-- src/main/jte/courses/index.jte -->
@import org.example.hexlet.dto.courses.CoursesPage
@param CoursesPage coursesPage

@template.layout.page(
    page = coursesPage,
    content = @`
        <h1>${coursesPage.getHeader()}</h1>
        @if(coursesPage.getCourses().isEmpty())
            <p>Пока не добавлено ни одного курса</p>
        @else
            @for(var course : coursesPage.getCourses())
                <div>
                    <h2><a href="/courses/${course.getId()}">${course.getName()}</a></h2>
                    <p>${course.getDescription()}</p>
                </div>
            @endfor
        @endif
    `
)
