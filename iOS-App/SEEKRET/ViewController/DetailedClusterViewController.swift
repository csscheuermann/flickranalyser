//
//  DetailedClusterViewController.swift
//  SEEKRET
//
//  Created by Constantin Scheuermann on 9/2/14.
//  Copyright (c) 2014 nomis-development.net. All rights reserved.
//

import UIKit
import MapKit

class DetailedClusterViewController: CustomSeekretUIViewController, EndpointControllerforRatingProtocoll, SDWebImageManagerDelegate, EndPointControllerForClusterProtocoll, UITabBarDelegate, UIAlertViewDelegate{
    
    @IBOutlet weak var uiImageClusterImage: UIImageView!
    @IBOutlet weak var tabBarForVoting: UITabBar!
    
    var counter: Int!
    var ePCFRP: EndPointControllerForCluster!
    var ePCFRPAPI: EndpointControllerforRatingAPI!
    var cluster: GTLSpotAPICluster!
    var uIHelper: MBProgressHUD!
    var urls: [String]!
    var spotName: String!
    
    
    @IBOutlet weak var uiImageViewForBluredBackground: UIImageView!
    @IBOutlet weak var mapView: MKMapView!
    var uiHelperMethods: UIHelperMethods!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.uiHelperMethods = UIHelperMethods()
        
        self.uIHelper = MBProgressHUD .showHUDAddedTo(self.view, animated: true);
        self.navigationItem.title = cluster.name
        
        self.tabBarForVoting.delegate = self
        self.addPointToMapView()
        self.addGestureListenerToUiImageView(self.uiImageClusterImage)
        self.urls = cluster.urlOfMostViewedPicture as [String]
        self.counter = 0
        self.loadImageForIndex(0)
        self.ePCFRP = EndPointControllerForCluster(delegate: self)
        self.ePCFRPAPI =  EndpointControllerforRatingAPI(delegate: self)
        ePCFRPAPI.hasAlreadyVoted(self.auth, clusterId: cluster.datastoreClusterKey)
        uIHelper.labelText = "CHECKING SEEKRET"
    }
    
    func addGestureListenerToUiImageView(imageView: UIImageView){
        imageView.userInteractionEnabled = true;
        var swipeRight = UISwipeGestureRecognizer(target: self, action: "respondToSwipeGesture:")
        swipeRight.direction = UISwipeGestureRecognizerDirection.Right
        imageView.addGestureRecognizer(swipeRight)
        
        var swipeDown = UISwipeGestureRecognizer(target: self, action: "respondToSwipeGesture:")
        swipeDown.direction = UISwipeGestureRecognizerDirection.Left
        imageView.addGestureRecognizer(swipeDown)
        
    }
    
    func didReceiveHasAlreadyVotedOrDismissed(responseCode: NSNumber, entity: Bool){
        self.setEnableStatusForRatingBar(!entity)
        NSLog("MESSAGE: %@, RESPONSE CODE: %d" , entity, responseCode)
        uIHelper.hide(true)
        
    }
    
    func setEnableStatusForRatingBar(enablestatus: Bool){
        var allItemsOfRatingBar: [UITabBarItem] = tabBarForVoting.items as [UITabBarItem]
        allItemsOfRatingBar[0].enabled = enablestatus
        allItemsOfRatingBar[1].enabled = enablestatus
        allItemsOfRatingBar[2].enabled = enablestatus
        
    }
    
    @IBAction func mapViewExpandTouched(sender: AnyObject) {
        NSLog("hallo")
    }
    
    func respondToSwipeGesture(gesture: UIGestureRecognizer) {
        
        if let swipeGesture = gesture as? UISwipeGestureRecognizer {
            
            switch swipeGesture.direction {
            case UISwipeGestureRecognizerDirection.Right:
                NSLog("SWIPE RIGHT")
                counter = uiHelperMethods.getValidIndex(UISwipeGestureRecognizerDirection.Right, urlArray: urls, counter: self.counter)
                loadImageForIndex(counter)
                break
            case UISwipeGestureRecognizerDirection.Left:
                counter = uiHelperMethods.getValidIndex(UISwipeGestureRecognizerDirection.Left, urlArray: urls, counter: self.counter)
                loadImageForIndex(counter)
                NSLog("SWIPE LEFT")
                break
            default:
                NSLog("Not a safe place for humans ;)")
                fatalError("SWIPE GESTURE THAT WAS NOT IMPLEMENTED, PLEASE IMPLEMENT IT!")
                break
            }
        }
    }
    
    
    
    func loadImageForIndex(index: Int){
        
        var manager = SDWebImageManager.sharedManager()
        
        manager.downloadImageWithURL(NSURL.URLWithString(urls[index]), options: SDWebImageOptions.RetryFailed,
            progress: { (receivedSize: NSInteger , expectedSize: NSInteger ) -> Void in
                NSLog("RECEIVED SIZE  %d, EXPECTED SIZE %d", receivedSize, expectedSize)
            },
            
            completed: { (image :UIImage!, error: NSError!, cachType: SDImageCacheType, Bool, finished) -> Void in
                
                self.uiImageViewForBluredBackground.image = image
                self.uiHelperMethods.setImageToImageView(image, imageView: self.uiImageClusterImage)
        })
    }
    
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    override func handleSucessfullLogin(auth: GTMOAuth2Authentication) -> Void {
        //DO NOTHING AT THE MOMENT
    }
    
    func tabBar(tabBar: UITabBar!, didSelectItem item: UITabBarItem!) {
        var itemsOfTabBar = tabBar.selectedItem
        for (index, currentItem) in enumerate(tabBar.items as [UITabBarItem]){
            if (currentItem == item){
                NSLog("Current Item is %d", index)
                if (index == 0){
                    showAreYouSureDialogBox()
                }else if (index == 1){
                    ePCFRP.evaluateCluster(auth, touristicness: 0,  datastoreClusterKey: cluster.datastoreClusterKey, spotName: spotName)
                    uIHelper.labelText = "VOTING CLUSTER AS SEEKRET"
                }else if (index == 2){
                    ePCFRP.evaluateCluster(auth, touristicness: 10,  datastoreClusterKey: cluster.datastoreClusterKey, spotName: spotName)
                    uIHelper.labelText = "VOTING CLUSTER AS TOURISTIC"
                    
                }else{
                    fatalError("WE HAVE NOT BEHAVIOUR FOR THAT!")
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
            
            ePCFRP.dismissCluster(self.auth, clusterId: cluster.datastoreClusterKey)
            uIHelper.labelText = "DISMISSING CLUSTER"
            
        }else{
            NSLog("Will no do anything")
        }
    }
    func didDismiss(responseCode: NSNumber, entity: String){
        NSLog("RESPONSE CODE %d, MESSAGE %@", responseCode, entity)
        uIHelper.hide(true)
        self.setEnableStatusForRatingBar(false)
    }
    
    func didEvaluation(responseCode: NSNumber, entity: String){
        NSLog("RESPONSE CODE %d, MESSAGE %@", responseCode, entity)
        uIHelper.hide(true)
        self.setEnableStatusForRatingBar(false)
    }
    
}
