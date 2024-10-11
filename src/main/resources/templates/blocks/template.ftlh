<#macro template>
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
        <ul class="nav nav-pills justify-content-center custom-bg shadow rounded" style="padding: 7px;   display: flex; align-items: center">
            <div style='padding-right: 95px; font-weight: 900;' class="h5 fw-bold text-black">EPM</div>
            <li class="nav-item">
                <a class="nav-link active h5 fw-bold text-white" aria-current="page" href="#">РАСПИСАНИЕ</a>
            </li>
            <li class="nav-item">
                <a class="nav-link h5 fw-bold text-white" href="#">КУРСЫ</a>
            </li>
            <li class="nav-item">
                <a class="nav-link h5 fw-bold text-white" href="#">СООБЩЕНИЯ</a>
            </li>
            <li class="nav-item">
                <a class="nav-link h5 fw-bold text-white" href="#">НОВОСТИ</a>
            </li>
            <div style='padding-left: 100px;'>
                <button type="button" class="btn btn-icon ">
                    <i class="bi bi-person-fill" style="font-size: 2rem;"></i>
                </button>
            </div>
        </ul>
        <h1 class="aa">dfsfsdfdsfsfd</h1>
        <div class="container mt-4">
            <#nested />
        </div>
    </body>

    </html>
</#macro>