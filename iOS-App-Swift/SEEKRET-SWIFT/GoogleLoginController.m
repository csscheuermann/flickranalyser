//
//  ViewController.m
//  SEEKRET
//
//  Created by Simon Wicha on 02.08.14.
//  Copyright (c) 2014 nomis-development.net. All rights reserved.
//

#import "GoogleLoginController.h"
#import <GTLPlusConstants.h>
#import <GPPSignInButton.h>
#import "GTLServiceSpotAPI.h"
#import "GTMHTTPFetcher.h"
#import "GTLQuerySpotAPI.h"
#import "GTLSpotAPISpot.h"
#import "GTLSpotAPICluster.h"



@interface GoogleLoginController ()

@property (nonatomic, strong) GPPSignIn *signIn;

@end

static NSString * const kClientID = @"449444009918-25mnitq6rqvgnuj0kvrg9qgm1ms2v4gu.apps.googleusercontent.com";

@implementation GoogleLoginController

- (void)viewDidLoad
{
    [super viewDidLoad];
    [self.loginButton setTitleColor:[UIColor grayColor] forState:UIControlStateHighlighted];
      NSLog(@"LOADING GOOGLE LOGIN");
    self.alreadyConenctedLabel.hidden = YES;
    self.signIn = [GPPSignIn sharedInstance];
    // Sie haben zuvor kClientID im Schritt "Den Google+ Client initialisieren" festgelegt,
    self.signIn.clientID = kClientID;
    self.signIn.scopes = [NSArray arrayWithObjects:kGTLAuthScopePlusLogin, nil];
    self.signIn.delegate = self;
    [self.signIn trySilentAuthentication];
    self.signIn = [GPPSignIn sharedInstance];
    [self.signIn authenticate];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}
- (IBAction)loginButtonTouched:(id)sender {
    
    self.signIn = [GPPSignIn sharedInstance];
    [self.signIn authenticate];
}
- (IBAction)logutButtonTouched:(id)sender {

    [self disconnect];
}
- (IBAction)loginWithoutOAuthTouched:(id)sender {
    [self showEndpoints];
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
        // Der Nutzer ist angemeldet.
        // Führen Sie hier andere Aktionen durch, z. B. das Anzeigen einer Abmelden-Schaltfläche.
        self.alreadyConenctedLabel.hidden = NO;
        self.loginButton.hidden = YES;
    } else {
        // Führen Sie hier andere Aktionen durch.
    }
}

-(void)showEndpoints {
    
   }

- (void)finishedWithAuth: (GTMOAuth2Authentication *)auth error: (NSError *) error
{
    NSLog(@"Received error %@ and auth object %@",error, auth);
    if (error) {
        // Führen Sie hier die Fehlerbehandlung durch.
    } else {
        [self refreshInterfaceBasedOnSignIn];
    }
}


@end
