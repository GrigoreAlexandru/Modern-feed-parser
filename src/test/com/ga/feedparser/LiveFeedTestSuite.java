package com.ga.feedparser;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test case using live feeds.
 */
public class LiveFeedTestSuite {
    private final static String BBC_WORLD_NEWS = "http://feeds.bbci.co.uk/news/world/rss.xml";
    private final static String NYT_WORLD_NEWS = "http://feeds.nytimes.com/nyt/rss/World";

    
    private final static Logger LOG = Logger.getLogger(LiveFeedTestSuite.class.getName());
    
    private FeedParser feedParser;

    @Before
    public void setUp() {
        feedParser = FeedParserFactory.newParser();
    }

    @After
    public void tearDown() {
        feedParser = null;
    }

    /** Test using BBC world news RSS feed. */
    @Test
    public void testBBCNewsRss() {
        try {
            // Open input stream for test feed.
            URL url = new URL(BBC_WORLD_NEWS);
            InputStream inStream = url.openConnection().getInputStream();
            
            // Parse feed.
            Feed feed = feedParser.parse(inStream);

            assertEquals("feed name", "rss", feed.getName());
            assertEquals("feed type", FeedType.RSS_2_0, feed.getType());
            assertEquals("feed title", "BBC News - World", feed.getTitle());

            List<Item> itemList = feed.getItemList();
            LOG.log(Level.INFO, "item count = " + itemList.size());
            assertTrue("item count", itemList.size() > 0);
            
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }

    /** Test using NY Times world news RSS feed. */
    @Test
    public void testNYTimesRss() {
        try {
            // Open input stream for test feed.
            URL url = new URL(NYT_WORLD_NEWS);
            InputStream inStream = url.openConnection().getInputStream();
            
            // Parse feed.
            Feed feed = feedParser.parse(inStream);

            assertEquals("feed name", "rss", feed.getName());
            assertEquals("feed type", FeedType.RSS_2_0, feed.getType());
            assertEquals("feed title", "NYT > World", feed.getTitle());

            List<Item> itemList = feed.getItemList();
            LOG.log(Level.INFO, "item count = " + itemList.size());
            assertTrue("item count", itemList.size() > 0);
            
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }

}
