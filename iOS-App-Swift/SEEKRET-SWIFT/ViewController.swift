//
//  ViewController.swift
//  SEEKRET-SWIFT
//
//  Created by Constantin Scheuermann on 8/25/14.
//  Copyright (c) 2014 Constantin Scheuermann. All rights reserved.
//

import UIKit


class ViewController: UIViewController{
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        let spotAPI = GTLServiceSpotAPI()
       
        
        spotAPI.retryEnabled = true
        
        var query:GTLQuerySpotAPI = GTLQuerySpotAPI.queryForGetNearestSpotByAddressWithSpotName("BERLIN") as GTLQuerySpotAPI
        var ticket:GTLServiceTicket = GTLServiceTicket()
        var returnedSpot:GTLSpotAPISpot
        var nsError:NSError
    
        
        spotAPI.executeQuery(query, completionHandler: { (ticket, returnedSpot, nsError) -> Void in
            println(returnedSpot.clusterRadiusInKm)
            
            var cluster:[GTLSpotAPICluster] = returnedSpot.cluster as [GTLSpotAPICluster]!;
            
        
            for (index, currentCluster) in enumerate(cluster){
                println(index)
                var castedCluster = currentCluster as GTLSpotAPICluster
                var castedClusterLatitude = castedCluster.latitude
                println(castedClusterLatitude)
            }
            
            
            
            var urls:[AnyObject] =  cluster.first?.urlOfMostViewedPicture as [AnyObject]!;
            
            
            
            
            
            for (index, currentItem) in enumerate(urls){
                
                
                var url:String = currentItem as String
                println(url)
                println(index)
                let nsURL = NSURL.URLWithString(url);
                var err: NSError?
                
                var imageData :NSData = NSData.dataWithContentsOfURL(nsURL,options: NSDataReadingOptions.DataReadingMappedIfSafe, error: &err)
      
                
                var bgImage = UIImage(data:imageData)
                var positionY = index*(Int(bgImage.size.height));
                
                var imageView = UIImageView(frame: CGRectMake(10, CGFloat(positionY)+30, bgImage.size.width, bgImage.size.height))
                imageView.image = bgImage
                self.view.addSubview(imageView)
                
            }
            
           
            
        })
        
        
        
    
        
        
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

