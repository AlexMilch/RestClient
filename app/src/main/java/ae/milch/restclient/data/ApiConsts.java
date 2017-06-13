package ae.milch.restclient.data;

public class ApiConsts {
    public static final String API_KEY = "aa919fc7727d423b93df445c208635d6";
    public static final String ENDPOINT = "https://newsapi.org";
    private static final String VERSION = "/v1";

    static final class Api {
        static final String ARTICLES = VERSION + "/articles";
        static final String SOURCES = VERSION + "/sources";
    }

    static final class Fields {
        static final String SOURCE = "source";
        static final String API_KEY = "apiKey";
        static final String LANGUAGE = "language";
    }
}
