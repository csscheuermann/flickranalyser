//  MainViewController.swift
//  SEEKRET
//
//  Created by Constantin Scheuermann on 8/27/14.
//  Copyright (c) 2014 nomis-development.net. All rights reserved.

import UIKit


class MainViewController: CustomSeekretUIViewController, EndPointControllerForTopSpotsProtocoll, UITableViewDelegate, UITableViewDataSource {
    
    @IBOutlet weak var topSpotsTableView: UITableView!
    
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

    
    func refreshTopSpots(){
        getTopSpots()
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    
    
    override func handleSucessfullLogin(auth: GTMOAuth2Authentication) -> Void {
        self.uiHelper = UIHelper(uiView: self.view)
        uiHelper.showSpinner(self.showSpinnerText)
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
        uiHelper.stopSpinner()
        refreshControl.endRefreshing()
    }
    
    func tableView(tableView: UITableView!, numberOfRowsInSection section: Int) -> Int {
        return self.topSpots.count
    }
    
    func tableView(tableView: UITableView!, cellForRowAtIndexPath indexPath: NSIndexPath!) -> UITableViewCell! {
        let cell: SpotNameCellView = topSpotsTableView.dequeueReusableCellWithIdentifier(self.cellIdentifierforSpot) as SpotNameCellView
        cell.setCell(topSpots[indexPath.row])
        return cell
    }
    
    override func prepareForSegue(segue: UIStoryboardSegue!, sender: AnyObject!) {
        
        if (segue.identifier == self.segueIdentifier){
            var indexPath: NSIndexPath = self.topSpotsTableView.indexPathForSelectedRow()
            var spotViewController:SpotViewController = segue.destinationViewController as SpotViewController
            spotViewController.spotName = topSpots[indexPath.row]
        }
    }
}

