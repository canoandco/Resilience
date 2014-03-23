package com.resilience

import com.resilience.security.User
import grails.transaction.Transactional

@Transactional
class MediaService {

    Media addMedia(MediaType mediaType,String mediaName,String mediaDescription, String mediaUrl, User provider) {
        Media media = new Media(
                mediaName: mediaName,
                mediaDescription:mediaDescription,
                mediaLink: mediaUrl,
                mediaType: mediaType,
                provider : provider
        )

        media.save(failOnError: true,flush:true)
        return media
    }



}
