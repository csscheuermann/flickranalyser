//
//  LoginProtocoll.swift
//  SEEKRET
//
//  Created by Constantin Scheuermann on 9/3/14.
//  Copyright (c) 2014 nomis-development.net. All rights reserved.
//

import Foundation

// The protocoll for the Login. Its like an Interface in Java. 

protocol LoginProtocol{
    
    func handleLogin(auth: GTMOAuth2Authentication,  error: NSError?)
    
    func handleSucessfullLogin(auth: GTMOAuth2Authentication) -> Void
        
}