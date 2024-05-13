<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="view-port" content="width=device-width, initial-scale=1" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
          crossorigin="anonymous">
    <title>Courses build example</title>
  </head>
  <body>
    <form action="/courses" method="post">
      <div>
        <label>
          Название курса
          <input type="text" name="name" />
        </label>
      </div>
      <div>
        <label>
          Описание курса
          <input type="text" name="description" />
        </label>
      </div>
      <input type="submit" value="Добавить курс" />
    </form>
  </body>
</html>