//
//  UIHelper.swift
//  SEEKRET-SWIFT
//
//  Created by Constantin Scheuermann on 8/26/14.
//  Copyright (c) 2014 Constantin Scheuermann. All rights reserved.
//



class UIHelper{
    
    var spinner: UIActivityIndicatorView = UIActivityIndicatorView(activityIndicatorStyle: UIActivityIndicatorViewStyle.Gray)
    var uiView:UIView
    var label:UILabel!
    var backgroundView:UIView!
    
    var backgroundColor = UIColor(red: 0.5, green:0.5, blue:0.5, alpha:0.5);
    
    init(uiView: UIView){
        self.uiView = uiView
        
    }
    
    
    func showSpinner(text: String){
        
        var screen = UIScreen.mainScreen()
        var boundWidthOfUI = screen.bounds.width
        var boundHeightOfUI = screen.bounds.height
        debugPrintln("UI BOUNDS: WIDTH \(boundWidthOfUI) AND HEIGHT \(boundHeightOfUI)")
        
        
        var viewWidthCenter = boundWidthOfUI / 2
        var viewHeightCenter =  boundHeightOfUI / 2
        debugPrintln("SHOWING SPINNER AT X \(viewWidthCenter) AND Y \(viewHeightCenter)")
        
        
        backgroundView = UIView(frame: CGRectMake(0, 0, boundWidthOfUI, boundHeightOfUI ));
        backgroundView.backgroundColor = backgroundColor;
        
        
        spinner.center = CGPointMake( viewWidthCenter, viewHeightCenter)
        
        spinner.transform = CGAffineTransformMakeScale(2.00, 2.00);
        
        spinner.tag = 12;
        
        spinner.frame = CGRectMake(viewWidthCenter - spinner.bounds.width, viewHeightCenter - spinner.bounds.height, spinner.bounds.width, spinner.bounds.height );
        spinner.backgroundColor = UIColor.clearColor();
        
        backgroundView.addSubview(spinner)
        
        
        
        label = UILabel(frame: CGRectMake(0, viewHeightCenter + spinner.bounds.height, boundWidthOfUI ,  200))
        
        label.textAlignment = NSTextAlignment.Center;
        
        label.autoresizingMask = UIViewAutoresizing.FlexibleWidth;
        label.font = UIFont (name: "HelveticaNeue-UltraLight", size: 30)
        label.numberOfLines = 1;
        
        label.backgroundColor = UIColor.clearColor()
        label.textColor = UIColor.blackColor()
        label.text = text + "..."
        
        
        backgroundView.addSubview(label)
        self.uiView.addSubview(backgroundView)
        

        spinner.startAnimating()
        
    }
    
    func stopSpinner(){
        self.uiView.backgroundColor = UIColor.clearColor()
         self.uiView.backgroundColor = UIColor.whiteColor()
        backgroundView.removeFromSuperview()
        spinner.stopAnimating()
        debugPrintln("STOPPING SPINNER.")

    }
    
    
}