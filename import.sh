#!/bin/bash
for dir in ../8260_code/chapter02*-calendar
do
  branch=$(basename $dir)
  echo "branch = $branch"
#    git checkout -b $branch
  ls $dir
#    cp -r ../8260_code/$branch/* .
#    git st
#    git add .
#    git st
#    git ci -m "$branch"
#    git checkout master
#    rm -rf src/ target/
#    git st
done
