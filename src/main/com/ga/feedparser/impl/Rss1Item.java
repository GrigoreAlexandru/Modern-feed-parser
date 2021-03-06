package com.ga.feedparser.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.ga.feedparser.FeedUtils;
import org.xml.sax.Attributes;

import com.ga.feedparser.Element;
import com.ga.feedparser.FeedType;

/**
 * Item implementation for RSS 1.0 feeds.
 */
class Rss1Item extends BaseItem {
    // XML elements for RSS items.
    private static final String TITLE = "title";
    private static final String LINK = "link";
    private static final String DESCRIPTION = "description";
    private static final String DATE = "pubDate";
    private static final String CREATOR = "creator";
    private static final String IDENTIFIER = "identifier";
    private static final String THUMBNAIL = "thumbnail";
    private static final String ENCODED = "encoded";
    
    /**
     * Constructs an Rss1Item with the specified namespace uri, name and
     * attributes.
     */
    public Rss1Item(String uri, String name, Attributes attributes) {
        super(uri, name, attributes);
    }
    
    @Override
    public FeedType getType() {
        return FeedType.RSS_1_0;
    }
    
    @Override
    public String getTitle() {
        Element title = getElement(TITLE);
        return (title != null) ? title.getContent() : null;
    }

    @Override
    public String getLink() {
        Element link = getElement(LINK);
        return (link != null) ? link.getContent() : null;
    }

    @Override
    public String getDescription() {
        Element descr = getElement(DESCRIPTION);
        return (descr != null) ? descr.getContent() : null;
    }

    @Override
    public String getAuthor() {
        // Use Dublin Core element.
        Element author = getElement(CREATOR);
        return (author != null) ? author.getContent() : null;
    }

    @Override
    public String getGuid() {
        // Use Dublin Core element.
        Element guid = getElement(IDENTIFIER);
        return (guid != null) ? guid.getContent() : null;
    }

    @Override
    public Date getPubDate() {
        // Use Dublin Core element.
        Element pubDate = getElement(DATE);
        return (pubDate != null) ? FeedUtils.convertRss1Date(pubDate.getContent()) : null;
    }

    @Override
    public List<String> getCategories() {
        return Collections.emptyList();
    }

    @Override
    public String getThumbnail() {
        if (getElement(THUMBNAIL) != null) return getElement(THUMBNAIL).getAttributes().getValue("url");
        else if (getElement("content") != null) return getElement("content").getAttributes().getValue("url");
        else if (getElement("group") != null) return getElement("group").getElement("content").getAttributes().getValue(1);
        else return null;
    }

    @Override
    public String getEncoded() {
        Element encoded = getElement(ENCODED);
        return (encoded != null) ? encoded.getContent() : null;
    }
}
