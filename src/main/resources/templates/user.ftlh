<#import "blocks/template.ftlh" as t>
<@t.template avatarUrl="/user/avatar?userId=${user.id}">
    <body>
    <div class="container mt-5">
        <div class="user-container">
            <img src="/student/avatar?studentId=${student.id}" class="img-thumbnail avatar" alt="Avatar">
            <form action="/user/upload-avatar" method="post" enctype="multipart/form-data">
                <div class="mb-3">
                    <input type="file"  name="avatar" accept="image/*" onchange="this.form.submit();">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"> <!-- CSRF токен -->

                </div>
            </form>

            <h2><p>${student.surnameStudent} ${student.nameStudent} ${student.patronymicStudent}</p></h2>
            <h5><p style="font-weight: 400; margin: 0;">${student.faculty.nameFaculty}</p></h5>
            <h5><p style="font-weight: 400; margin: 0;">${student.specialty.nameSpecialty}</p></h5>
            <h5><p style="font-weight: 400; margin: 0;">${student.groupName}</p></h5>
        </div>


    </div>
    </body>




</@t.template>