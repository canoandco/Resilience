package com.resilience

import com.resilience.security.User

class Media {

    MediaType mediaType
    User mediaProvider
    String mediaLink
    Date dateTimeBegin
    Date dateTimeEnd

    static constraints = {
    }
}
