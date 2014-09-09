//
//  EndpointControllerForRating.swift
//  SEEKRET
//
//  Created by Constantin Scheuermann on 9/2/14.
//  Copyright (c) 2014 nomis-development.net. All rights reserved.

import Foundation

protocol EndPointControllerForClusterProtocoll {
    func didDismiss(responseCode: NSNumber, entity: String)
    func didEvaluation(responseCode: NSNumber, entity: String)
}

class EndPointControllerForCluster{
    var delegate: EndPointControllerForClusterProtocoll
    
    init(delegate: EndPointControllerForClusterProtocoll) {
        self.delegate = delegate
    }
    
    func dismissCluster(auth: GTMOAuth2Authentication, clusterId: String){

        let clusterAPI = GTLServiceClusterAPI()
        
        clusterAPI.retryEnabled = true
        clusterAPI.authorizer = auth

        var query:GTLQueryClusterAPI = GTLQueryClusterAPI.queryForIncrementDismissCountWithDatastoreKeyOfCluster(clusterId) as GTLQueryClusterAPI
        var ticket:GTLServiceTicket = GTLServiceTicket()
        var returnedResponse:GTLClusterAPIResponse
        var nsError:NSError

        clusterAPI.executeQuery(query, completionHandler: { (ticket, returnedResponse, nsError) -> Void in
            var responseCode: NSNumber = returnedResponse.status
            self.delegate.didDismiss(responseCode, entity: String(returnedResponse.entity as NSString))
        })
    }
    
    
    func evaluateCluster(auth: GTMOAuth2Authentication, touristicness: Int, datastoreClusterKey: String, spotName: String){
        
        let clusterAPI = GTLServiceClusterAPI()
        
        clusterAPI.retryEnabled = true
        clusterAPI.authorizer = auth
        
        var query:GTLQueryClusterAPI = GTLQueryClusterAPI.queryForEvaluateClusterWithDatastoreKeyOfCluster(datastoreClusterKey, touristicnessRatingFrom1To10: touristicness, spotName: spotName) as GTLQueryClusterAPI
        var ticket:GTLServiceTicket = GTLServiceTicket()
        var returnedResponse:GTLClusterAPIResponse
        var nsError:NSError
        
        clusterAPI.executeQuery(query, completionHandler: { (ticket, returnedResponse, nsError) -> Void in
            var responseCode: NSNumber = returnedResponse.status
            var response = String(returnedResponse.entity as NSString)
            NSLog("RECEIVED MESSAGE %@", response)
            self.delegate.didEvaluation(responseCode, entity: response)
        })
    }

    
  
        
        
}
