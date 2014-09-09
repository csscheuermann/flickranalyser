//
//  EndpointControllerforRatingProtocoll.swift
//  SEEKRET
//
//  Created by Constantin Scheuermann on 9/5/14.
//  Copyright (c) 2014 nomis-development.net. All rights reserved.

import Foundation

protocol EndpointControllerforRatingProtocoll {
    func didReceiveHasAlreadyVotedOrDismissed(responseCode: NSNumber, entity: Bool)
}

class EndpointControllerforRatingAPI{
    var delegate: EndpointControllerforRatingProtocoll
    
    init(delegate: EndpointControllerforRatingProtocoll) {
        self.delegate = delegate
    }
    
    func hasAlreadyVoted(auth: GTMOAuth2Authentication, clusterId: String){
        let ratingAPI = GTLServiceRatingAPI()
        ratingAPI.retryEnabled = true
        ratingAPI.authorizer = auth
        
        var query:GTLQueryRatingAPI = GTLQueryRatingAPI.queryForHasAlreadyDismissedOrVotedWithClusterPrimaryKey(clusterId) as GTLQueryRatingAPI
        var ticket:GTLServiceTicket = GTLServiceTicket()
        var returnedResponse:GTLRatingAPIResponse
        var nsError:NSError
        
        ratingAPI.executeQuery(query, completionHandler: { (ticket, returnedResponse, nsError) -> Void in
            
            var responseCode: NSNumber = returnedResponse.status
            var entityBool: Bool = returnedResponse.entity as Bool
            self.delegate.didReceiveHasAlreadyVotedOrDismissed(responseCode, entity: entityBool)
            
        })
    }
}
