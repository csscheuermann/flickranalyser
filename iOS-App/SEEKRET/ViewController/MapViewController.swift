//
//  MapViewController.swift
//  SEEKRET-SWIFT
//
//  Created by Constantin Scheuermann on 8/25/14.
//  Copyright (c) 2014 Constantin Scheuermann. All rights reserved.
//

import UIKit
import MapKit

class MapViewController: UIViewController, EndpointControllerProtocol, GPPSignInDelegate{
    
    @IBOutlet weak var spotMapView: MKMapView!
 
    var uiHelper:UIHelper!
    var spotName: String?

    
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
    

    
    
    func finishedWithAuth(auth: GTMOAuth2Authentication,  error: NSError? ) -> Void{
        if error != nil{
            debugPrintln("AUTH WENT WRONG")
        }else{
            debugPrintln("FINISHED WITH AUTH")
            self.uiHelper = UIHelper(uiView: self.spotMapView)
            uiHelper.showSpinner("Fetching Cluster")
            let endpointController = EnpointController(delegate: self);
            endpointController.getCluster(spotName!, auth: auth)
        }
    }
    
    
    func didRecieveCluster(cluster: [GTLSpotAPICluster], spotName: String){
        var annotations = [MKPointAnnotation]()
        for (index, currentCluster) in enumerate(cluster){
            var castedCluster = currentCluster as GTLSpotAPICluster
            var castedClusterLatitude = castedCluster.latitude
            var CoralCastleAnnotation = MKPointAnnotation()
            var latitude:CLLocationDegrees = castedCluster.latitude
            var longitude:CLLocationDegrees = castedCluster.longitude
            var churchLocation:CLLocationCoordinate2D = CLLocationCoordinate2DMake(latitude, longitude)
            CoralCastleAnnotation.coordinate = churchLocation
            CoralCastleAnnotation.title = "TEST"
            CoralCastleAnnotation.subtitle = "TEST"
            var latDelta:CLLocationDegrees = 0.001
            var longDelta:CLLocationDegrees = 0.001
            var theSpan:MKCoordinateSpan = MKCoordinateSpanMake(latDelta, longDelta)
            var theRegion:MKCoordinateRegion = MKCoordinateRegionMake(churchLocation, theSpan)
            self.spotMapView.setRegion(theRegion, animated: true)
            annotations.append(CoralCastleAnnotation)
        }
        
        self.spotMapView.addAnnotations(annotations)
        uiHelper.stopSpinner()
    }
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        performSilentLogin();
    }
    
 
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
}
