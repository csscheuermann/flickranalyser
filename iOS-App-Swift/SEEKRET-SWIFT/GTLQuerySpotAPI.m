/* This file was generated by the ServiceGenerator.
 * The ServiceGenerator is Copyright (c) 2014 Google Inc.
 */

//
//  GTLQuerySpotAPI.m
//

// ----------------------------------------------------------------------------
// NOTE: This file is generated from Google APIs Discovery Service.
// Service:
//   spotAPI/v1
// Description:
//   API for Spots.
// Classes:
//   GTLQuerySpotAPI (4 custom class methods, 4 custom properties)

#import "GTLQuerySpotAPI.h"

#import "GTLSpotAPIResponse.h"
#import "GTLSpotAPISpot.h"
#import "GTLSpotAPISpotResultList.h"

@implementation GTLQuerySpotAPI

@dynamic fields, onlyExcludedPictures, spotId, spotName;

#pragma mark -
#pragma mark Service level methods
// These create a GTLQuerySpotAPI object.

+ (id)queryForGetNearestSpotByAddressWithSpotName:(NSString *)spotName {
  NSString *methodName = @"spotAPI.getNearestSpotByAddress";
  GTLQuerySpotAPI *query = [self queryWithMethodName:methodName];
  query.spotName = spotName;
  query.expectedObjectClass = [GTLSpotAPISpot class];
  return query;
}

+ (id)queryForGetSpotByIdWithSpotId:(NSString *)spotId {
  NSString *methodName = @"spotAPI.getSpotById";
  GTLQuerySpotAPI *query = [self queryWithMethodName:methodName];
  query.spotId = spotId;
  query.expectedObjectClass = [GTLSpotAPISpot class];
  return query;
}

+ (id)queryForGetSpotByNamePutToCrawlQueueWithSpotName:(NSString *)spotName
                                  onlyExcludedPictures:(BOOL)onlyExcludedPictures {
  NSString *methodName = @"spotAPI.getSpotByNamePutToCrawlQueue";
  GTLQuerySpotAPI *query = [self queryWithMethodName:methodName];
  query.spotName = spotName;
  query.onlyExcludedPictures = onlyExcludedPictures;
  query.expectedObjectClass = [GTLSpotAPIResponse class];
  return query;
}

+ (id)queryForGetTopSpots {
  NSString *methodName = @"spotAPI.getTopSpots";
  GTLQuerySpotAPI *query = [self queryWithMethodName:methodName];
  query.expectedObjectClass = [GTLSpotAPISpotResultList class];
  return query;
}

@end