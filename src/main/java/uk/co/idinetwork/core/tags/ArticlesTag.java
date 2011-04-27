package uk.co.idinetwork.core.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.RequestContextUtils;

import uk.co.idinetwork.core.service.BloggerService;

import com.google.gdata.client.GoogleService;

@SuppressWarnings("serial")
public class ArticlesTag extends TagSupport {
	private WebApplicationContext applicationContext;
	private BloggerService articleService;
	private String tags;
	private String name;

	public ArticlesTag() {
	}
	
	@Override
	public int doStartTag() throws JspException {
		int response = EVAL_BODY_AGAIN;
		
		applicationContext = RequestContextUtils.getWebApplicationContext(pageContext.getRequest(), pageContext.getServletContext());
		
		Object bean = applicationContext.getBean("bloggerService");
		if (bean == null) {
			throw new RuntimeException("ArticleService not configured properly");
		}
		else {
			articleService = (BloggerService) bean;
		}

		GoogleService myService = new GoogleService("blogger", "continuing-to-learning");
		if (StringUtils.isBlank(tags)) {
			pageContext.getRequest().setAttribute("articles", articleService.loadUserBlogs(myService).values());
		}
		else {
			pageContext.getRequest().setAttribute(StringUtils.isBlank(name) ? "articles" : name, articleService.loadUserBlogs(myService, tags.toLowerCase()).values());
		}
		
		return response;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}
	
	public String getTags() {
		return tags;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
