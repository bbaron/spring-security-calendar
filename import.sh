#!/bin/bash
for dir in ../8260_code/chapter02*-calendar
do
  branch=$(basename $dir)
  echo "branch = $branch"
  git checkout -b $branch
  cp -rv $dir .
  git add .
  git ci -m "$branch"
  git checkout master
  rmdir src
done
