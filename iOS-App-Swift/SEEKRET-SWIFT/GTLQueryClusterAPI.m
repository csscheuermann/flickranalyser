/* This file was generated by the ServiceGenerator.
 * The ServiceGenerator is Copyright (c) 2014 Google Inc.
 */

//
//  GTLQueryClusterAPI.m
//

// ----------------------------------------------------------------------------
// NOTE: This file is generated from Google APIs Discovery Service.
// Service:
//   clusterAPI/v1
// Description:
//   This API serves everything needed to update a cluster.
// Classes:
//   GTLQueryClusterAPI (3 custom class methods, 6 custom properties)

#import "GTLQueryClusterAPI.h"

#import "GTLClusterAPIAddress.h"
#import "GTLClusterAPIResponse.h"

@implementation GTLQueryClusterAPI

@dynamic datastoreKeyOfCluster, fields, latitude, longitude, spotName,
         touristicnessRatingFrom1To10;

#pragma mark -
#pragma mark Service level methods
// These create a GTLQueryClusterAPI object.

+ (id)queryForEvaluateClusterWithDatastoreKeyOfCluster:(NSString *)datastoreKeyOfCluster
                          touristicnessRatingFrom1To10:(NSInteger)touristicnessRatingFrom1To10
                                              spotName:(NSString *)spotName {
  NSString *methodName = @"clusterAPI.evaluateCluster";
  GTLQueryClusterAPI *query = [self queryWithMethodName:methodName];
  query.datastoreKeyOfCluster = datastoreKeyOfCluster;
  query.touristicnessRatingFrom1To10 = touristicnessRatingFrom1To10;
  query.spotName = spotName;
  query.expectedObjectClass = [GTLClusterAPIResponse class];
  return query;
}

+ (id)queryForGetAddressFromLatLngWithLatitude:(double)latitude
                                     longitude:(double)longitude {
  NSString *methodName = @"clusterAPI.getAddressFromLatLng";
  GTLQueryClusterAPI *query = [self queryWithMethodName:methodName];
  query.latitude = latitude;
  query.longitude = longitude;
  query.expectedObjectClass = [GTLClusterAPIAddress class];
  return query;
}

+ (id)queryForIncrementDismissCountWithDatastoreKeyOfCluster:(NSString *)datastoreKeyOfCluster {
  NSString *methodName = @"clusterAPI.incrementDismissCount";
  GTLQueryClusterAPI *query = [self queryWithMethodName:methodName];
  query.datastoreKeyOfCluster = datastoreKeyOfCluster;
  query.expectedObjectClass = [GTLClusterAPIResponse class];
  return query;
}

@end
