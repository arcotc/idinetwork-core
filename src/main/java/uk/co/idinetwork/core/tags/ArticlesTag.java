package uk.co.idinetwork.core.tags;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.RequestContextUtils;

import uk.co.idinetwork.core.model.Article;
import uk.co.idinetwork.core.service.BloggerService;

import com.google.gdata.client.GoogleService;

@SuppressWarnings("serial")
public class ArticlesTag extends TagSupport {
	private WebApplicationContext applicationContext;
	private BloggerService articleService;
	private String tags;
	private String name;
	private Integer maxItems = -1;

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
		String attributeName = "articles";
		Collection<Article> articles = new ArrayList<Article>();
		
		if (StringUtils.isBlank(tags)) {
			articles = articleService.loadArticlesOrderedByDateLatestFirst(myService);
		}
		else {
			attributeName = StringUtils.isBlank(name) ? "articles" : name;
			articles = articleService.loadArticlesOrderedByDateLatestFirst(myService, tags.toLowerCase());
		}
		
		if (maxItems > 0) {
			Collection<Article> temp = new ArrayList<Article>();
			int i=1;
			for (Article article : articles) {
				temp.add(article);
				
				if (i == maxItems) {
					break;
				}
				else {
					i++;
				}
			}
			
			articles = temp;
		}

		pageContext.getRequest().setAttribute(attributeName, articles);
		
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
	
	public void setMaxItems(Integer maxItems) {
		this.maxItems = maxItems;
	}
	
	public Integer getMaxItems() {
		return maxItems;
	}
}
