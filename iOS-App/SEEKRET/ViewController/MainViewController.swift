//
//  MainViewController.swift
//  SEEKRET
//
//  Created by Constantin Scheuermann on 8/27/14.
//  Copyright (c) 2014 nomis-development.net. All rights reserved.
//

import UIKit


class MainViewController: UIViewController, GPPSignInDelegate {
    
   
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        var signIn = GPPSignIn.sharedInstance()
        signIn.delegate = self
        if (!signIn.trySilentAuthentication()) {
            println("NOT LOGGED IN")
        } else {
          println("LOGGED IN")
        }
        
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }

    func finishedWithAuth(auth: GTMOAuth2Authentication,  error: NSError ) -> Void{
        debugPrintln("TEST")
        
        let spotAPI = GTLServiceSpotAPI()
        spotAPI.retryEnabled = true
        spotAPI.authorizer = auth
        var query:GTLQuerySpotAPI = GTLQuerySpotAPI.queryForGetTopSpots() as GTLQuerySpotAPI
        var ticket:GTLServiceTicket = GTLServiceTicket()
        var returnedSpot:GTLSpotAPISpotResultList
        var nsError:NSError
        var cluster:[GTLSpotAPICluster] = []
        
        spotAPI.executeQuery(query, completionHandler: { (ticket, returnedSpot, nsError) -> Void in
            var array:NSArray = returnedSpot.topSpots
            println(array[0])

        })
    }
    
    
}