#!/bin/bash

echo "I will now create everything you need."

/Users/scheuermann/appengine-java-sdk-1.9.3/bin/endpoints.sh get-discovery-doc --output=01_discoveryfiles --format=rpc com.seekret.endpoints.ClusterService com.seekret.endpoints.RatingService com.seekret.endpoints.SpotService com.seekret.endpoints.UserService

/Users/scheuermann/appengine-java-sdk-1.9.3/bin/endpoints.sh get-client-lib --output=02_clientLib com.seekret.endpoints.ClusterService com.seekret.endpoints.RatingService com.seekret.endpoints.SpotService com.seekret.endpoints.UserService
zip -r discovery_files.zip 01_discoveryfiles/
zip -r client_lib.zip 02_clientLib/
mv discovery_files.zip /Users/scheuermann/Dropbox/EndpointsSeekret/
mv client_lib.zip /Users/scheuermann/Dropbox/EndpointsSeekret/
