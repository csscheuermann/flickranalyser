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
                let url = NSURL.URLWithString(urls[validIndex]);
                var err: NSError?
                var imageData :NSData = NSData.dataWithContentsOfURL(url,options: NSDataReadingOptions.DataReadingMappedIfSafe, error: &err)
                var bgImage = UIImage(data:imageData)
          
                debugPrintln("SIZE OF IMAGE: \(bgImage.size.height) /  \(bgImage.size.width)")
                
                // Crop to 200 * 150
             var croppedImage = self.croppIngimageByImageName(bgImage, toRect: CGRectMake(0, 0, 250, 200))
                         debugPrintln("SIZE OF IMAGE: \(croppedImage.size.height) /  \(croppedImage.size.width)")
                
                clusterImageView.image = croppedImage
            }
        }
        
    }
    func croppIngimageByImageName(imageToCrop: UIImage, toRect:CGRect) -> UIImage
    {
    //CGRect CropRect = CGRectMake(rect.origin.x, rect.origin.y, rect.size.width, rect.size.height+15);
    
        var imageRef:CGImageRef  = CGImageCreateWithImageInRect(imageToCrop.CGImage, toRect);
        
        var cropped: UIImage =  UIImage(CGImage: imageRef)
        
        
    return cropped;
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
