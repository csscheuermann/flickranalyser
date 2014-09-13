//
//  ViewController.m
//  SEEKRET
//
//  Created by Simon Wicha on 02.08.14.
//  Copyright (c) 2014 nomis-development.net. All rights reserved.
//

#import "LoginViewController.h"
#import <GTLPlusConstants.h>
#import <GPPSignInButton.h>
#import "GTLServiceSpotAPI.h"
#import "GTMHTTPFetcher.h"
#import "GTLQuerySpotAPI.h"
#import "GTLSpotAPISpot.h"
#import "GTLSpotAPICluster.h"
#import <MBProgressHUD.h>
#import "SEEKRET-Swift.h"


@interface LoginViewController ()

@property (weak, nonatomic) IBOutlet UIView *viewForBlur;


@end

static NSString * const kClientID = @"449444009918-25mnitq6rqvgnuj0kvrg9qgm1ms2v4gu.apps.googleusercontent.com";

@implementation LoginViewController
static GPPSignIn *signIn;


- (void)viewDidLoad
{
    [super viewDidLoad];
    
    UIImage *bgImage = [UIImage imageNamed:@"background"];
    UIImageView *imgView = [[UIImageView alloc] initWithImage:bgImage];
    [self.viewForBlur addSubview:imgView];
    UIVisualEffect *blur = [UIBlurEffect effectWithStyle:UIBlurEffectStyleLight];
    UIVisualEffectView *bluredView = [[UIVisualEffectView alloc] initWithEffect:blur];
    bluredView.frame = self.viewForBlur.frame;
    [imgView addSubview:bluredView];
    
    [self.loginButton setTitleColor:[UIColor grayColor] forState:UIControlStateHighlighted];
    
    self.proceedWithoutLoginButton.hidden = YES;
    signIn = [GPPSignIn sharedInstance];
    // Sie haben zuvor kClientID im Schritt "Den Google+ Client initialisieren" festgelegt,
    signIn.clientID = kClientID;
    
    signIn.scopes = [NSArray arrayWithObjects:kGTLAuthScopePlusLogin,kGTLAuthScopePlusUserinfoEmail,kGTLAuthScopePlusMe,kGTLAuthScopePlusUserinfoProfile, nil];
    signIn.delegate = self;
}

- (void)viewDidAppear:(BOOL)animated
{
    [super viewDidAppear:animated];
    
    [UIView animateWithDuration:1.f
                          delay:0.5f
                        options: UIViewAnimationOptionTransitionCrossDissolve
                     animations:^{
                         self.logoSeekret.frame = CGRectMake(self.logoSeekret.frame.origin.x, 127, self.logoSeekret.frame.size.width, self.logoSeekret.frame.size.height);
                         self.loginText.hidden = NO;
                         self.loginButton.hidden = NO;
                         self.loginText.alpha = 1.f;
                         self.loginButton.alpha = 1.f;
                     }
                     completion:^(BOOL finished){
                         [self performSelector:@selector(silentLogin) withObject: nil afterDelay:0.2f];
                     }];
    
    
}

- (void)silentLogin {
    if ([signIn trySilentAuthentication]) {
        MBProgressHUD *hud = [MBProgressHUD showHUDAddedTo:self.view animated:YES];
        hud.labelText = @"performing silent login ...";
    }
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}
- (IBAction)loginButtonTouched:(id)sender {
    //THIS WE MUST NOT DO - Otherwise login wont work
    // do not implement [signIn authenticate];
    signIn = [GPPSignIn sharedInstance];
    signIn.delegate = self;
    MBProgressHUD *hud = [MBProgressHUD showHUDAddedTo:self.view animated:YES];
    hud.labelText = @"performing login ...";
    
}
- (IBAction)logutButtonTouched:(id)sender {
    
    [self disconnect];
}
- (IBAction)loginWithoutOAuthTouched:(id)sender {
    //DO NOTHING
}

- (void)disconnect {
    [[GPPSignIn sharedInstance] disconnect];
}

- (void)didDisconnectWithError:(NSError *)error {
    if (error) {
        NSLog(@"Received error %@", error);
    } else {
        // Der Nutzer ist abgemeldet und getrennt.
        // Bereinigen Sie die Nutzerdaten entsprechend den Google+ Vorgaben.
    }
}


-(void)refreshInterfaceBasedOnSignIn
{
    if ([[GPPSignIn sharedInstance] authentication]) {
        
        UINavigationController *myVC = (UINavigationController *)[self.storyboard instantiateViewControllerWithIdentifier:@"NavigationBarController"];
        [self presentViewController:myVC animated:YES completion:nil];
    }
}



- (void)finishedWithAuth: (GTMOAuth2Authentication *)auth error: (NSError *) error
{
    dispatch_async(dispatch_get_main_queue(), ^{
        [MBProgressHUD hideHUDForView:self.view animated:YES];
    });
    NSLog(@"Received error %@ and auth object %@",error, auth);
    if (error) {
        MBProgressHUD *hud = [MBProgressHUD showHUDAddedTo:self.view animated:YES];
        //TODO: Icon für Fehlermeldung
        hud.customView = [[UIImageView alloc] initWithImage:[UIImage imageNamed:@"error.png"]];
        hud.mode = MBProgressHUDModeCustomView;
        hud.labelText = @"Error with Login";
        
        [hud show:YES];
        [hud hide:YES afterDelay:3];
        
    } else {
        [self refreshInterfaceBasedOnSignIn];
    }
}



@end
