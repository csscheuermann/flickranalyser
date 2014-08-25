//
//  EndpointController.swift
//  SEEKRET-SWIFT
//
//  Created by Constantin Scheuermann on 8/25/14.
//  Copyright (c) 2014 Constantin Scheuermann. All rights reserved.
//

protocol EndpointControllerProtocol {
    func didRecieveCluster(cluster: [GTLSpotAPICluster])
}

class EnpointController{
    
    var delegate: EndpointControllerProtocol
    
    init(delegate: EndpointControllerProtocol) {
        self.delegate = delegate
    }
    
        
    
    func getCluster(clusterName: String) {
        let spotAPI = GTLServiceSpotAPI()
        spotAPI.retryEnabled = true
        
        var query:GTLQuerySpotAPI = GTLQuerySpotAPI.queryForGetNearestSpotByAddressWithSpotName(clusterName) as GTLQuerySpotAPI
        var ticket:GTLServiceTicket = GTLServiceTicket()
        var returnedSpot:GTLSpotAPISpot
        var nsError:NSError
        var cluster:[GTLSpotAPICluster] = []
        
        spotAPI.executeQuery(query, completionHandler: { (ticket, returnedSpot, nsError) -> Void in
        println(returnedSpot.clusterRadiusInKm)
            
        cluster = returnedSpot.cluster as [GTLSpotAPICluster]!;
            
            
        /*for (index, currentCluster) in enumerate(cluster){
            println(index)
            var castedCluster = currentCluster as GTLSpotAPICluster
            var castedClusterLatitude = castedCluster.latitude
            println(castedClusterLatitude)
        }*/
            // Now send the JSON result to our delegate object
        self.delegate.didRecieveCluster(cluster)
        
        })
    }
}