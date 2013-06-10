<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="id" required="true" %>
<%@ attribute name="name" required="true" %>
<%@ attribute name="value" required="true" %>
<%@ attribute name="required" required="true" %>
<input type="text" id="${id}" name="${name}" value="${value}" required="${required}"/>
<script type="text/javascript">
  $("#${id}").datepicker({
      dateFormat: 'dd/mm/yy',
      showOtherMonths: true,
      selectOtherMonths: true,
      changeMonth: true,
      changeYear: true
  });
</script>
