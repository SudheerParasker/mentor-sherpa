<%-- 
    Document   : layout
    Created on : May 28, 2016, 5:26:43 PM
    Author     : Sudheer.Parasker@SnapIT.Solutions
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.6 -->
  <link rel="stylesheet" href="../../themes/AdminLTE/bootstrap/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
  <!-- jvectormap -->
  <link rel="stylesheet" href="../../themes/AdminLTE/plugins/jvectormap/jquery-jvectormap-1.2.2.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="../../themes/AdminLTE/dist/css/AdminLTE.min.css">
  <!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
  <link rel="stylesheet" href="../../themes/AdminLTE/dist/css/skins/_all-skins.min.css">

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
   <title><tiles:insertAttribute name="title" ignore="true" /></title>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
        <tiles:insertAttribute name="header" />
        <tiles:insertAttribute name="leftnav" />
        <div class="content-wrapper">
        <tiles:insertAttribute name="breadcrumb" />
        <section class="content">
        <tiles:insertAttribute name="body" />
        </section>
        </div>
        <tiles:insertAttribute name="footer" />
        <tiles:insertAttribute name="scriptjs" />

</div>
    </body>
</html>
