//
//  UIHelperMethods.swift
//  SEEKRET
//
//  Created by Constantin Scheuermann on 9/5/14.
//  Copyright (c) 2014 nomis-development.net. All rights reserved.
//

import Foundation


class UIHelperMethods{
    
    
    
    func getValidIndex(showPictureEnum: UISwipeGestureRecognizerDirection, urlArray: [String], counter: Int) -> Int {
        
        var counterToReturn: Int!
        
        var length = urlArray.count - 1
        DDLog.logInfo("Max URL Length: \(length), Current Countervalue: \(counter).")
        
        
        switch showPictureEnum {
        case UISwipeGestureRecognizerDirection.Left:
            if ( counter == length){
                counterToReturn = 0
            }
            
            counterToReturn = counter + 1
            
            if (counterToReturn > length){
                counterToReturn = 0
            }
            
            DDLog.logInfo("Next Picture will be displayed. Max URL Length: \(length), Countervalue: \(counterToReturn).")
            break
        case UISwipeGestureRecognizerDirection.Right:
            
            if ( counter == length){
                counterToReturn = 1
            }
            
            
            counterToReturn = counter - 1
            
            if (counterToReturn < 0){
                counterToReturn = length
            }
            
            DDLog.logInfo("Previous Picture will be displayed. Max URL Length: \(length), Countervalue: \(counterToReturn).")
            break
        default:
            DDLog.logError("Not a safe place for humans ;)")
            fatalError("SWIPE GESTURE THAT WAS NOT IMPLEMENTED, PLEASE IMPLEMENT IT!s")
            break
            
        }
        
        return counterToReturn
        
    }
    
    func setImageToImageView(image: UIImage, imageView: UIImageView){
        
        self.addNiceAnimation(imageView)
        self.debugImageSize(image, message: "ORIGINAL")
        var imageResized = self.resizeImage(image, withWidth: 320, withHeight: 250)
        self.debugImageSize(imageResized, message: "RESIZED")
        var croppedImage = self.croppIngimageByImageName(imageResized, toRect: CGRectMake(0, 0, 320, 200))
        self.debugImageSize(croppedImage, message: "CROPPED")
        imageView.image = croppedImage
        
        
    }
    
    func getValidCounterValue(counterValue: Int, urls: [String]) -> Int{
        var length = urls.count - 1
        if (counterValue > length){
            return length
        }
        return counterValue
    }
    
    
    func debugImageSize(image: UIImage, message: String){
        DDLog.logInfo("SIZE OF IMAGE   \(message), WIDTH \(image.size.width), HEIGHT \(image.size.height)")
    }
    
    func isImageLandscape(image: UIImage) -> Bool{
        if (image.size.width >= image.size.height){
            return true
        }
        debugImageSize(image, message: "NOT LANDSCAPE")
        return false
    }
    
    func addNiceAnimation(clusterImageView: UIImageView){
        var transition:CATransition = CATransition()
        
        transition.duration = 0.5
        transition.timingFunction = CAMediaTimingFunction(name: kCAMediaTimingFunctionEaseInEaseOut);
        transition.type = kCATransitionFade;
        
        clusterImageView.layer.addAnimation(transition, forKey:nil)
    }
    
    func croppIngimageByImageName(imageToCrop: UIImage, toRect:CGRect) -> UIImage
    {
        var clippedRect:CGRect = CGRectMake(0, 0, 640, 400);
        var imageRef:CGImageRef  = CGImageCreateWithImageInRect(imageToCrop.CGImage, clippedRect);
        var croppedImage: UIImage!  = UIImage(CGImage: imageRef)
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
    
    
    
    
}