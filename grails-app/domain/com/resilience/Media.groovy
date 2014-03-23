package com.resilience

import com.resilience.security.User

class Media {

    MediaType mediaType
    String mediaLink
    String mediaName
    String mediaDescription

    static belongsTo = [provider:User]

    static constraints = {
    }
}
