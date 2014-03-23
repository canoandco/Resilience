package com.resilience.tools

import com.resilience.Media
import com.resilience.MediaCategory
import com.resilience.MediaType
import com.resilience.security.User
import grails.converters.JSON
import grails.converters.XML

import java.text.SimpleDateFormat

/**
 * Created by cano on 10/02/14.
 */
class Converter {

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



    static void registerMarshallers() {
        JSON.registerObjectMarshaller(MediaCategory) {
            def returnSet = [:]

            returnSet.id = it.id
            returnSet.mediaCategoryDesc = it.mediaCategoryDesc
            returnSet.medias = it.medias

            return returnSet
        }

        XML.registerObjectMarshaller(MediaCategory) {
            def returnSet = [:]

            returnSet.id = it.id
            returnSet.mediaCategoryDesc = it.mediaCategoryDesc
            returnSet.medias = it.medias

            return returnSet
        }

        JSON.registerObjectMarshaller(Media) {
            def returnSet = [:]

            returnSet.id = it.id
            returnSet.provider = it.provider
            returnSet.mediaType = it.mediaType
            returnSet.mediaLink = it.mediaLink
            returnSet.mediaName = it.mediaName
            returnSet.mediaDescription = it.mediaDescription
            return returnSet
        }

        XML.registerObjectMarshaller(Media) {
            def returnSet = [:]

            returnSet.id = it.id
            returnSet.provider = it.provider
            returnSet.mediaType = it.mediaType
            returnSet.mediaLink = it.mediaLink
            returnSet.mediaName = it.mediaName
            returnSet.mediaDescription = it.mediaDescription
            return returnSet
        }


        JSON.registerObjectMarshaller(MediaType) {
            def returnSet = [:]

            returnSet.id = it.id
            returnSet.mediaTypeName = it.mediaTypeName
            returnSet.mediaTypeDesc = it.mediaTypeDesc

            return returnSet
        }

        XML.registerObjectMarshaller(MediaType) {
            def returnSet = [:]

            returnSet.id = it.id
            returnSet.mediaTypeName = it.mediaTypeName
            returnSet.mediaTypeDesc = it.mediaTypeDesc

            return returnSet
        }

        JSON.registerObjectMarshaller(User) {
            def returnSet = [:]

            returnSet.id = it.id
            returnSet.username = it.username


            return returnSet
        }

        XML.registerObjectMarshaller(User) {
            def returnSet = [:]

            returnSet.id = it.id
            returnSet.username = it.username


            return returnSet
        }




    }



}
