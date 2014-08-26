/* This file was generated by the ServiceGenerator.
 * The ServiceGenerator is Copyright (c) 2014 Google Inc.
 */

//
//  GTLSpotAPICluster.h
//

// ----------------------------------------------------------------------------
// NOTE: This file is generated from Google APIs Discovery Service.
// Service:
//   spotAPI/v1
// Description:
//   API for Spots.
// Classes:
//   GTLSpotAPICluster (0 custom class methods, 12 custom properties)

#if GTL_BUILT_AS_FRAMEWORK
  #import "GTL/GTLObject.h"
#else
  #import "GTLObject.h"
#endif

@class GTLSpotAPIPointOfInterest;

// ----------------------------------------------------------------------------
//
//   GTLSpotAPICluster
//

@interface GTLSpotAPICluster : GTLObject
@property (copy) NSString *datastoreClusterKey;

// Remapped to 'descriptionProperty' to avoid NSObject's 'description'.
@property (copy) NSString *descriptionProperty;

@property (retain) NSNumber *dismissCounter;  // intValue
@property (retain) NSNumber *latitude;  // doubleValue
@property (retain) NSNumber *longitude;  // doubleValue
@property (copy) NSString *name;
@property (retain) NSNumber *numberOfPOIs;  // intValue
@property (retain) NSNumber *overallTouristicnessInPointsFrom1To10;  // doubleValue
@property (retain) NSNumber *overallTouristicnessVotes;  // intValue
@property (retain) NSNumber *overallViews;  // intValue
@property (retain) NSArray *pointOfInterestList;  // of GTLSpotAPIPointOfInterest
@property (retain) NSArray *urlOfMostViewedPicture;  // of NSString
@end