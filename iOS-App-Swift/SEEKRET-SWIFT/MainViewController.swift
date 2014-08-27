//
//  MainViewController.swift
//  SEEKRET-SWIFT
//
//  Created by Constantin Scheuermann on 8/27/14.
//  Copyright (c) 2014 Constantin Scheuermann. All rights reserved.
//

import UIKit
import MobileCoreServices

    
    class MainViewController: UIViewController, GPPSignInDelegate {
        
        
        var kClientID = "1099379908084-s2b7ltsscjubgnofctrbe5ru1qtpspgb.apps.googleusercontent.com"
        
        @IBOutlet weak var loginButton: UIButton!
        @IBOutlet var gppsloginButton: GPPSignInButton!
        var signIn = GPPSignIn.sharedInstance()
        
        override func viewDidLoad() {
            super.viewDidLoad()
            //var signIn = GPPSignIn.sharedInstance()
            signIn.clientID = kClientID;
            signIn.scopes = [kGTLAuthScopePlusLogin]
            
            signIn.delegate = self
            signIn.authenticate()
        }
        
        override func didReceiveMemoryWarning() {
            super.didReceiveMemoryWarning()
            // Dispose of any resources that can be recreated.
        }
        
        
        func finishedWithAuth(auth: GTMOAuth2Authentication,  error: NSError ) -> Void{
            debugPrintln("TEST")
        }
        
        func didDisconnectWithError ( error: NSError) -> Void{
            debugPrintln("TEST2")
        }
    
    
}