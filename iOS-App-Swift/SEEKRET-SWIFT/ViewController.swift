//
//  ViewController.swift
//  SEEKRET-SWIFT
//
//  Created by Constantin Scheuermann on 8/25/14.
//  Copyright (c) 2014 Constantin Scheuermann. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
                            
    override func viewDidLoad() {
        super.viewDidLoad()
        
        let spotAPI = GTLServiceSpotAPI()
       
        
        spotAPI.retryEnabled = true
        
        var query:GTLQuerySpotAPI = GTLQuerySpotAPI.queryForGetNearestSpotByAddressWithSpotName("BERLIN") as GTLQuerySpotAPI
        var ticket:GTLServiceTicket = GTLServiceTicket()
        var returnedSpot:GTLSpotAPISpot
        var nsError:NSError
    
        
        spotAPI.executeQuery(query, completionHandler: { (ticket, returnedSpot, nsError) -> Void in
            println(returnedSpot.clusterRadiusInKm)
            
            var cluster:[AnyObject] = returnedSpot.cluster as [AnyObject]!;
            
            var urls:[AnyObject] =  cluster.first?.urlOfMostViewedPicture as [AnyObject]!;
            
            for currentItem in urls{
                var url:String = currentItem as String
                println(url)
            }
            
           
            
        })
        
        
        
    
        
        
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

