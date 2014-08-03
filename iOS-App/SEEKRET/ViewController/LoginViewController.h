//
//  ViewController.h
//  SEEKRET
//
//  Created by Simon Wicha on 02.08.14.
//  Copyright (c) 2014 nomis-development.net. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <GPPSignIn.h>

@class GPPSignInButton;

@interface LoginViewController : UIViewController <GPPSignInDelegate>

@property (strong, nonatomic) IBOutlet UIView *view;
@property (weak, nonatomic) IBOutlet UIButton *loginButton;

@end
