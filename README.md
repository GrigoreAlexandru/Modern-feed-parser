# Modern-feed-parser


[![Build Status](https://travis-ci.org/GrigoreAlexandru/Modern-feed-parser.svg?branch=master)](https://travis-ci.org/GrigoreAlexandru/Modern-feed-parser)

Supported specs:

* Rss 2.*
* Rss 1.*
* Atom 1.*

Upgraded version of [simple-feed-parser](https://github.com/ernieyu/simple-feed-parser) with support for websub (pubsubhubbub), cloud, logo, thumbnail and encoded HTML.

**Sample Usage**

```
        try {
            URL url = new URL("https://superfeedr-blog-feed.herokuapp.com/");
            HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
            if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream feedStream = httpConnection.getInputStream();
                FeedParser parser = FeedParserFactory.newParser();
                Feed feed = parser.parse(feedStream);
                System.out.println(feed.getLogo());
                System.out.println(feed.getWebSub());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FeedException e) {
            e.printStackTrace();
        }
```

**Get started**

[JAR](https://github.com/GrigoreAlexandru/Modern-feed-parser/releases/download/1.0.0/Modern-feed-parser-1.0.0.jar)
