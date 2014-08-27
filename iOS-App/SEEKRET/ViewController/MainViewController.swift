//
//  MainViewController.swift
//  SEEKRET
//
//  Created by Constantin Scheuermann on 8/27/14.
//  Copyright (c) 2014 nomis-development.net. All rights reserved.
//

import UIKit


class MainViewController: UIViewController{
    
   
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        var signIn = GPPSignIn.sharedInstance()
        if (!signIn.trySilentAuthentication()) {
            print("TEST")
        } else {
           print("TEST2")
            var test = signIn.userID
           print(test)
        }
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    
}