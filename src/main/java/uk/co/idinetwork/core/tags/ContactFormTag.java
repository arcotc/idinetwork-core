package uk.co.idinetwork.core.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

@SuppressWarnings("serial")
public class ContactFormTag extends TagSupport {
	private String msg;
	private String email;
	private String confirmationUrl;
	
	public ContactFormTag() {
	}
	
	@Override
	public int doStartTag() throws JspException {
		int response = EVAL_BODY_AGAIN;
		
		JspWriter out = pageContext.getOut();
		
		try {
			out.println("<span class=\"contactForm.message\">" + msg + "</span>");
			out.println("<form action=\"/site/contact/submit\" method=\"post\">");
			out.println("   <input type=\"hidden\" name=\"confirmationUrl\" value=\"" + confirmationUrl + "\">");
			out.println("   <input type=\"hidden\" name=\"email\" value=\"" + email + "\">");
			out.println("   <table width=\"100%\">");
			out.println("       <tr>");
			out.println("           <td valign=\"top\" id=\"contactForm\">");
			out.println("	            Name:");
			out.println("           </td>");
			out.println("           <td valign=\"top\" id=\"contactForm\">");
			out.println("	            <input type=\"text\" name=\"name\">");
			out.println("           </td>");
			out.println("       </tr>");
			out.println("       <tr>");
			out.println("           <td valign=\"top\" id=\"contactForm\">");
			out.println("	            Email Address:");
			out.println("           </td>");
			out.println("           <td valign=\"top\" id=\"contactForm\">");
			out.println("	            <input type=\"text\" name=\"emailAddress\">");
			out.println("           </td>");
			out.println("       </tr>");
			out.println("       <tr>");
			out.println("           <td valign=\"top\" id=\"contactForm\">");
			out.println("	            Message:");
			out.println("           </td>");
			out.println("           <td valign=\"top\" id=\"contactForm\">");
			out.println("	            <textarea id=\"contactForm\" name=\"userMsg\" cols=\"50\" rows=\"8\"></textarea><br/>");
			out.println("           </td>");
			out.println("       </tr>");
			out.println("       <tr>");
			out.println("           <td colspan=\"99\" id=\"contactForm\">");
			out.println("	            <input type=\"submit\" value=\"Send\" id=\"contactForm\">");
			out.println("           </td>");
			out.println("       </tr>");
			out.println("   </table>");
			out.println("</form>");
		}
		catch (IOException ioException) {
			
		}
		
		return response;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getConfirmationUrl() {
		return confirmationUrl;
	}

	public void setConfirmationUrl(String confirmationUrl) {
		this.confirmationUrl = confirmationUrl;
	}
}
