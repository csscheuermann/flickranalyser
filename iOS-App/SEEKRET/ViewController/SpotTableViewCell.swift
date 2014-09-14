//
//  SpotTableViewCell.swift
//  SEEKRET
//
//  Created by Constantin Scheuermann on 8/29/14.
//  Copyright (c) 2014 nomis-development.net. All rights reserved.
//


import UIKit

class SpotTableViewCell: UITableViewCell, SDWebImageManagerDelegate{
    
    
    @IBOutlet weak var clusterImageView: UIImageView!
    
    var urls : [String]!
    var counter : Int = 0
    
    var circleView: UIView!
    var circleViewPictureCounterLabel: UILabel!
    var addressLabel: UILabel!
    var addressSubLabel: UILabel!
    var clusterDataStoreKey: String!
    var touristicnessValue : Int!
    var uiHelperMethods: UIHelperMethods!
    
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.initCircle()
        self.uiHelperMethods = UIHelperMethods()
    }
    
 
    
    override func setSelected(selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }
    
    func setCellViewPicture(){
        if (urls != nil){
            let validIndex = uiHelperMethods.getValidCounterValue(self.counter, urls: urls)
            assert(validIndex <= urls.count, "COUNTER WAS BIGGER THAN ARRAY SIZE - SHOULD NEVER HAPPEN. COUNTER \(validIndex) URL ARRAY COUNT \(urls.count)")
            
            var manager = SDWebImageManager.sharedManager()
            manager.downloadImageWithURL(NSURL.URLWithString(urls[validIndex]), options: SDWebImageOptions.RetryFailed,
                progress: { (receivedSize: NSInteger , expectedSize: NSInteger ) -> Void in
                       DDLog.logInfo("RECEIVED SIZE  \(receivedSize), EXPECTED SIZE \(expectedSize)")
                },
                
                completed: { (image :UIImage!, error: NSError!, cachType: SDImageCacheType, Bool, finished) -> Void in
                    //TODO COS AND SIW: DAS SOLLTEN WIR MAL VERSTEHEN. WIE FUNKTIONIERT DIESER CROP. Ich habe es so hingebogen, dass es geht, aber verstehen tu ich es nicht mehr ... ich will skalieren und dann croppen.
                    self.uiHelperMethods.setImageToImageView(image, imageView: self.clusterImageView )
                    self.setTextForVotes()
            })
        }
    }
    
    func setAdress(address: String){
        
        var myArray = address.componentsSeparatedByString(",")
        self.addressLabel.font = UIFont.boldSystemFontOfSize(16)
        self.addressSubLabel.font = UIFont.systemFontOfSize(14);
        self.addressSubLabel.textColor = UIColor.grayColor()
        
        if (myArray.count > 1){
            self.addressLabel.text = myArray[0]
            self.addressSubLabel.text = myArray[1]
        }else{
            self.addressLabel.text = "NO ADDRESS AVAILABLE"
            self.addressSubLabel.text = "NO SUBADDRESS AVAILABLE"
        }
    }
    
   
        
    func setTouristicnessValue(){
        switch self.touristicnessValue {
        case 0,1,2:
            self.circleView.backgroundColor = UIColor.greenColor()
        case 3,4:
            self.circleView.backgroundColor = UIColor.yellowColor()
        case 5,6,7:
            self.circleView.backgroundColor = UIColor.orangeColor()
        case 8,9,10:
            self.circleView.backgroundColor = UIColor.redColor()
        default:
            self.circleView.backgroundColor = UIColor.purpleColor()
        }
    }
    
    func initCircle(){
        let circleWidth:CGFloat = 50.0
        let circleHeight:CGFloat = 50.0
        
        let startPointX:CGFloat = clusterImageView.bounds.width-(1.25*circleWidth)
        DDLog.logInfo("CLUSTER IMAGE VIEW HEIGHT \(clusterImageView.bounds.height)")
        let startPointY:CGFloat = clusterImageView.frame.height-(circleHeight/2)
        
        self.circleView = UIView(frame: CGRectMake(startPointX,startPointY,circleWidth,circleHeight));
        self.circleView.alpha = 1;
        self.circleView.layer.cornerRadius = circleHeight/2;
        self.circleView.backgroundColor = UIColor.blueColor();
        self.circleView.layer.borderColor = UIColor.whiteColor().CGColor;
        self.circleView.layer.borderWidth = 2
        
        self.circleViewPictureCounterLabel = UILabel(frame: CGRectMake(10,0,50.0,50.0))
        self.addressLabel = UILabel(frame: CGRectMake(10,startPointY+35,240,30));
        self.addressSubLabel = UILabel(frame: CGRectMake(5,startPointY+60,240,30));
        
        self.clusterImageView.addSubview(addressSubLabel)
        self.clusterImageView.addSubview(circleView)
        self.circleView.addSubview(circleViewPictureCounterLabel)
        self.clusterImageView.addSubview(addressLabel)
        
        self.circleViewPictureCounterLabel.layer.zPosition = 106
        self.circleView.layer.zPosition = 105
    }
    
    
    
    func setTextForVotes(){
        var validCount = uiHelperMethods.getValidCounterValue(self.counter, urls: urls)+1
        var urlCount = self.urls.count
        self.circleViewPictureCounterLabel.text = "\(validCount)/\(urlCount)"
    }
    
    
    func showPicture(showPictureEnum: UISwipeGestureRecognizerDirection){
        self.counter = uiHelperMethods.getValidIndex(showPictureEnum, urlArray: urls , counter: self.counter)
        setCellViewPicture()
    }
    
}
