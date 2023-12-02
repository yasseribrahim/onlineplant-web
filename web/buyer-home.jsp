<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="includes/imports.jsp"%>
    </head>
    <body>
        <!-- Page Wrapper -->
        <div id="wrapper">

            <!-- Content Wrapper -->
            <div id="content-wrapper" class="d-flex flex-column">

                <!-- Main Content -->
                <div id="content">

                    <%@include file="includes/header.jsp" %>

                    <!-- Begin Page Content -->
                    <div class="container-fluid">
                        <div class="mb-4">
                            <div class="row w-100">
                                <h1 class="h3 mb-0 text-gray-800">Home</h1>
                                    &ensp;
                                    &ensp;
                                <a href="buyer-plants-search.jsp" class="btn btn-secondary shadow-sm">
                                    <i class="fas fa-search fa-sm text-white-50"></i>&ensp;Plants Search</a>
                                    &ensp;
                                    &ensp;
                                <a href="buyer-transactions.jsp" class="btn btn-primary shadow-sm float-right">
                                    <i class="fas fa-download fa-sm text-white-50"></i>&ensp;List Transactions</a>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-12 mb-4">
                                <div class="card shadow mb-4">
                                    <div class="card-header py-3">
                                        <h6 class="m-0 font-weight-bold text-primary">Welcome Buyer</h6>
                                    </div>
                                    <div class="card-body">
                                        <div class="text-center">
                                            <img class="img-fluid px-3 px-sm-4 mt-3 mb-4" style="width: 25rem;"
                                                 src="assets/img/undraw_posting_photo.svg" alt="...">
                                        </div>
                                        <p>Welcome in Online Plant </p>
                                        Click here to <a href="buyer-transactions.jsp">List Transactions &rarr;</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- End of Main Content -->

                <%@include file="includes/footer.jsp" %>

            </div>
            <!-- End of Content Wrapper -->

        </div>
        <!-- End of Page Wrapper -->

        <%@include file="includes/tail.jsp" %>
    </body>
</html>
