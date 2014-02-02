package com.resilience.tools

import grails.transaction.Transactional

import java.text.SimpleDateFormat

@Transactional
class ToolboxService {

    /**
     * Transforms a Date to an ISO 8601 String
     * @param date Date to transform
     * @return The transformed Date into a String
     */
    static String dateToISO8601(Date date) {
        String formatted = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .format(date.getTime());
        return formatted.substring(0, 22) + ":" + formatted.substring(22);
    }
}
