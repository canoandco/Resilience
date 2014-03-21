package com.resilience

class MediaCategory {

    String mediaCategoryName
    String mediaCategoryDesc

    static hasMany = [medias:Media]

    static constraints = {
    }
}
