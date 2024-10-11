<!doctype html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
          integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn"
          crossorigin="anonymous">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.5.0/font/bootstrap-icons.min.css">
    <link rel="stylesheet" href="/static/css/style.css">
    <title>EPM</title>
</head>
<body>

<div class="d-flex justify-content-center align-items-center vh-100">
    <div class="card w-50 shadow rounded">
        <div class="card-header custom-bg">
            <h4 class="d-flex justify-content-center ">Авторизация</h4>
        </div>
        <div class="card-body">
            <form action="/login" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                <div class="mb-3">
                    <label class="visually-hidden" for="username">
                        <h6>Логин</h6>
                    </label>
                    <div class="input-group">
                        <div class="input-group-text">@</div>
                        <input type="text" class="form-control" id="username" name="username" required>
                    </div>
                </div>

                <div class="mb-3">
                    <label for="inputPassword" class="form-label">
                        <h6>Пароль</h6>
                    </label>
                    <input type="password" id="inputPassword" class="form-control" name="password" required>
                </div>

                <div class="d-flex justify-content-center">
                    <button type="submit" class="btn custom-bg btn-center shadow-sm rounded">Войти</button>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>
