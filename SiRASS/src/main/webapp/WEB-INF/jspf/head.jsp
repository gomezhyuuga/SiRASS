<%@page contentType="text/html;charset=uft-8" pageEncoding="UTF-8"%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <title>SiRASS - <%= request.getParameter("title")%> </title>
        <!--[if gte IE 9]>
            <style type="text/css">
            .gradient {
                filter: none;
            }
            </style>
        <![endif]-->
        <!--[if lt IE 9]>
        <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->

        <meta name="description" content="Sistema de Registro y Administraci&oacute;n de Servicio Social" />
        <meta name="author" content="Skyforge" />
        <meta name="keywords" content="servicio social, uacm, skyforge, practicas profesionales" />
        <link rel="stylesheet" href="/SiRASS/bootstrap/css/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" type="text/css" href="/SiRASS/css/main.css" />
        <link rel="icon" href="/SiRASS/favicon.ico" type="image/x-icon" />
        <!-- styles for this page -->
<%  if (request.getParameter("style") != null ) {
        for (String style : request.getParameterValues("style")) { %>
        <link rel="stylesheet" type="text/css" href="/SiRASS/css/<%= style %>.css" />
<%      }
    } %>