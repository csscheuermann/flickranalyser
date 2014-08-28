//
//  EndpointController.swift
//  SEEKRET-SWIFT
//
//  Created by Constantin Scheuermann on 8/25/14.
//  Copyright (c) 2014 Constantin Scheuermann. All rights reserved.
//

protocol EndpointControllerProtocol {
    func didRecieveCluster(cluster: [GTLSpotAPICluster], spotName: String)
}

class EnpointController{
    
    var delegate: EndpointControllerProtocol
    
    init(delegate: EndpointControllerProtocol) {
        self.delegate = delegate
    }
    
      
    func getCluster(clusterName: String, auth: GTMOAuth2Authentication) {
        let spotAPI = GTLServiceSpotAPI()
        spotAPI.retryEnabled = true
        spotAPI.authorizer = auth
        var query:GTLQuerySpotAPI = GTLQuerySpotAPI.queryForGetSeekretSpotsBySpotNameWithSpotName(clusterName) as GTLQuerySpotAPI
        var ticket:GTLServiceTicket = GTLServiceTicket()
        var returnedCuster:GTLSpotAPIClusterCollection
        var nsError:NSError
        var cluster:[GTLSpotAPICluster] = []
        
        spotAPI.executeQuery(query, completionHandler: { (ticket, returnedCuster, nsError) -> Void in
        println(returnedCuster.clusterRadiusInKm)

            
        cluster = returnedCuster.items as [GTLSpotAPICluster]!;
        
            
            
            
        /*for (index, currentCluster) in enumerate(cluster){
            println(index)
            var castedCluster = currentCluster as GTLSpotAPICluster
            var castedClusterLatitude = castedCluster.latitude
            println(castedClusterLatitude)
        }*/
            // Now send the JSON result to our delegate object
            self.delegate.didRecieveCluster(cluster, spotName: clusterName)
        
        })
    }
    
    
    

}