//
//  SeekretLogging.h
//  SEEKRET
//
//  Created by Constantin Scheuermann on 9/14/14.
//  Copyright (c) 2014 nomis-development.net. All rights reserved.
//

#ifndef SEEKRET_SeekretLogging_h
#define SEEKRET_SeekretLogging_h

#define LOG_FLAG_ERROR    (1 << 0)  // 0...00001
#define LOG_FLAG_WARN     (1 << 1)  // 0...00010
#define LOG_FLAG_INFO     (1 << 2)  // 0...00100
#define LOG_FLAG_DEBUG    (1 << 3)  // 0...01000
#define LOG_FLAG_VERBOSE  (1 << 4)  // 0...10000

static const int ddLogLevel = LOG_FLAG_ERROR | LOG_FLAG_WARN | LOG_FLAG_INFO | LOG_FLAG_DEBUG | LOG_FLAG_VERBOSE;


#endif
