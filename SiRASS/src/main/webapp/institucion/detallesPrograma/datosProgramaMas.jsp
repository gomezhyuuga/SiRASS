<div class="span11">
    <div class="well">               
        <h3>
            Información Programa:
        </h3>
        <br>
        <div class="row">
            <div class="span2 pull-left">
                <h4>
                    Desarrollo
                </h4>            
            </div>
            <div class="span8">
                <h5>
                    <%=programaSS.getDesarrollo()%>
                </h5>            
            </div>
        </div>
                <br>
        <div class="row">
            <div class="span2 pull-left">
                <h4>
                    Justificación
                </h4>
            </div>
            <div class="span8">
                <h5>
                    <%=programaSS.getJustificacion()%>
                </h5>
            </div>
        </div>
        <br>
        <div class="row">
            <div class="span2 pull-left">
                <h4>
                    Objetivo General
                </h4>
            </div>
            <div class="span8">
                <h5 align="center">
                    <%=programaSS.getObjGeneral()%>
                </h5>                
            </div>
        </div>
        <br>
        <div class="row">
            <div class="span2 pull-left">
                <h4>
                    Alcance
                </h4>                
            </div>
            <div class="span8">
                <%
                while(it1.hasNext()){
                        AlcancePrograma alc = (AlcancePrograma)it1.next();;
                %>
                <h5>
                    <%=alc.getDescripcion()%>  
                </h5>
                <%}%>                
            </div>
        </div>        
        <br>
        <div class="row">
            <div class="span2 pull-left">
                <h4>
                    Area
                </h4>
            </div>
            <div class="span8">
                <h5>
                    <%=programaSS.getArea()%>
                </h5>
            </div>
        </div>
        <br>
        <div class="row">
            <div class="span2 pull-left">
                <h4>
                    Observaciones
                </h4>
            </div>
            <div class="span8">
                <h5>
                    <%=programaSS.getObservaciones()%>
                </h5>
            </div>
        </div>
        <br>
        <div class="row">
            <div class="span2 pull-left">
                <h4>
                    Evaluación
                </h4>
            </div>
            <div class="span8">
                <h5>
                    <%=programaSS.getEvaluacion()%>
                </h5>
            </div>
        </div>
        <br>
        <div class="row">
            <div class="span2 pull-left">
                <h4>
                    Recursos
                </h4>
            </div>
            <div class="span8">
                <h5>
                    <%=programaSS.getRecursos()%>
                </h5>
            </div>
        </div>
        <br>
        <div class="row">
            <div class="span2 pull-left">
                <h4>
                    Resultados
                </h4>
            </div>
            <div class="span8">
                <h5>
                    <%=programaSS.getResultados()%>
                </h5>
            </div>
        </div>
    </div>
</div>