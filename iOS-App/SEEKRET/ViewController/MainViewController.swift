//  MainViewController.swift
//  SEEKRET
//
//  Created by Constantin Scheuermann on 8/27/14.
//  Copyright (c) 2014 nomis-development.net. All rights reserved.
//

import UIKit


class MainViewController: UIViewController,EndPointControllerForTopSpotsProtocoll, GPPSignInDelegate, UITableViewDelegate, UITableViewDataSource {
    
    @IBOutlet weak var topSpotsTableView: UITableView!
    var uiHelper:UIHelper!
    var topSpots: [String] = []
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.topSpotsTableView.delegate = self
        self.topSpotsTableView.dataSource = self
        topSpots = ["Getting Top Spots ..."]
        performSilentLogin();
    }
    
    /// We have to do the Login here, because it is only possible in subclasses from UIViewController.
    /// Sad but true ...
    func performSilentLogin(){
        //Silent Sign In
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
    
    func finishedWithAuth(auth: GTMOAuth2Authentication,  error: NSError? ) -> Void{
        if error != nil{
            debugPrintln("AUTH WENT WRONG")
        }else{
            debugPrintln("FINISHED WITH AUTH")
            self.uiHelper = UIHelper(uiView: self.view)
            uiHelper.showSpinner("Fetching TopList")
            
            let endpointControllerForTopSpots = EndPointControllerForTopSpots(delegate: self);
            endpointControllerForTopSpots.getTopSpots(auth)
        }
    }
    
    func didRecieveTopSpots(topSpots: [String]){
        self.topSpots = topSpots
        self.topSpotsTableView.reloadData()
         uiHelper.stopSpinner()
    }
    
    func tableView(tableView: UITableView!, numberOfRowsInSection section: Int) -> Int {
        return self.topSpots.count
    }
    
    func tableView(tableView: UITableView!, cellForRowAtIndexPath indexPath: NSIndexPath!) -> UITableViewCell! {
        let cell: SpotNameCellView = topSpotsTableView.dequeueReusableCellWithIdentifier("SpotNameCellIdent") as SpotNameCellView
        cell.setCell(topSpots[indexPath.row])
        return cell
    }
    
    func tableView(tableView: UITableView!, didSelectRowAtIndexPath indexPath: NSIndexPath!) {
        let spotName = topSpots[indexPath.row]
        var mapViewController:MapViewController = self.storyboard.instantiateViewControllerWithIdentifier("MapViewController") as MapViewController
        mapViewController.spotName = spotName
      self.presentViewController(mapViewController, animated: true, completion: nil)
    
        
    }
    
    
    
}