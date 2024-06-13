
@import org.example.hexlet.dto.courses.CoursesPage
@import org.example.hexlet.NamedRoutes
@param CoursesPage page
<!-- src/main/jte/courses/index.jte -->
@template.layout.page(
    content = @`
        @if(page.getCourses().isEmpty())
            <p>Пока не добавлено ни одного курса</p>
        @else
            @for(var course : page.getCourses())
                <div>
                    <tr>
                        <td>
                            ${course.getId()}
                        </td>
                        <td>
                            <a href="${NamedRoutes.coursePath(course.getId())}">${course.getName()}  ${course.getDescription()}</a>
                        </td>
                    </tr>
                </div>
            @endfor
        @endif
        <form action="${NamedRoutes.coursesPath()}" method="get">
            <input type="search" required name="term" value="${page.getTerm()}" />
            <input type="submit" value="Искать" />
        </form>
    `,
    footer = @`
        <p>Thank for visiting, come again soon!</p>
        <a href="https://evg-c.github.io" target="_blank">My GitHub</a>
    `
)
