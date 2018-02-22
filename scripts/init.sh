#!/usr/bin/env bash

mongod
mongo < init-mongo-script/init.js

echo 'Init script executed.'