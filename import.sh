#!/bin/bash
for dir in ../8260_code/chapter02*-calendar
do
  branch=$(basename $dir)
  echo "branch = $branch"
  git checkout -b $branch
  cp -r $dir/* .
  git add .
  git ci -m "$branch"
  git checkout master
  rm -rf src target 2> /dev/null
done
