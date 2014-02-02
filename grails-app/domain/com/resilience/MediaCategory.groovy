package com.resilience

class MediaCategory {

    String mediaCategoryDesc

    static hasMany = ["medias":Media]

    static constraints = {
    }
}
