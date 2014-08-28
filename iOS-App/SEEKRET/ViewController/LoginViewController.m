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


@interface LoginViewController ()



@end

static NSString * const kClientID = @"449444009918-25mnitq6rqvgnuj0kvrg9qgm1ms2v4gu.apps.googleusercontent.com";

@implementation LoginViewController
static GPPSignIn *signIn;


- (void)viewDidLoad
{
    [super viewDidLoad];
    [self.loginButton setTitleColor:[UIColor grayColor] forState:UIControlStateHighlighted];
    
    self.alreadyConenctedLabel.hidden = YES;
    signIn = [GPPSignIn sharedInstance];
    // Sie haben zuvor kClientID im Schritt "Den Google+ Client initialisieren" festgelegt,
    signIn.clientID = kClientID;
    
    signIn.scopes = [NSArray arrayWithObjects:kGTLAuthScopePlusLogin,kGTLAuthScopePlusUserinfoEmail,kGTLAuthScopePlusMe,kGTLAuthScopePlusUserinfoProfile, nil];
    signIn.delegate = self;
    [signIn trySilentAuthentication];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}
- (IBAction)loginButtonTouched:(id)sender {
    
    signIn = [GPPSignIn sharedInstance];
    [signIn authenticate];
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
  //  static GTLServiceSpotAPI *service = nil;
//    if (!service) {
//        service = [[GTLServiceSpotAPI alloc] init];
//        service.retryEnabled = YES;
//        
//        GTLQuerySpotAPI *query = [GTLQuerySpotAPI queryForGetNearestSpotByAddressWithSpotName:@"Berlin, Germany"];
//        [service executeQuery:query completionHandler:^(GTLServiceTicket *ticket, GTLSpotAPISpot *object, NSError *error){
//            NSNumber *array = object.overallMaxPOINumberPerCluster;
////            object.cluster[0]
//            GTLSpotAPICluster *cluster = object.cluster[0];
//            NSURL *url = cluster.urlOfMostViewedPicture[0];
//            NSLog(@"First output: %@", array);
//        }];
//        
//    }
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
