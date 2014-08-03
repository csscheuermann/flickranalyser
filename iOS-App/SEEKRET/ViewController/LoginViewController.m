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


@interface LoginViewController ()

@property (nonatomic, strong) GPPSignIn *signIn;

@end

static NSString * const kClientID = @"1099379908084-v0l7ieuv3mvu4i3ql2psaou2l0aucfk6.apps.googleusercontent.com";

@implementation LoginViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
    self.signIn = [GPPSignIn sharedInstance];
    // Sie haben zuvor kClientID im Schritt "Den Google+ Client initialisieren" festgelegt,
    self.signIn.clientID = kClientID;
    self.signIn.scopes = [NSArray arrayWithObjects:kGTLAuthScopePlusLogin, nil];
    self.signIn.delegate = self;
    [self.signIn trySilentAuthentication];
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

-(void)refreshInterfaceBasedOnSignIn
{
    if ([[GPPSignIn sharedInstance] authentication]) {
        // Der Nutzer ist angemeldet.
        self.loginButton.hidden = YES;
        // Führen Sie hier andere Aktionen durch, z. B. das Anzeigen einer Abmelden-Schaltfläche.
    } else {
        self.loginButton.hidden = NO;
        // Führen Sie hier andere Aktionen durch.
    }
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
