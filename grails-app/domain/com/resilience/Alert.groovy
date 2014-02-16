package com.resilience

class Alert {

    String description
    Long latitude
    Long longitude

    static belongsTo = [user:Alert]

    static constraints = {
    }
}
