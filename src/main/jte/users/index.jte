@import org.example.hexlet.NamedRoutes
@import org.example.hexlet.dto.users.UsersPage
@param UsersPage page
<!-- src/main/jte/users/index.jte -->
@template.layout.page(
    page = page,
    content = @`
        @if(page.getUsers().isEmpty())
            <p>Пока не добавлено ни одного пользователя</p>
        @else
            @for(var user: page.getUsers())
                <div>
                    <tr>
                        <td>
                            ${user.getId()}
                        </td>
                        <td>
                            <a href="users/${user.getId()}">${user.getName()} ${user.getEmail()}</a>
                        </td>
                    </tr>
                </div>
            @endfor
        @endif
        <a href="${NamedRoutes.buildUserPath()}">Добавить пользователя</a>
    `,
    footer = @`
        <p>Thank for visiting</p>
        <a href="https://evg-c.github.io" target="_blank">My GitHub</a>
    `
)
