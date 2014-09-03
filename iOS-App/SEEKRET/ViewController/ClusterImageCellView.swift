//
//  ClusterImageCellView.swift
//  SEEKRET
//
//  Created by Constantin Scheuermann on 8/29/14.
//  Copyright (c) 2014 nomis-development.net. All rights reserved.
//


import UIKit

class ClusterImageCellView: UITableViewCell, SDWebImageManagerDelegate{
    
    
    @IBOutlet weak var clusterImageView: UIImageView!
    
    var urls : [String]!
    var counter : Int = 0
    
    var circleView: UIView!
    var circleViewPictureCounterLabel: UILabel!
    var addressLabel: UILabel!
    var addressSubLabel: UILabel!
    var clusterDataStoreKey: String!
    var touristicnessValue : Int!
    
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.initCircle()
        
    }
    
    
    
    override func setSelected(selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }
    
    func setCellViewPicture(){
        if (urls != nil){
            let validIndex = getValidCounterValue()
            assert(validIndex <= urls.count, "COUNTER WAS BIGGER THAN ARRAY SIZE - SHOULD NEVER HAPPEN. COUNTER \(validIndex) URL ARRAY COUNT \(urls.count)")
            
            var manager = SDWebImageManager.sharedManager()
            manager.downloadImageWithURL(NSURL.URLWithString(urls[validIndex]), options: SDWebImageOptions.RetryFailed,
                progress: { (receivedSize: NSInteger , expectedSize: NSInteger ) -> Void in
                    NSLog("RECEIVED SIZE  %d, EXPECTED SIZE %d", receivedSize, expectedSize)
                },
                
                completed: { (image :UIImage!, error: NSError!, cachType: SDImageCacheType, Bool, finished) -> Void in
                    //TODO COS AND SIW: DAS SOLLTEN WIR MAL VERSTEHEN. WIE FUNKTIONIERT DIESER CROP. Ich habe es so hingebogen, dass es geht, aber verstehen tu ich es nicht mehr ... ich will skalieren und dann croppen.
                    self.addNiceAnimation()
                    
                    self.debugImageSize(image, message: "ORIGINAL")
                    var imageResized = self.resizeImage(image, withWidth: 320, withHeight: 250)
                    self.debugImageSize(imageResized, message: "RESIZED")
                    var croppedImage = self.croppIngimageByImageName(imageResized, toRect: CGRectMake(0, 0, 320, 200))
                    self.debugImageSize(croppedImage, message: "CROPPED")
                    self.clusterImageView.image = croppedImage
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
    
    func addNiceAnimation(){
        var transition:CATransition = CATransition()
        
        transition.duration = 0.5
        transition.timingFunction = CAMediaTimingFunction(name: kCAMediaTimingFunctionEaseInEaseOut);
        transition.type = kCATransitionFade;
        
        self.clusterImageView.layer.addAnimation(transition, forKey:nil)
    }
    
    func isImageLandscape(image: UIImage) -> Bool{
        if (image.size.width >= image.size.height){
            return true
        }
        debugImageSize(image, message: "NOT LANDSCAPE")
        return false
    }
    
    func debugImageSize(image: UIImage, message: String){
        NSLog("SIZE OF IMAGE   %@, WIDTH %@, HEIGHT %@", message, image.size.width, image.size.height)
    }
    
    func croppIngimageByImageName(imageToCrop: UIImage, toRect:CGRect) -> UIImage
    {
        var clippedRect:CGRect = CGRectMake(0, 0, 640, 400);
        var imageRef:CGImageRef  = CGImageCreateWithImageInRect(imageToCrop.CGImage, clippedRect);
        var croppedImage: UIImage  = UIImage(CGImage: imageRef)
        return croppedImage;
    }
    
    
    
    func resizeImage(image: UIImage,  withWidth: CGFloat, withHeight: CGFloat) -> UIImage{
        var newSize = CGSizeMake(withWidth, withHeight)
        var widthRatio = newSize.width/image.size.width
        var heightRatio = newSize.height/image.size.height
        
        if(widthRatio > heightRatio){
            newSize=CGSizeMake(image.size.width*heightRatio,image.size.height*heightRatio)
        }else{
            newSize=CGSizeMake(image.size.width*widthRatio,image.size.height*widthRatio)
        }
        
        UIGraphicsBeginImageContextWithOptions(newSize, false, 0.0)
        image.drawInRect( CGRectMake(0,0,newSize.width,newSize.height))
        var newImage: UIImage = UIGraphicsGetImageFromCurrentImageContext();
        UIGraphicsEndImageContext();
        
        return newImage;
    }
    
    
    func getValidCounterValue() -> Int{
        var length = urls.count - 1
        if (counter > length){
            return length
        }
        return counter
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
        NSLog("CLUSTER IMAGE VIEW HEIGHT %@", clusterImageView.bounds.height)
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
        var validCount = self.getValidCounterValue()+1
        var urlCount = self.urls.count
        self.circleViewPictureCounterLabel.text = "\(validCount)/\(urlCount)"
    }
    
    
    func showPicture(showPictureEnum: UISwipeGestureRecognizerDirection){
        
        var length = urls.count - 1
        NSLog("Max URL Length: %d, Current Countervalue: %d.", length, self.counter)
        
        if ( self.counter == length){
            self.counter = 0
        }else{
            switch showPictureEnum {
            case UISwipeGestureRecognizerDirection.Left:
                self.counter = self.counter + 1
                
                if (self.counter > length){
                    self.counter = 0
                }
                
                NSLog("Next Picture will be displayed. Max URL Length: %d, Countervalue: %d.", length, self.counter)
                break
            case UISwipeGestureRecognizerDirection.Right:
                
                self.counter = self.counter - 1
                
                if (self.counter < 0){
                    self.counter = length
                }
                
                NSLog("Previous Picture will be displayed. Max URL Length: %d, Countervalue: %d.", length, self.counter)
                break
            default:
                NSLog("Not a safe place for humans ;)")
                fatalError("SWIPE GESTURE THAT WAS NOT IMPLEMENTED, PLEASE IMPLEMENT IT!s")
                break
            }
        }
        setCellViewPicture()
        
        
    }
    
}
