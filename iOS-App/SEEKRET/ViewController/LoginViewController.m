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
    imgView.contentMode = UIViewContentModeScaleAspectFill;
    [self.viewForBlur addSubview:imgView];
    UIVisualEffect *blur = [UIBlurEffect effectWithStyle:UIBlurEffectStyleLight];
    UIVisualEffectView *bluredView = [[UIVisualEffectView alloc] initWithEffect:blur];
    bluredView.frame = imgView.bounds;
    [imgView addSubview:bluredView];
    
//    self.loginButton.hidden = YES;
    
    [self.loginButton setTitleColor:[UIColor grayColor] forState:UIControlStateHighlighted];
    
    

    self.proceedWithoutLoginButton.hidden = YES;
    signIn = [GPPSignIn sharedInstance];
    // Sie haben zuvor kClientID im Schritt "Den Google+ Client initialisieren" festgelegt,
    signIn.clientID = kClientID;
    
    signIn.scopes = [NSArray arrayWithObjects:kGTLAuthScopePlusLogin,kGTLAuthScopePlusUserinfoEmail,kGTLAuthScopePlusMe,kGTLAuthScopePlusUserinfoProfile, nil];
    signIn.delegate = self;
    BOOL silent = [signIn trySilentAuthentication];
    if (silent) {
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
    signIn = [GPPSignIn sharedInstance];
    MBProgressHUD *hud = [MBProgressHUD showHUDAddedTo:self.view animated:YES];
    hud.mode = MBProgressHUDModeAnnularDeterminate;
    hud.labelText = @"performing login ...";
    dispatch_async(dispatch_get_global_queue( DISPATCH_QUEUE_PRIORITY_LOW, 0), ^{
        [signIn authenticate];
    });
    
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
//        hud.customView = [[[UIImageView alloc] initWithImage:[UIImage imageNamed:@"37x-Checkmark.png"]] autorelease];
        hud.mode = MBProgressHUDModeCustomView;
        hud.labelText = @"Error with Login";
        
        [hud show:YES];
        [hud hide:YES afterDelay:3];
        
    } else {
        [self refreshInterfaceBasedOnSignIn];
    }
}



@end
