//
//  EndpointControllerForRating.swift
//  SEEKRET
//
//  Created by Constantin Scheuermann on 9/2/14.
//  Copyright (c) 2014 nomis-development.net. All rights reserved.
//

import Foundation
protocol EndPointControllerForClusterProtocoll {
    func didVote(responseCode: NSNumber)
}

class EndPointControllerForRating{
    var delegate: EndPointControllerForRatingProtocoll
    
    init(delegate: EndPointControllerForRatingProtocoll) {
        self.delegate = delegate
    }
    
    
    
    
    func voteForCluster(auth: GTMOAuth2Authentication, clusterId: String){
        
        
        
        let ratingAPI = GTLServiceRatingAPI()
        
        
        ratingAPI.retryEnabled = true
        ratingAPI.authorizer = auth
        var query:GTLQueryRatingAPI = GTLQueryRatingAPI.queryForAddNewRatingWithUserPrimaryKey(auth.userEmail, clusterId) as GTLQueryRatingAPI
        
        var ticket:GTLServiceTicket = GTLServiceTicket()
        
        var returnedResponse:GTLRatingAPIResponse
        var nsError:NSError

        
        spotAPI.executeQuery(query, completionHandler: { (ticket, returnedSpot, nsError) -> Void in
            var responseCode = returnedResponse.status
            
            
            
            self.delegate.didVote(responseCode)
            
        })
    }
    
        
        
}
