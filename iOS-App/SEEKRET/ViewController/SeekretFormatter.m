//
//  MyCustomFormatter.m
//  SEEKRET
//
//  Created by Constantin Scheuermann on 9/14/14.
//  Copyright (c) 2014 nomis-development.net. All rights reserved.
//
#import "SeekretFormatter.h"

@implementation SeekretFormatter

- (NSString *)formatLogMessage:(DDLogMessage *)logMessage
{
    NSString *logLevel;
    switch (logMessage->logFlag)
    {
        case LOG_FLAG_ERROR : logLevel = @"ERROR"; break;
        case LOG_FLAG_WARN  : logLevel = @"WARN"; break;
        case LOG_FLAG_INFO  : logLevel = @"INFO"; break;
        case LOG_FLAG_DEBUG : logLevel = @"DEBUG"; break;
        default             : logLevel = @"DEFAULT"; break;
    }
    
    NSString *fileAsString = [NSString stringWithFormat:@"%s", logMessage->file];
    
    
    NSArray* stringAsArray = [fileAsString componentsSeparatedByString: @"/"];
    NSString* className = [stringAsArray  lastObject];
    
    
    return [NSString stringWithFormat:@"%@ | %@ %s %d | %@ ", logLevel, className, logMessage->function,logMessage->lineNumber,  logMessage->logMsg];
     // return [NSString stringWithFormat:@"%@", className];
}

@end