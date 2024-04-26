#!/usr/bin/env bash
set -e
for d in ps1 ps2 ps3 ps4; do
  echo "building $d"
  cd $d
  javac *.java
  cd ..
done
