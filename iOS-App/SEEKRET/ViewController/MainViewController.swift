//  MainViewController.swift
//  SEEKRET
//
//  Created by Constantin Scheuermann on 8/27/14.
//  Copyright (c) 2014 nomis-development.net. All rights reserved.

import UIKit


class MainViewController: CustomSeekretUIViewController, EndPointControllerForTopSpotsProtocoll, UITableViewDelegate, UITableViewDataSource, UIAlertViewDelegate {
    
    @IBOutlet weak var topSpotsTableView: UITableView!
    @IBOutlet weak var logoutButton: UIBarButtonItem!
    
    var topSpots: [String] = []
    var refreshControl:UIRefreshControl!

    
    //Constants
    let showSpinnerText: String = "Fetching TopList"
    let cellIdentifierforSpot: String = "SpotNameCellIdent"
    let segueIdentifier: String = "showClusterView"
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.automaticallyAdjustsScrollViewInsets = false;
        self.topSpotsTableView.delegate = self
        self.topSpotsTableView.dataSource = self
        self.updateLocation()
        self.attachUIRefresh()
    }
    
    
    func attachUIRefresh(){
        var refreshControl:UIRefreshControl = UIRefreshControl()
        refreshControl.tintColor = UIColor.orangeColor();
        refreshControl.addTarget(self, action: "refreshTopSpots", forControlEvents: UIControlEvents.ValueChanged)
        
        var refreshTitle: NSAttributedString = NSAttributedString(string: "Pull to Refersh List")
        refreshControl.attributedTitle = refreshTitle
        self.topSpotsTableView.addSubview(refreshControl)
        self.refreshControl = refreshControl
    }

    
    @IBAction func logoutButtonTouched(sender: AnyObject) {
        if (sender as UIBarButtonItem == logoutButton) {
            var alert: UIAlertView = UIAlertView(title: "Logout", message: "Are you sure you want to logout for SEEKRET?", delegate: self, cancelButtonTitle: "YES",  otherButtonTitles: "Cancel")
            alert.show();
        }
    }
    
    func alertView(alertView: UIAlertView!, clickedButtonAtIndex buttonIndex: Int){
        if (buttonIndex == 0){
            GPPSignIn.sharedInstance() .disconnect()
            var hud = MBProgressHUD .showHUDAddedTo(self.view, animated: true)
            hud.labelText = "Logging out from SEEKRET ..."
            hud.hide(true, afterDelay: 2.0)
            var timer = NSTimer.scheduledTimerWithTimeInterval(2.0, target: self, selector: Selector("showLoginScreen"), userInfo: nil, repeats: false)
        }else{
            NSLog("Will no do anything")
        }
    }
    

    
    func showLoginScreen(){
        self.dismissViewControllerAnimated(true, completion: nil)
    }
    
    func refreshTopSpots(){
        getTopSpots()
    }
    

    
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    
    
    override func handleSucessfullLogin(auth: GTMOAuth2Authentication) -> Void {
        self.uiHelper = MBProgressHUD .showHUDAddedTo(self.view, animated: true);
        uiHelper.labelText = self.showSpinnerText;
        getTopSpots()
    }
    
    func getTopSpots(){
        let endpointControllerForTopSpots = EndPointControllerForTopSpots(delegate: self);
        endpointControllerForTopSpots.getTopSpots(self.auth)
    }
    
    func didRecieveTopSpots(topSpots: [String]){
        debugPrintln("RECEIVED TOP SPOTS. LENGTH: \(topSpots.count)")
        self.topSpots = topSpots
        self.topSpotsTableView.reloadData()
        uiHelper.hide(true);
        refreshControl.endRefreshing()
    }
    
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.topSpots.count
    }
    
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        let cell: SpotNameCellView = topSpotsTableView.dequeueReusableCellWithIdentifier(self.cellIdentifierforSpot) as SpotNameCellView
        cell.setCell(topSpots[indexPath.row])
        return cell
    }
    
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject!) {
        
        if (segue.identifier == self.segueIdentifier){
            var indexPath: NSIndexPath = self.topSpotsTableView.indexPathForSelectedRow()!
            var spotViewController:SpotViewController = segue.destinationViewController as SpotViewController
            spotViewController.spotName = topSpots[indexPath.row]
        }
    }
}

