package com.resilience.errors

import com.resilience.tools.ToolboxService

class ErrorController {

    private def formatError(int status, int code, String message,String detailedMessage) {
        render(status: status, contentType: "text/json") {
            [
                    errorCode: code,
                    errorMessage: message,
                    detailedMessage: detailedMessage,
                    timestamp: ToolboxService.dateToISO8601(new Date())
            ]
        }
    }

    def missingParameter() {
        formatError(400, 1,"Paramètre(s) manquant(s)", "Il manque un ou plusieurs paramètres dans votre requête.")
    }

    def userExists() {
        formatError(400, 2,"Utilisateur existant", "Un utilisateur portant le même nom d'utilisateur existe déjà.")
    }

    def userNotFound() {
        formatError(404, 3,"Utilisateur introuvable", "L'utilisateur n'a pas pu être trouvé.")
    }

    def categoryNotFound() {
        formatError(404, 4,"Categorie introuvable", "La catégorie n'a pas pu être trouvée.")
    }

    def alreadySubscribed() {
        formatError(400, 5,"Catégorie déjà souscrite", "L'utilisateur est déjà abonné à cette catégorie.")
    }


}
