<!-- src/main/jte/users/index.jte -->
@import org.example.hexlet.dto.users.UsersPage
@param UsersPage page

@template.layout.page(
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
    `,
    footer = @`
        <p>Thank for visiting</p>
        <a href="https://evg-c.github.io" target="_blank">My GitHub</a>
    `
)
