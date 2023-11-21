<!doctype html>
<html lang="en">

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="./assets/fonts/icomoon/style.css">

    <link rel="stylesheet" href="./assets/css/owl.carousel.min.css">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="./assets/css/bootstrap.min.css">

    <!-- Style -->
    <link rel="stylesheet" href="./assets/css/style.css">

    <title>Etat de stock</title>
</head>

<body>
    <div class="content">
        <div class="container">
            <h2 class="mb-5">Etat de stock</h2>

            <div class="row mb-3">
                <div class="col-md-2">
                    <label for="startDate" class="form-label">Date de début</label>
                    <input type="date" class="form-control" id="startDate">
                </div>
                <div class="col-md-2">
                    <label for="endDate" class="form-label">Date de fin</label>
                    <input type="date" class="form-control" id="endDate">
                </div>
                <div class="col-md-2">
                    <label for="endDate" class="form-label">Date de fin</label>
                    <input type="date" class="form-control" id="endDate">
                </div>
            </div>

            <div class="row mt-3">
                <div class="col-md-12 text-end">
                    <button type="submit" class="btn btn-primary">Valider</button>
                </div>
            </div>

            <br>

            <div class="table-responsive custom-table-responsive">

                <table class="table custom-table">
                    <thead>
                        <tr>
                            <th scope="col">Article</th>
                            <th scope="col">Unité</th>
                            <th scope="col">Qtt totale</th>
                            <th scope="col">Reste</th>
                            <th scope="col">Montant</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr scope="row">
                            <td>
                                1392
                            </td>
                            <td><a href="#">James Yates</a></td>
                            <td>
                                Web Designer
                                <small class="d-block">Far far away, behind the word mountains</small>
                            </td>
                            <td>+63 983 0962 971</td>
                            <td>NY University</td>
                        </tr>
                        <tr class="spacer">
                            <td colspan="100"></td>
                        </tr>
                        <tr scope="row">
                            <td>4616</td>
                            <td><a href="#">Matthew Wasil</a></td>
                            <td>
                                Graphic Designer
                                <small class="d-block">Far far away, behind the word mountains</small>
                            </td>
                            <td>+02 020 3994 929</td>
                            <td>London College</td>
                        </tr>
                        <tr class="spacer">
                            <td colspan="100"></td>
                        </tr>
                        <tr scope="row">
                            <td>9841</td>
                            <td><a href="#">Sampson Murphy</a></td>
                            <td>
                                Mobile Dev
                                <small class="d-block">Far far away, behind the word mountains</small>
                            </td>
                            <td>+01 352 1125 0192</td>
                            <td>Senior High</td>
                        </tr>
                        <tr class="spacer">
                            <td colspan="100"></td>
                        </tr>
                        <tr scope="row">
                            <td>9548</td>
                            <td><a href="#">Gaspar Semenov</a></td>
                            <td>
                                Illustrator
                                <small class="d-block">Far far away, behind the word mountains</small>
                            </td>
                            <td>+92 020 3994 929</td>
                            <td>College</td>
                        </tr>

                    </tbody>
                </table>
            </div>


        </div>

    </div>



    <script src="./assets/js/jquery-3.3.1.min.js"></script>
    <script src="./assets/js/popper.min.js"></script>
    <script src="./assets/js/bootstrap.min.js"></script>
    <script src="./assets/js/main.js"></script>
</body>

</html>