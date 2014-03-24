package org.apache.jsp.WEB_002dINF.pages;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class create_005fpatient_005fform_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<form method=\"post\" action=\"patient_form_submit\">\n");
      out.write("\n");
      out.write("    First Name : <input type=\"text\" name=\"NameFirst\"><br>\n");
      out.write("    Last Name : <input type=\"text\" name=\"NameLast\" maxlength=\"20\"><br>\n");
      out.write("    Phone Number : <input type=\"text\" name=\"Phone\" maxlength=\"20\"><br>\n");
      out.write("    Username : <input type=\"text\" name=\"Username\" maxlength=\"20\"><br>\n");
      out.write("    Password : <input type=\"password\" name=\"Password\" maxlength=\"20\"><br>\n");
      out.write("    Street : <input type=\"text\" name=\"Street\" maxlength=\"20\"><br>\n");
      out.write("    City : <input type=\"text\" name=\"City\" maxlength=\"20\"><br>\n");
      out.write("    Province : <input type=\"text\" name=\"Province\" maxlength=\"20\"><br>\n");
      out.write("    Postal Code : <input type=\"text\" name=\"PostalCode\" maxlength=\"20\"><br>\n");
      out.write("    Default Doctor : <input type=\"text\" name=\"defaultDoc\" maxlength=\"20\"><br>\n");
      out.write("    Health Card : <input type=\"text\" name=\"healthCard\" maxlength=\"20\"><br>\n");
      out.write("    SIN : <input type=\"text\" name=\"SIN\" maxlength=\"20\"><br>\n");
      out.write("    Current Health : <input type=\"text\" name=\"currentHealth\" maxlength=\"20\"><br>\n");
      out.write("    <input type=\"submit\" value=\"submit\">\n");
      out.write("</form>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
