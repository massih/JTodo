<!DOCTYPE html>
<html lang="en">
<head>
    <title>JTodo</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css"
          integrity="sha512-xh6O/CkQoPOWDdYTDqeRdPCVd1SpvCA9XXcUnZS2FmJNp1coAFzvtCN9BmamE+4aHK8yyUHUSCcJHgXloTyT2A=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
</head>
<body>
<nav class="navbar navbar-dark bg-dark navbar-expand-lg">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">JTodo</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="/">Today</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="#">All Tasks</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/new">New Task</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="container text-center my-3">
    <div class="row justify-content-md-center">
        <h1 class="display-2">List of Tasks</h1>
    </div>
    <div class="row justify-content-md-center">
        <table class="table">
            <tr>
                <th>Description</th>
                <th>Repeat type</th>
                <th>Repeat number</th>
                <th>Edit</th>
            </tr>

            <#list tasks as task>
                <tr>
                    <td>${task.description}</td>
                    <td>${task.repeatType}</td>
                    <td>${task.repeatNumber}</td>
                    <td>
                        <a role="button" class="btn btn-sm" href="/update/${task.id}">
                            <i class="fa-solid fa-pen-to-square"></i>
                        </a>
                    </td>
                </tr>
            </#list>
        </table>

    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
        crossorigin="anonymous"></script>

</body>
</html>