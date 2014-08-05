#!/bin/bash

echo "I will now create everything you need."

/Users/scheuermann/appengine-java-sdk-1.9.3/bin/endpoints.sh get-discovery-doc --output=01_discoveryfiles --format=rpc com.flickranalyser.endpoints.ClusterService com.flickranalyser.endpoints.RatingService com.flickranalyser.endpoints.SpotService com.flickranalyser.endpoints.UserService

/Users/scheuermann/appengine-java-sdk-1.9.3/bin/endpoints.sh get-client-lib --output=02_clientLib com.flickranalyser.endpoints.ClusterService com.flickranalyser.endpoints.RatingService com.flickranalyser.endpoints.SpotService com.flickranalyser.endpoints.UserService
zip -r discovery_files.zip 01_discoveryfiles/
zip -r client_lib.zip 02_clientLib/
mv discovery_files.zip /Users/scheuermann/Dropbox/EndpointsSeekret/
mv client_lib.zip /Users/scheuermann/Dropbox/EndpointsSeekret/
