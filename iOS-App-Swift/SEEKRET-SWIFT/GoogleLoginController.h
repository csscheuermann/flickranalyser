//
//  ViewController.h
//  SEEKRET
//
//  Created by Simon Wicha on 02.08.14.
//  Copyright (c) 2014 nomis-development.net. All rights reserved.
//
#import <UIKit/UIKit.h>
#import <GPPSignIn.h>
#import "GTMOAuth2Authentication.h"


@class GPPSignInButton;

@interface GoogleLoginController : UIViewController <GPPSignInDelegate>
@property (weak, nonatomic) IBOutlet UIButton *logoutButton;

- (IBAction)logoutTouched:(id)sender;

@property (strong, nonatomic) IBOutlet UIView *view;
@property (weak, nonatomic) IBOutlet GPPSignInButton *loginButton;
@property (weak, nonatomic) IBOutlet UIButton *proceedWithoutLoginButton;
@property (weak, nonatomic) IBOutlet UILabel *alreadyConenctedLabel;


@end
