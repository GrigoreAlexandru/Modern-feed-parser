package com.ga.feedparser.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ga.feedparser.Element;
import com.ga.feedparser.FeedType;
import com.ga.feedparser.FeedUtils;
import org.xml.sax.Attributes;

/**
 * Item implementation for Atom feeds.
 */
class AtomItem extends BaseItem {
    // XML elements for Atom items.
    private static final String TITLE = "title";
    private static final String LINK = "link";
    private static final String UPDATED = "updated";
    private static final String ID = "id";
    private static final String CONTENT = "content";
    private static final String SUMMARY = "summary";
    private static final String AUTHOR = "author";
    private static final String NAME = "name";
    private static final String CATEGORY = "category";
    private static final String THUMBNAIL = "thumbnail";
    private static final String ENCODED = "encoded";

    /**
     * Constructs an AtomItem with the specified namespace uri, name and 
     * attributes.
     */
    public AtomItem(String uri, String name, Attributes attributes) {
        super(uri, name, attributes);
    }
    
    @Override
    public FeedType getType() {
        return FeedType.ATOM_1_0;
    }
    
    @Override
    public String getTitle() {
        Element title = getElement(TITLE);
        return (title != null) ? title.getContent() : null;
    }

    @Override
    public String getLink() {
        Element link = getElement(LINK);
        return (link != null) ? link.getAttributes().getValue("href") : null;
    }

    @Override
    public String getDescription() {
        Element descr = getElement(CONTENT);
        if (descr == null) descr = getElement(SUMMARY);
        return (descr != null) ? descr.getContent() : null;
    }

    @Override
    public String getAuthor() {
        Element author = getElement(AUTHOR);
        if (author != null) {
            Element name = author.getElement(NAME);
            return (name != null) ? name.getContent() : null;
        }
        return null;
    }

    @Override
    public String getGuid() {
        Element guid = getElement(ID);
        return (guid != null) ? guid.getContent() : null;
    }

    @Override
    public Date getPubDate() {
        Element pubDate = getElement(UPDATED);
        return (pubDate != null) ? FeedUtils.convertAtomDate(pubDate.getContent()) : null;
    }

    @Override
    public List<String> getCategories() {
        List<Element> elementList = getElementList(CATEGORY);
        
        // Create list of category terms.
        List<String> categories = new ArrayList<String>();
        for (Element element : elementList) {
            categories.add(element.getAttributes().getValue("term"));
        }

        return categories;
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
