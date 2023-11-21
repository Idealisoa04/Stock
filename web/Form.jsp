<%@page import="stock.Magasin"%>
<%@page import="stock.Article"%>
<%@page import="stock.List"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Mouvement de stock</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
    <link rel="stylesheet" href="./assets/css/bootstrap.min.css">

    <link rel="stylesheet" href="./assets/css/styleMouvement.css">

</head>

<%
    List lists = (List) request.getAttribute("list");
    Article[] articles = lists.getArticles();
    Magasin[] magasins = lists.getMagasins();
%>

<body>
    <!-- partial:index.partial.html -->
    <div class="demo-page">

        <main class="demo-page-content">
            <form action="MouvementServlet" method="post">
                <section>
                    <div class="href-target" id="input-types"></div>
                    <h1>
                        Mouvement de stock
                    </h1>

                    <div class="nice-form-group">
                        <label>Date de sortie</label>
                        <input type="datetime-local"  name="datetime" class="form-control" value="" />
                    </div>

                    <div class="nice-form-group">
                        <label>Article</label>
                        <select name="article">
                          <option>Choisissez un article...</option>
                           <%
                                for(int i=0 ; i<articles.length ; i ++) {
                            %>
                            <option value="<%= articles[i].getId() %>"> <%= articles[i].getNom() %> </option>
                            <%
                                }
                            %> 

                        </select>
                    </div>

                    <div class="nice-form-group">
                        <label>Quantité</label>
                        <input type="number" name="quantite" placeholder="combien vont sortir..." value="" />
                    </div>

                    <div class="nice-form-group">
                        <label>Magasin</label>
                        <select name="magasin">
                          <option>Choisissez le magasin...</option>
                          <%
                                for(int i=0 ; i<magasins.length ; i ++) {
                            %>
                            <option value="<%= magasins[i].getId() %>"> <%= magasins[i].getNom() %> </option>
                            <%
                                }
                            %>
                        </select>
                    </div>

                    <details>
                        <summary>
                            <button type="submit" class="²btn-primary">Valider</button>
                        </summary>
                        <script src="https://gist.github.com/nielsVoogt/e25c9c8f2b8456bbd1239b775d21333f.js"></script>
                    </details>
                </section>
            </form>
        </main>
    </div>
    <!-- partial -->

</body>

</html>