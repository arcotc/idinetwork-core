package uk.co.idinetwork.core.tags;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.RequestContextUtils;

import uk.co.idinetwork.core.model.Config;
import uk.co.idinetwork.core.service.GeneralService;

@SuppressWarnings("serial")
public class ConfigTag extends TagSupport {
	private WebApplicationContext applicationContext;
	private GeneralService generalService;
	private String key;
	private Boolean debug = false;
	
	public ConfigTag() {
	}
	
	@Override
	public int doStartTag() throws JspException {
		int response = EVAL_BODY_AGAIN;
		applicationContext = RequestContextUtils.getWebApplicationContext(pageContext.getRequest(), pageContext.getServletContext());
		
		Object bean = applicationContext.getBean("generalService");
		if (bean == null) {
			throw new RuntimeException("GeneralService not configured properly");
		}
		else {
			generalService = (GeneralService) bean;
		}
		
		JspWriter out = pageContext.getOut();
		
		try {
			if (debug) {
				List<Config> configs = generalService.findConfig();
				for (Config config : configs) {
					out.println("<!-- config debug");
					out.println(config.getKey() + " -> " + config.getValue());
					out.println("-->");
				}
			}
			else {
				List<Config> configs = generalService.findConfig();
				String value = "not-set";
				for (Config config : configs) {
					if (config.getKey().equalsIgnoreCase(key)) {
						value = config.getValue();
						break;
					}
				}
				out.print(value);
			}
		}
		catch (IOException ioException) {
			
		}
		
		return response;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	public Boolean getDebug() {
		return debug;
	}
	
	public void setDebug(Boolean debug) {
		this.debug = debug;
	}
}
