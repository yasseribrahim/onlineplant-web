<%@page import="com.online.plant.client.call.TransactionsClient"%>
<%@page import="com.online.plant.models.Transaction"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    List<Transaction> transactions = new TransactionsClient().getTransactionsSeller(request.getRemoteUser());
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
                        <h1 class="h3 mb-2 text-gray-800">Transactions</h1>
                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">Transactions</h6>
                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered" id="dataTable" width="100&percnt;" cellspacing="0">
                                        <thead>
                                            <tr>
                                                <th>Id</th>
                                                <th>Buyer</th>
                                                <th>Seller</th>
                                                <th>Date</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%for (Transaction transaction : transactions) {%>
                                            <tr>
                                                <td><%=transaction.getId()%></td>
                                                <td><%=transaction.getBuyerId()%></td>
                                                <td><%=transaction.getSellerId()%></td>
                                                <td><%=transaction.getDate()%></td>
                                                <td><a class="btn btn-primary" href="seller-transaction-details.jsp?id=<%=transaction.getId()%>">View Details</a></td>
                                            </tr>
                                            <%}%>
                                        </tbody>
                                    </table>
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
