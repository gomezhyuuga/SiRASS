<%@page import="skyforge.sirass.model.prestador.Inscripcion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String aviso;
    if (request.getParameter("aviso") != null) {
        aviso = request.getParameter("aviso");
        if (aviso.equals("6")) {%>
            <p class="lead">Parece que a&uacute;n no est&aacute;s inscrito en ning&uacute;n 
            <strong>programa de Servicio Social</strong>. Puedes hacerlo pulsando la 
            opci&oacute;n de <em>Inscripci&oacute;n</em> en la barra lateral de 
            navegaci&oacute;n o pulsando el siguiente bot&oacute;n.</p>
            <a href="/SiRASS/prestador/inscripcion.jsp" class="btn btn-primary btn-large">
                Inscripci&oacute;n al Servicio Social
            </a>
<%      } else if (aviso.equals("1")) {%>
            <p class="lead">Tu solicitud de inscripci&oacute;n est&aacute; siendo validada por un responsable de Servicio Social. <a href="#"><strong>Ver estado</strong></a></p>
            <p>Una vez que se compruebe que es correcta, genera tu <em>Carta Compromiso</em> y entrega los papeles respectivos en las oficinas de Servicio Social.Una vez que se compruebe que es correcta, genera tu <em>Carta Compromiso</em> y entrega los papeles respectivos en las oficinas de Servicio Social.</p>
            <a href="#" class="btn btn-inverse btn-small">Generar carta compromiso</a>
<%      } else if (aviso.equals("2")) {%>
            <p>Actualmente est&aacute;s inscrito en el siguiente programa de Servicio Social:</p>
            <p class="lead"><strong>Apoyo al &aacute;rea acad&eacute;mico-administrativa en la UACM</strong></p>
            <h3>Horas acumuladas</h3>
<%          // Calcular horas
            int horas = 0;
            double width = 0;
            if (session.getAttribute("inscripcion") != null) {
                Inscripcion inscr = (Inscripcion) session.getAttribute("inscripcion");
                horas = inscr.getHorasRealizadas();
                width = (horas * 5) / 24;
            }
%>
            <div class="progress progress-success" style="height: 30px; border: 1px solid #8c8c8c;">
                <div class="bar" style="width: <%= width + "%" %>; height: 30px;"></div>
            </div>
            <h6><%= horas %>/480 - <%= width + "%"%></h8>
<%      } else if (aviso.equals("algo")) {%>
            <p>algo</p>
<%      }
    }
%>