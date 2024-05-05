<!-- src/main/jte/courses/index.jte -->
@import org.example.hexlet.dto.courses.CoursesPage
@param CoursesPage page

@template.layout.page(
    page = page,
    content = @`
        @if(page.getCourses().isEmpty())
            <p>Пока не добавлено ни одного курса</p>
        @else
            @for(var course : page.getCourses())
                <div>
<!--                     <h2><a href="/courses/${course.getId()}">${course.getName()}</a></h2> -->
<!--                     <p>${course.getDescription()}</p>                                     -->
                    <tr>
                        <td>
                            ${course.getId()}
                        </td>
                        <td>
                            <a href="/courses/${course.getId()}">${course.getName()}  ${course.getDescription()}</a>
                        </td>
                    </tr>
                </div>
            @endfor
        @endif
        <form action="/courses" method="get">
            <input type="search" required name="term" value="${page.getTerm()}" />
            <input type="submit" value="Искать" />
        </form>
    `,
    footer = @`
        <p>Thank for visiting, come again soon!</p>
        <a href="https://evg-c.github.io" target="_blank">My GitHub</a>
    `
)
