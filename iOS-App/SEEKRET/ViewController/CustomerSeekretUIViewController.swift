//
//  CustomerSeekretUIViewController.swift
//  SEEKRET
//
//  Created by Constantin Scheuermann on 9/3/14.
//  Copyright (c) 2014 nomis-development.net. All rights reserved.



import Foundation

// This class handles the complete login mechanism for all view controller within the app.
// Just inherit from this class and have fun with it :)
class CustomSeekretUIViewController: UIViewController, GPPSignInDelegate{
    
    //Login Handler handels the entire login mechanism for us. It uses closures the simulate kind
    // of abstract methods for the subclasses.
    var loginHandler: Loginhandler!
    
    var uiHelper:UIHelper!
    
    var auth: GTMOAuth2Authentication!
    
    override func viewDidLoad() {
        self.loginHandler = Loginhandler(handleSucessfullLogin)
        loginHandler.performSilentLogin(self);
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    
    func finishedWithAuth(auth: GTMOAuth2Authentication,  error: NSError? ) -> Void{
        self.auth = auth
        loginHandler.handleLogin(auth, error: error)
    }
    
    func handleSucessfullLogin(auth: GTMOAuth2Authentication) -> Void {
        fatalError("Must Override the function handleSucessfullLogin in the subclass.")
    }
    
}