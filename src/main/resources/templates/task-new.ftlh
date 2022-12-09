<!DOCTYPE html>
<html lang="en">
<head>
    <title>JTodo</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
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
                    <a class="nav-link" aria-current="page" href="/all">All Tasks</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="#">New Task</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="container text-center my-3">
    <div class="row justify-content-md-center">
        <h1 class="display-2">New Task</h1>
    </div>
    <div class="row justify-content-md-center">
        <div class="col-md-6 col-sm-12">
            <form action="/api/new" method="post">
                <div class="mb-3">
                    <label for="description-input" class="form-label">Description</label>
                    <input type="text" class="form-control" id="description-input" name="description" required>
                </div>
                <div class="mb-3">
                    <select class="form-select" aria-label="Repeating the task" name="repeatType">
                        <option selected>Daily</option>
                        <option value="Weekly">Weekly</option>
                        <option value="Monthly">Monthly</option>
                        <option value="Yearly">Yearly</option>
                    </select>
                </div>
                <div class="mb-3 form-check">
                    <label class="form-label" for="number-of-repeat-input">Number of repeat</label>
                    <input type="number" class="form-control" id="number-of-repeat-input" name="repeatNumber" required>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
        crossorigin="anonymous"></script>

</body>
</html>