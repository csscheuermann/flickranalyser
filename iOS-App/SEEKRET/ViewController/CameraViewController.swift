//
//  CameraViewController.swift
//  SEEKRET
//
//  Created by Constantin Scheuermann on 9/21/14.
//  Copyright (c) 2014 nomis-development.net. All rights reserved.
//

import UIKit

class CameraViewController: AbstractSeekretViewController, UIImagePickerControllerDelegate, UINavigationControllerDelegate{
    
    
    @IBOutlet weak var cameraView: UIImageView!
    
    @IBOutlet weak var uiButtonView: UIView!
    @IBOutlet weak var catchTheSeekret: UIButton!
    
    @IBOutlet weak var selectAPicture: UIButton!
    
    
    override func handleSucessfullLogin(auth: GTMOAuth2Authentication) -> Void {
        //We will do nothing so far
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        
        var button   = UIButton.buttonWithType(UIButtonType.System) as UIButton
        button.frame = CGRectMake(0, 0, uiButtonView.bounds.width, 100)
        button.setTitle("CATCH SEEKRET", forState: UIControlState.Normal)
        button.addTarget(self, action: "action:", forControlEvents:UIControlEvents.TouchUpInside)
        self.uiButtonView.addSubview(button)
        
        
    }
    
    
    func catchTheSeekretButton(sender : UIButton) {
        println("Button capture2")
    }
    func catchTheSeekret(sender : UIButton) {
        println("Button capture3")
    }
    
    func action(sender : UIButton) {
        println("Button capture")
           }
}