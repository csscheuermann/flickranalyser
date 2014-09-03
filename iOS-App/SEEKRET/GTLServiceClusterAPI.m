/* This file was generated by the ServiceGenerator.
 * The ServiceGenerator is Copyright (c) 2014 Google Inc.
 */

//
//  GTLServiceClusterAPI.m
//

// ----------------------------------------------------------------------------
// NOTE: This file is generated from Google APIs Discovery Service.
// Service:
//   clusterAPI/v1
// Description:
//   This API serves everything needed to update a cluster.
// Classes:
//   GTLServiceClusterAPI (0 custom class methods, 0 custom properties)

#import "GTLClusterAPI.h"

@implementation GTLServiceClusterAPI

#if DEBUG
// Method compiled in debug builds just to check that all the needed support
// classes are present at link time.
+ (NSArray *)checkClasses {
  NSArray *classes = [NSArray arrayWithObjects:
                      [GTLQueryClusterAPI class],
                      [GTLClusterAPIAddress class],
                      [GTLClusterAPIJsonMap class],
                      [GTLClusterAPIResponse class],
                      nil];
  return classes;
}
#endif  // DEBUG

- (id)init {
  self = [super init];
  if (self) {
    // Version from discovery.
    self.apiVersion = @"v1";

    // From discovery.  Where to send JSON-RPC.
    // Turn off prettyPrint for this service to save bandwidth (especially on
    // mobile). The fetcher logging will pretty print.
    self.rpcURL = [NSURL URLWithString:@"https://seekret-dev.appspot.com/_ah/api/rpc?prettyPrint=false"];
  }
  return self;
}

@end