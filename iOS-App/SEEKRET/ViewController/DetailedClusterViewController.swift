//
//  DetailedClusterViewController.swift
//  SEEKRET
//
//  Created by Constantin Scheuermann on 9/2/14.
//  Copyright (c) 2014 nomis-development.net. All rights reserved.
//

import UIKit
import MapKit

class DetailedClusterViewController: UIViewController, EndPointControllerForClusterProtocoll, UITabBarDelegate, UIAlertViewDelegate{
    
    
    @IBOutlet weak var latLongPair: UILabel!
    @IBOutlet weak var tabBarForVoting: UITabBar!
    var cluster: GTLSpotAPICluster!
    var uIHelper: UIHelper!
    var auth: GTMOAuth2Authentication!
    
    @IBOutlet weak var votes: UILabel!
    @IBOutlet weak var touristicness: UILabel!
    @IBOutlet weak var overallViews: UILabel!
    @IBOutlet weak var numberOfPOIs: UILabel!
    
    @IBOutlet weak var mapView: MKMapView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.uIHelper = UIHelper(uiView: self.view)
        self.navigationItem.title = cluster.name
        self.numberOfPOIs.text = self.getNumberOfPOIs()
        votes.text = cluster.overallTouristicnessVotes.stringValue
        touristicness.text = cluster.overallTouristicnessInPointsFrom1To10.stringValue
        overallViews.text = cluster.overallViews.stringValue
        latLongPair.text = "\(cluster.latitude) / \(cluster.longitude)"
        
        self.tabBarForVoting.delegate = self
        self.addPointToMapView()
        
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func tabBar(tabBar: UITabBar!, didSelectItem item: UITabBarItem!) {
        
        var itemsOfTabBar = tabBar.selectedItem
        for (index, currentItem) in enumerate(tabBar.items as [UITabBarItem]){
            
            if (currentItem == item){
                debugPrintln("Current Item is \(index)")
                
                if (index == 0){
                    showAreYouSureDialogBox()
                }else{
                    //DO OTHER STUFF
                }
            }
        }
        
    }
    
    
    
    func getNumberOfPOIs() -> NSString{
        var numberAsString: NSString = cluster.numberOfPOIs.stringValue
        return numberAsString
    }
    
    func addPointToMapView(){
       
        var currentClusterAnnotation = MKPointAnnotation()
        
        var latitude:CLLocationDegrees = cluster.latitude
        var longitude:CLLocationDegrees = cluster.longitude
        
        var currentClusterLocation:CLLocationCoordinate2D = CLLocationCoordinate2DMake(latitude, longitude)
        currentClusterAnnotation.coordinate = currentClusterLocation
        
        currentClusterAnnotation.title = "TEST"
        currentClusterAnnotation.subtitle = "TEST"
        
        var latDelta:CLLocationDegrees = 0.01
        var longDelta:CLLocationDegrees = 0.01
        var theSpan:MKCoordinateSpan = MKCoordinateSpanMake(latDelta, longDelta)
        var theRegion:MKCoordinateRegion = MKCoordinateRegionMake(currentClusterLocation, theSpan)
        self.mapView.setRegion(theRegion, animated: true)
        self.mapView.addAnnotation(currentClusterAnnotation)
        
        
    }
    func showAreYouSureDialogBox(){
        
        var alert: UIAlertView = UIAlertView(title: "Dismiss Place", message: "Are you sure you want to dismiss this place?", delegate: self, cancelButtonTitle: "YES",  otherButtonTitles: "NO")
        alert.show();
        
        
        
    }
    func alertView(alertView: UIAlertView!, clickedButtonAtIndex buttonIndex: Int){
        if (buttonIndex == 0){
            let ePCFRP = EndPointControllerForCluster(delegate: self)
            ePCFRP.dismissCluster(self.auth, clusterId: cluster.datastoreClusterKey)
            uIHelper.showSpinner("DISMISSING CLUSTER")
            
        }else{
            debugPrintln("Will no do anything")
            //DO NOTHING
        }
    }
    func didDismiss(responseCode: NSNumber, entity: String){
        
        debugPrintln("RESPONSE CODE \(responseCode), MESSAGE \(entity)")
        uIHelper.stopSpinner()
        
        
    }
    
    
}
