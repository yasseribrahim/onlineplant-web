<%@page import="com.online.plant.models.TransactionDetails"%>
<%@page import="com.online.plant.client.call.TransactionsClient"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    int id = Integer.parseInt(request.getParameter("id"));
    int plantId = Integer.parseInt(request.getParameter("plantId"));
    TransactionDetails details = new TransactionsClient().getTransactionPlantDetails(id, plantId);
%>
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
                        <div class="d-sm-flex align-items-center justify-content-between mb-4">
                            <h1 class="h3 mb-0 text-gray-800">Transaction Plant Edit</h1>
                        </div>
                        <div class="row">
                            <div class="col-lg-12 mb-4">

                                <!-- Illustrations -->
                                <div class="card shadow mb-4">
                                    <div class="card-header py-3">
                                        <h6 class="m-0 font-weight-bold text-primary">Transaction No: <%=id%></h6>
                                    </div>
                                    <div class="card-body">
                                        <form action="transactions/plant/edit" method="post">
                                            <input type="hidden" name="id" value="<%=id%>"/>
                                            <input type="hidden" name="plantId" value="<%=plantId%>"/>
                                            <div class="form-group">
                                                <label>Name</label>
                                                <input type="text" readonly class="form-control" value="<%=details.getName()%>">
                                            </div>
                                            <div class="row">
                                                <div class="form-group col-4">
                                                    <label>Type</label>
                                                    <input type="text" readonly class="form-control" value="<%=details.getType()%>">
                                                </div>
                                                <div class="form-group col-4">
                                                    <label>Age</label>
                                                    <input type="text" readonly class="form-control" value="<%=details.getAge()%>">
                                                </div>
                                                <div class="form-group col-4">
                                                    <label>Price</label>
                                                    <input type="text" readonly class="form-control" value="<%=details.getPrice()%>">
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="form-group col-6">
                                                    <label>Quantity</label>
                                                    <input type="number" name="quantity" required="" class="form-control" value="<%=details.getQuantity()%>">
                                                </div>
                                            </div>
                                            <button type="submit" class="btn btn-success">Edit Quantity</button>
                                        </form>
                                        <hr>
                                        Click here to <a href="seller-transaction-plants.jsp?id=<%=id%>&plantId=<%=plantId%>">Back &rarr;</a>
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
