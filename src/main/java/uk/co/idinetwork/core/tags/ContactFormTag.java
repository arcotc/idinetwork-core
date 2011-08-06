package uk.co.idinetwork.core.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import net.tanesha.recaptcha.ReCaptcha;
import net.tanesha.recaptcha.ReCaptchaFactory;

import org.apache.commons.lang.StringUtils;

@SuppressWarnings("serial")
public class ContactFormTag extends TagSupport {
	private String msg;
	private String fromEmail;
	private String toEmail;
	private String confirmationUrl;
	
	public ContactFormTag() {
	}
	
	@Override
	public int doStartTag() throws JspException {
		int response = EVAL_BODY_AGAIN;
		
		JspWriter out = pageContext.getOut();
		
		try {
			out.println("<span class=\"contactForm-message\">" + msg + "<br/><br/></span>");
			String errorMsg = getAttribute(pageContext, "errorMsg");
			if (!StringUtils.isBlank(errorMsg)) {
				out.println("<span style=\"color: red\">" + errorMsg + "<br/><br/></span>");
			}
			out.println("<form action=\"/site/contact/submit\" method=\"post\">");
			out.println("   <input type=\"hidden\" name=\"confirmationUrl\" value=\"" + confirmationUrl + "\">");
			out.println("   <input type=\"hidden\" name=\"fromEmail\" value=\"" + fromEmail + "\">");
			out.println("   <input type=\"hidden\" name=\"toEmail\" value=\"" + toEmail + "\">");
			out.println("   <table width=\"100%\">");
			out.println("       <tr>");
			out.println("           <td valign=\"top\" id=\"contactForm\">");
			out.println("	            Name: *");
			out.println("           </td>");
			out.println("           <td valign=\"top\" id=\"contactForm\">");
			out.println("	            <input type=\"text\" name=\"name\" value=\"" + getAttribute(pageContext, "name") + "\">");
			out.println("           </td>");
			out.println("       </tr>");
			out.println("       <tr>");
			out.println("           <td valign=\"top\" id=\"contactForm\">");
			out.println("	            Email Address: *");
			out.println("           </td>");
			out.println("           <td valign=\"top\" id=\"contactForm\">");
			out.println("	            <input type=\"text\" name=\"usersEmail\" value=\"" + getAttribute(pageContext, "usersEmail") + "\">");
			out.println("           </td>");
			out.println("       </tr>");
			out.println("       <tr>");
			out.println("           <td valign=\"top\" id=\"contactForm\">");
			out.println("	            Message: *");
			out.println("           </td>");
			out.println("           <td valign=\"top\" id=\"contactForm\">");
			out.println("	            <textarea id=\"contactForm\" name=\"userMsg\" cols=\"50\" rows=\"8\">" + getAttribute(pageContext, "userMsg") + "</textarea><br/>");
			out.println("           </td>");
			out.println("       </tr>");
			out.println("       <tr>");
			out.println("           <td valign=\"top\" id=\"contactForm\" colspan=\"99\">");

	        ReCaptcha c = ReCaptchaFactory.newReCaptcha("6Lco2cUSAAAAACOgUdeoBlUd_IVYLm31LmTf0Eyd", "6Lco2cUSAAAAAO3-55M3iPPOfr3FIe_4n5XYsFBW", false);
	        out.print(c.createRecaptchaHtml(null, null));
			
			out.println("				<div id=\"dynamic_recaptcha_1\">");
			out.println("           </td>");
			out.println("       </tr>");
			
			out.println("       <tr>");
			out.println("           <td colspan=\"99\" id=\"contactForm\">");
			out.println("	            <input type=\"submit\" value=\"Send\" id=\"contactForm\">");
			out.println("           </td>");
			out.println("       </tr>");
			out.println("       <tr>");
			out.println("           <td colspan=\"99\" id=\"contactForm\">");
			out.println("	            * = Required");
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

	public String getFromEmail() {
		return fromEmail;
	}

	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}

	public String getToEmail() {
		return toEmail;
	}

	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}

	public String getConfirmationUrl() {
		return confirmationUrl;
	}

	public void setConfirmationUrl(String confirmationUrl) {
		this.confirmationUrl = confirmationUrl;
	}
	
	private String getAttribute(PageContext pageContext, String attributeName) {
		Object o = pageContext.getRequest().getAttribute(attributeName);
		if ((o != null) && (o instanceof String)) {
			return (String) o;
		}
		else {
			return "";
		}
	}
}
