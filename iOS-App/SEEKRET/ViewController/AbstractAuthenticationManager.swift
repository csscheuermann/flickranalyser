//
//  AbstractAuthenticationManager.swift
//  SEEKRET
//
//  Created by Constantin Scheuermann on 9/3/14.
//  Copyright (c) 2014 nomis-development.net. All rights reserved.
//

import Foundation


//Login Handler. Important to mention is the closure pattern. It allows us to create something like abstact methods.
class AbstractAuthenticationManager: LoginProtocol{
    
    let className: String = "AbstractAuthenticationManager"
    
    func handleLogin(auth: GTMOAuth2Authentication,  error: NSError?){
        if error != nil{
        
            NSLog("\(className): Something went wrong: %@", error!)
        }else{
            NSLog("\(className): Login was sucessfull!")
            self.handleSucessfullLogin(auth)
        }
    }
    
    
    // 'Implementation' provided by subclass
    let handleSuccessfullLoginImpl: ((auth: GTMOAuth2Authentication) -> Void)
    
    // Delegates to 'implementation' provided by subclass
    func handleSucessfullLogin(auth: GTMOAuth2Authentication) -> Void {
        return handleSuccessfullLoginImpl(auth: auth)
    }
    
    init(handleSuccessfullLoginImpl: ((auth: GTMOAuth2Authentication) -> Void)) {
        self.handleSuccessfullLoginImpl = handleSuccessfullLoginImpl
    }
    
    func performSilentLogin(delegate: GPPSignInDelegate){
        var signIn = GPPSignIn.sharedInstance()
        signIn.delegate = delegate
        if (!signIn.trySilentAuthentication()) {
            NSLog("\(className): Silentlogin not logged in!")
        } else {
            NSLog("\(className): Silentlogin logged in!")
        }
    }

    
}