//
//  EndPointControllerForTopSpots.swift
//  SEEKRET
//
//  Created by Constantin Scheuermann on 8/28/14.
//  Copyright (c) 2014 nomis-development.net. All rights reserved.
//

import Foundation

protocol EndPointControllerForTopSpotsProtocoll {
    func didRecieveTopSpots(topSpots: [String])
}

class EndPointControllerForTopSpots{
    var delegate: EndPointControllerForTopSpotsProtocoll
    
    init(delegate: EndPointControllerForTopSpotsProtocoll) {
        self.delegate = delegate
    }
    
    func getTopSpots(auth: GTMOAuth2Authentication){
        
        let spotAPI = GTLServiceSpotAPI()
        spotAPI.retryEnabled = true
        spotAPI.authorizer = auth
        var query:GTLQuerySpotAPI = GTLQuerySpotAPI.queryForGetTopSpots() as GTLQuerySpotAPI
        var ticket:GTLServiceTicket = GTLServiceTicket()
        var returnedSpot:GTLSpotAPISpotResultList
        var nsError:NSError
        var cluster:[GTLSpotAPICluster] = []
        
        spotAPI.executeQuery(query, completionHandler: { (ticket, returnedSpot, nsError) -> Void in
            if (nsError != nil) {
                NSLog("SOMETHING DURING GET TOP SPOTS WENT WRONG %@", nsError)
            }else{
                NSLog("UHH JEAH I GOT THE TOP SPOTS BABY...")
                var resultArray: [String] = returnedSpot.topSpots as [String]
                self.delegate.didRecieveTopSpots(resultArray)
            }
        })
    }
    
}