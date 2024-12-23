
<#macro template(avatarUrl)>
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

        <script>
            document.addEventListener("DOMContentLoaded", function() {
                var currentPage = window.location.pathname;  // Получаем текущий путь
                var navItems = document.querySelectorAll('.nav-link');

                navItems.forEach(function(item) {
                    if (item.getAttribute('href') === currentPage) {
                        item.classList.add('active');
                    } else {
                        item.classList.remove('active');
                    }
                });
            });
        </script>
    </head>

    <body>


        <ul class="nav nav-pills justify-content-center custom-bg shadow rounded" style="padding: 7px;   display: flex; align-items: center">
            <div style='padding-right: 95px; font-weight: 900;' class="h5 fw-bold text-black">EPM</div>
            <li class="nav-item">
                <a class="nav-link active h5 fw-bold text-white" aria-current="page" href="/" id="schedule">РАСПИСАНИЕ</a>
            </li>
            <li class="nav-item">
                <a class="nav-link h5 fw-bold text-white" href="/courses" id="courses">КУРСЫ</a>
            </li>
            <li class="nav-item">
                <a class="nav-link h5 fw-bold text-white" href="/messages">СООБЩЕНИЯ</a>
            </li>
            <li class="nav-item">
                <a class="nav-link h5 fw-bold text-white" href="/news">НОВОСТИ</a>
            </li>
            <div style='padding-left: 100px;'>
                <a href="/user" class="btn btn-icon">
                    <img src="${avatarUrl}" class="btn-image-img" style="height: 60px">
                </a>
            </div>
        </ul>



        <div class="container mt-4">
            <#nested />
        </div>




    </body>


    </html>
</#macro>