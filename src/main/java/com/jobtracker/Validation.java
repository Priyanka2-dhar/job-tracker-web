package com.jobtracker;

import java.net.URI;
import java.net.URISyntaxException;

public class Validation {

    // Trims the text; returns null if it is empty
    public static String trimToNull(String s) {
        if (s == null) return null;
        s = s.trim();
        return s.isEmpty() ? null : s;
    }

    // A link is optional (null is fine), but if given it must be http(s) with a host
    public static boolean validLink(String link) {
        if (link == null) return true;
        if (link.length() > 500) return false;
        try {
            URI u = new URI(link);
            String scheme = u.getScheme();
            return ("http".equalsIgnoreCase(scheme) || "https".equalsIgnoreCase(scheme))
                    && u.getHost() != null;
        } catch (URISyntaxException e) {
            return false;
        }
    }
}