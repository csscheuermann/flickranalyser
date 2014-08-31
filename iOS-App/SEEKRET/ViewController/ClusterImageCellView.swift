//
//  ClusterImageCellView.swift
//  SEEKRET
//
//  Created by Constantin Scheuermann on 8/29/14.
//  Copyright (c) 2014 nomis-development.net. All rights reserved.
//


import UIKit

class ClusterImageCellView: UITableViewCell {
    
    
    var urls : [String]!
    var counter : Int = 0
    @IBOutlet weak var clusterImageView: UIImageView!
    
    @IBOutlet weak var touristicnessValue: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }
    
    override func setSelected(selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
        
        // Configure the view for the selected state
    }
    
    func setCellViewPicture(){
        if (urls != nil){
            
            let validIndex = getValidCounterValue()
            
            assert(validIndex <= urls.count, "COUNTER WAS BIGGER THAN ARRAY SIZE - SHOULD NEVER HAPPEN. COUNTER \(validIndex) URL ARRAY COUNT \(urls.count)")
            
            
            
            if(urls[validIndex] != "test"){
                var manager = SDWebImageManager.sharedManager()
                manager.downloadImageWithURL(NSURL.URLWithString(urls[validIndex]), options: SDWebImageOptions.RetryFailed, progress: { (receivedSize: NSInteger , expectedSize: NSInteger ) -> Void in
                   
                    }, completed: { (image :UIImage!, error: NSError!, cachType: SDImageCacheType, Bool, finished) -> Void in
                        
                        if (self.isImageLandscape(image)){
                            self.debugImageSize(image, message: "ORIGINAL")
                            
                            var imageResized = self.resizeImage(image, withWidth: 320, withHeight: 250)
                            self.debugImageSize(imageResized, message: "RESIZED")
                            var croppedImage = self.croppIngimageByImageName(imageResized, toRect: CGRectMake(0, 0, 320, 200))
                            self.debugImageSize(croppedImage, message: "CROPPED")
                            
                            self.clusterImageView.image = croppedImage
                        }else{
                            self.clusterImageView.image = nil
                        }
                })
                
       
                
            }
        }
        
    }
    
    
    func isImageLandscape(image: UIImage) -> Bool{
        if (image.size.width > image.size.height){
            return true
        }
        debugImageSize(image, message: "NOT LANDSCAPE")
        return false
    }
    
    func debugImageSize(image: UIImage, message: String){
        debugPrintln("SIZE OF IMAGE (\(message)) : / WIDTH \(image.size.width) // HEIGHT \(image.size.height) ")
    }
    
    func croppIngimageByImageName(imageToCrop: UIImage, toRect:CGRect) -> UIImage
    {
//        //CGRect CropRect = CGRectMake(rect.origin.x, rect.origin.y, rect.size.width, rect.size.height+15);
//        
//        var imageRef:CGImageRef  = CGImageCreateWithImageInRect(imageToCrop.CGImage, toRect);
//        
//        var cropped: UIImage =  UIImage(CGImage: imageRef)
//        
//        
//        return cropped;
        
        

        // Center the crop area
        var clippedRect:CGRect = CGRectMake(0, 0, 640, 400);
        
        // Crop logic
          var imageRef:CGImageRef  = CGImageCreateWithImageInRect(imageToCrop.CGImage, clippedRect);
      
        var croppedImage: UIImage  = UIImage(CGImage: imageRef)
     
        return croppedImage;
        
        
    }
    
    
    
    func resizeImage(image: UIImage,  withWidth: CGFloat, withHeight: CGFloat) -> UIImage
    {
        var newSize = CGSizeMake(withWidth, withHeight)
        var widthRatio = newSize.width/image.size.width
        var heightRatio = newSize.height/image.size.height
        
        if(widthRatio > heightRatio)
        {
            newSize=CGSizeMake(image.size.width*heightRatio,image.size.height*heightRatio)
        }
        else
        {
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
    
    func setTouristicnessValue(touristicnessValue : Int){
        switch touristicnessValue {
        case 0,1,2:
            self.touristicnessValue.text = "ABSOLUTE SEEKRET"
        case 3,4:
            self.touristicnessValue.text = "SEEKRET"
        case 5,6,7:
            self.touristicnessValue.text = "TOURISTIC"
        case 8,9,10:
            self.touristicnessValue.text = "ABSOLUTE TOURISTIC"
        default:
            self.touristicnessValue.text = "NO VOTES AVAILBALE"
        }
    }
    
    func setNextPicture(){
        
        var length = urls.count - 1
        
        if ( counter == length){
            counter = 0
        }else{
            counter = counter + 1
        }
        
        debugPrintln("Next Picture will be displayed. Max URL Length: \(length), Countervalue: \(counter).")
        setCellViewPicture()
    }
    func setPreviousPicture(){
        var length = urls.count - 1
        
        if ( counter == 0){
            counter = length
        }else{
            counter = counter - 1
        }
        debugPrintln("Previous Picture will be displayed. Max URL Length: \(length), Countervalue: \(counter).")
        setCellViewPicture()
    }
    
    
    
    
}
