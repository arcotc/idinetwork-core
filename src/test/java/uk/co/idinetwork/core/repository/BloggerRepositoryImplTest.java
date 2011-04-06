package uk.co.idinetwork.core.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import uk.co.idinetwork.core.model.Article;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.gdata.client.GoogleService;
import com.google.gdata.data.Entry;
import com.google.gdata.data.Feed;
import com.google.gdata.util.ServiceException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring/applicationContext-test.xml" })
public class BloggerRepositoryImplTest {
	@Autowired BloggerFeedRepository bloggerFeedRepository;
	@Autowired BloggerRepository bloggerRepository;
	
    private final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

//    @Before
    public void setUp() {
        helper.setUp();
		bloggerFeedRepository.saveBloggerFeed("IDI Network", "http://idinetwork.blogspot.com/feeds/posts/default");
    }

//    @After
    public void tearDown() {
        helper.tearDown();
    }
	
	@Test
	@Ignore
	public void testLoadUserBloggs() throws IOException, ServiceException {
		GoogleService myService = new GoogleService("blogger", "continuing-to-learning");
		Collection<Article> actual = bloggerRepository.loadUserBlogs(myService);
		
		assertNotNull(actual);
	}

	@Test
	@Ignore
	public void testGetBlogId() throws IOException, ServiceException {
		GoogleService myService = new GoogleService("blogger", "continuing-to-learning");
		try {
			// Request the feed
			final URL feedUrl = new URL("http://continuing-to-learning.blogspot.com/feeds/posts/default");
			Feed resultFeed = myService.getFeed(feedUrl, Feed.class);
	
			// Print the results
			for (int i = 0; i < resultFeed.getEntries().size(); i++) {
				Entry entry = resultFeed.getEntries().get(i);
				
				assertNotNull(entry.getId().substring(entry.getId().indexOf("blog-") + 5, entry.getId().indexOf(".post")));
			}
		}
		catch (ServiceException serviceException) {
			assertFalse(true);
		}
		catch (IOException ioException) {
			assertFalse(true);
		}
	}
}
