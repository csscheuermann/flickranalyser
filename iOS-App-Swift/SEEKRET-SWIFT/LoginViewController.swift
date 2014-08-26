//
//  LoginController.swift
//  SEEKRET-SWIFT
//
//  Created by Constantin Scheuermann on 8/26/14.
//  Copyright (c) 2014 Constantin Scheuermann. All rights reserved.
//


import UIKit


class LoginViewController: UIViewController, GPPSignInDelegate {
    var kClientID = "449444009918-25mnitq6rqvgnuj0kvrg9qgm1ms2v4gu.apps.googleusercontent.com"
    
    @IBOutlet weak var loginButton: UIButton!
    @IBOutlet var gppsloginButton: GPPSignInButton!
    var signIn = GPPSignIn.sharedInstance()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // self.alreadyConenctedLabel.hidden = YES;
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