<%-- 
    Document   : dashboard
    Created on : 1 thg 7, 2023, 09:34:40
    Author     : phangiabao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Dashboard | Dormitory Management </title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="../css/styles.css" rel="stylesheet" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js" crossorigin="anonymous"></script>
    </head>

    <body class="sb-nav-fixed">
        <?php include_once('includes/navbar.php'); ?>
        <div id="layoutSidenav">
            <?php include_once('includes/sidebar.php'); ?>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">Dashboard</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">Dashboard</li>
                        </ol>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="card">
                                    <div class="card-header">
                                        Thông báo
                                    </div>
                                    <div class="card-body">
                                        <table class="table">
                                            <thead>
                                                <tr>
                                                    <th scope="col">Ngày Đăng</th>
                                                    <th scope="col">Thông báo</th>
                                                    <th style="width:10%" scope="col">Đăng bởi</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            <?php $sqlnews = "SELECT news.id newsid, news.title,news.updated_at,  users.username postby, department.name fromdept FROM news  INNER JOIN users ON news.user_id = users.id INNER JOIN department on news.department_id = department.id ORDER BY news.id DESC LIMIT 10";
                                        $newsList = executeResult($sqlnews);
                                       
                                        foreach ($newsList as  $row) { ?>
                                            <tr>
                                                <td><?php echo $row['updated_at']; ?></td>
                                                <td><a style="text-decoration: none;" href="news-detail.php?id=<?php echo $row['newsid']; ?>">[<?php echo $row['fromdept']; ?>] <?php echo $row['title']; ?></a></td>
                                                <td><?php echo $row['postby']; ?></td>

                                                
                                            </tr>


                                        <?php 
                                        } ?>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>

                        </div>

                    </div>
                </main>
                <!-- foooter -->
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
    </body>

    </html>
