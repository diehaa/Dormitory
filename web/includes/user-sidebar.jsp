<%-- 
    Document   : sidebar
    Created on : 1 thg 7, 2023, 09:01:01
    Author     : phangiabao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="layoutSidenav_nav">
    <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
        <div class="sb-sidenav-menu">
            <div class="nav">
                <div class="sb-sidenav-menu-heading">Core</div>
                <a class="nav-link" href="dashboard.php">
                    <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                    Dashboard
                </a>


                <a class="nav-link" href="manage-users.php">
                    <div class="sb-nav-link-icon"><i class="fas fa-users"></i></div>
                    Manage Users
                </a>
                <a class="nav-link" href="create.php">
                    <div class="sb-nav-link-icon"><i class="fas fa-users"></i></div>
                    Add Users
                </a>

                <a class="nav-link" href="bwdates-report-ds.php">
                    <div class="sb-nav-link-icon"><i class="fa fa-calendar"></i></div>
                    B/w Dates Report
                </a>

                <a class="nav-link" href="logout.php">
                    <div class="sb-nav-link-icon"><i class="fas fa-sign-out-alt"></i></div>
                    Signout
                </a>
            </div>
        </div>

    </nav>
</div>