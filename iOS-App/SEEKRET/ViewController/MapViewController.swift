//
//  MapViewController.swift
//  SEEKRET-SWIFT
//
//  Created by Constantin Scheuermann on 8/25/14.
//  Copyright (c) 2014 Constantin Scheuermann. All rights reserved.
//

import UIKit
import MapKit

class MapViewController: UIViewController, EndpointControllerProtocol{
    
    
    @IBOutlet weak var labelSpotName: UILabel!
    var uiHelper:UIHelper!
    @IBOutlet weak var mapView: MKMapView!
    
    
    func didRecieveCluster(cluster: [GTLSpotAPICluster], spotName: String){
        
        var annotations = [MKPointAnnotation]()
        labelSpotName.text = spotName
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
            self.mapView.setRegion(theRegion, animated: true)
            
            
            annotations.append(CoralCastleAnnotation)
            
        }
        
        self.mapView.addAnnotations(annotations)
        uiHelper.stopSpinner()
        
        
    }
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        
        
        
        doLoadCluster();
        
    }
    
    
    
    func doLoadCluster(){
        self.uiHelper = UIHelper(uiView: self.mapView)
        uiHelper.showSpinner("Fetching Cluster")
        let endpointController = EnpointController(delegate: self);
        endpointController.getCluster("SANTOS")
    }
    
    
    
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    
    
    
    
    
}
